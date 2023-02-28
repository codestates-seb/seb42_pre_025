package com.stackoverflow.team25.answer.service;

import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.answer.repository.AnswerRepository;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.question.service.QuestionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionServiceImpl questionServiceImpl;

    @Override
    @Transactional
    public Answer createAnswer(Answer answer) {
        /**
         * 1. PostId 를 AnswerId로 저장
         * 2. Score와 isAccepted 필드에 Null이 아닌 초기값을 저장하도록 설정
         */
        answer.setScore(0L);
        answer.setIsAccepted(false);
//        answer.setOwner(User.builder().userId(getUserId()).build());
        /**
         * 1. 받아온 questionId를 가진 Question이 존재하는 지 검증
         * 2. Answer를 추가할수록, Question의 answerCount가 증가함.
         */
        Question forPatchingQuestion = questionServiceImpl.findVerifiedQuestion(answer.getQuestion().getQuestionId());
        forPatchingQuestion.setAnswerCount(forPatchingQuestion.getAnswerCount() + 1);
        questionServiceImpl.updateQuestion(forPatchingQuestion);
        /**
         * DB에 저장한 Answer를 Post에 넣어줍니다.
         */
        Answer savedAnswer = answerRepository.save(answer);

        return savedAnswer;
    }

    @Override
    @Transactional
    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        if (findAnswer.getOwner().getUserId() != answer.getOwner().getUserId()) {
            throw new RuntimeException("글을 작성한 회원이 아닙니다");
        }
        /**
         * 수정에 용이하도록 Answer Entity의 필드들은 null이 들어갈 수 있는 객체들로 구성하였다.
         *      1. null 값이 들어오면(따로 입력받지 못하면), 수정 되지 않는다.
         *      2. null 이 아닌 값이 들어오면, 수정 된다.
         */
//        Optional.ofNullable(answer.getScore())
//                .ifPresent(findAnswer::setScore);
//        Optional.ofNullable(answer.getIsAccepted())
//                .ifPresent(findAnswer::setIsAccepted);
//        Optional.ofNullable(answer.getContent())
//                .ifPresent(findAnswer::setContent);
//        Optional.ofNullable(answer.getModifiedAt())
//                .ifPresent(findAnswer::setModifiedAt);
//
        Optional.ofNullable(answer.getContent())
                .ifPresent(findAnswer::setContent);

        return answerRepository.save(findAnswer);
    }

    @Override
    public Answer findAnswer(long answerId) {
        return findVerifiedAnswer(answerId);
    }

    @Override
    public Page<Answer> findAnswers(Pageable pageable) {
        PageRequest of = PageRequest.of(pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                pageable.getSort());

        return answerRepository.findAll(of);
    }

    @Override
    @Transactional
    public void removeAnswer(long answerId) {
        //TODO: 로직 확인 하기, 삭제된 댓글 조회 안되게 하기
        Answer findAnswer = findVerifiedAnswer(answerId);
        /**
         * Answer 삭제시, Question의 AnswerCount 개수를 한개 줄인다.
         */
        Long questionId = findAnswer.getQuestion().getQuestionId();
        Question verifiedQuestion = questionServiceImpl.findVerifiedQuestion(questionId);
        verifiedQuestion.setAnswerCount(verifiedQuestion.getAnswerCount() - 1);

        findAnswer.setAnswerType(Answer.AnswerType.DELETE);
        questionServiceImpl.saveQuestion(verifiedQuestion);
        answerRepository.save(findAnswer);
    }

    public Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer answer = optionalAnswer.orElseThrow(() -> {
            throw new RuntimeException("Answer가 DB에 없다.");
        });

        return answer;
    }

    @Override
    public List<Answer> findAnswersByQuestion(Long questionId) {
        List<Answer> findAnswers = answerRepository.findAllByQuestionId(questionId);

        return findAnswers;
    }
}
