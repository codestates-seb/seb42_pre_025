package com.stackoverflow.team25.answer.service;

import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.answer.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;


    public Answer createAnswer(Answer answer){
        /**
         * Score 와 isAccepted가 Null로 들어오는 경우, Null 값이 아닌 다음의 초기값을 가지도록 한다.
         */
        answer.setScore(
                Optional.ofNullable(answer.getScore())
                .orElse(0L)
        );

        answer.setIsAccepted(
                Optional.ofNullable(answer.getIsAccepted())
                        .orElse(false)
        );

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer){
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        /**
         * 수정에 용이하도록 Answer Entity의 필드들은 null이 들어갈 수 있는 객체들로 구성하였다.
         *      1. null 값이 들어오면(따로 입력받지 못하면), 수정 되지 않는다.
         *      2. null 이 아닌 값이 들어오면, 수정 된다.
         */
        System.out.println(answer.getScore());
        Optional.ofNullable(answer.getScore())
                .ifPresent(isAccepted -> findAnswer.setScore(isAccepted));
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

    public List<Answer> findAnswers(){
        List<Answer> findAnswers = answerRepository.findAll();
        return findAnswers;
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
