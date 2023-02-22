package com.stackoverflow.team25.answer.service;

import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.answer.repository.AnswerRepository;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerServiceImpl implements AnswerService{
    private final AnswerRepository answerRepository;

    public Answer createAnswer(Answer answer){
        /**
         * 편의 메서드를 이용해 외래키 필드(question, owner) 를 넣어줍니다.
         */
        Question question = new Question();
        question.setQuestionId(answer.getQuestion().getQuestionId());
        answer.addQuestion(question);

//        User user = new User();
//        user.setUserId(answer.getUser().getUserId());
//        answer.addUser(user);
        /**
         * Score와 isAccepted 필드에 Null이 아닌 초기값을 저장하도록 설정
         */
        answer.setScore(0L);
        answer.setIsAccepted(false);

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer){
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        /**
         * 수정에 용이하도록 Answer Entity의 필드들은 null이 들어갈 수 있는 객체들로 구성하였다.
         *      1. null 값이 들어오면(따로 입력받지 못하면), 수정 되지 않는다.
         *      2. null 이 아닌 값이 들어오면, 수정 된다.
         */
        Optional.ofNullable(answer.getScore())
                .ifPresent(score -> findAnswer.setScore(score));
        Optional.ofNullable(answer.getIsAccepted())
                .ifPresent(isAccepted -> findAnswer.setIsAccepted(isAccepted));
        Optional.ofNullable(answer.getContent())
                .ifPresent(content -> findAnswer.setContent(content));
        Optional.ofNullable(answer.getModifiedAt())
                .ifPresent(modifiedAt -> findAnswer.setModifiedAt(modifiedAt));

        return answerRepository.save(findAnswer);
    }

    public Answer findAnswer(long answerId){
        return findVerifiedAnswer(answerId);
    }

    public Page<Answer> findAnswers(Pageable pageable){
        PageRequest of = PageRequest.of(pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                pageable.getSort());

        return answerRepository.findAll(of);
    }

    public void removeAnswer(long answerId){
        Answer findAnswer = findVerifiedAnswer(answerId);

        answerRepository.delete(findAnswer);
    }

    public Answer findVerifiedAnswer(long answerId){
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer answer = optionalAnswer.orElseThrow(() -> {throw new RuntimeException("Answer가 DB에 없다.");});

        return answer;
    }
}
