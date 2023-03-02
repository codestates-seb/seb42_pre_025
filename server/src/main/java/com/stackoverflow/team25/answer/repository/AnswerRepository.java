package com.stackoverflow.team25.answer.repository;


import com.stackoverflow.team25.answer.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Page<Answer> findAll(Pageable pageable);

    List<Answer> findAllByQuestionQuestionIdAndAnswerType(long questionsId, Answer.AnswerType answerType);

}
