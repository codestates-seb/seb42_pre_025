package com.stackoverflow.team25.answer.service;

import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.answer.repository.AnswerRepository;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    @Override
    @Transactional
    public Answer createAnswer(Answer answer) {
        answer.setScore(0L);
        answer.setIsAccepted(false);

        Question verifiedQuestion = questionService.findVerifiedQuestion(answer.getQuestion().getQuestionId());
        verifiedQuestion.setAnswerCount(verifiedQuestion.getAnswerCount() + 1);

        return answerRepository.save(answer);
    }

    @Override
    @Transactional
    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        if (!Objects.equals(findAnswer.getOwner().getUserId(), answer.getOwner().getUserId())) {
            throw new RuntimeException("글을 작성한 회원이 아닙니다");
        }
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
        Long questionId = findAnswer.getQuestion().getQuestionId();
        Question verifiedQuestion = questionService.findVerifiedQuestion(questionId);

        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(findAnswer.getOwner().getUserId(), userId)) {
            throw new RuntimeException("삭제할 수 있는 회원이 아닙니다.");
        }

        if (!findAnswer.getAnswerType().equals(Answer.AnswerType.DELETE)){
            findAnswer.setAnswerType(Answer.AnswerType.DELETE);
            verifiedQuestion.setAnswerCount(verifiedQuestion.getAnswerCount() - 1);
        }
    }

    public Answer findVerifiedAnswer(long answerId) {
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);

        return optionalAnswer.orElseThrow(() -> new RuntimeException("Answer가 DB에 없다."));
    }

    @Override
    public List<Answer> findAnswersByQuestion(Long questionId) {

        return answerRepository.findAllByQuestionQuestionIdAndAnswerType(questionId, Answer.AnswerType.ACTIVATE);
    }
}
