package com.stackoverflow.team25.answer.repository;


import com.stackoverflow.team25.answer.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Page<Answer> findAll(Pageable pageable);

    @Query(value = "SELECT a FROM Answer a WHERE a.question.questionId = :questionId")
    List<Answer> findAllByQuestionId(Long questionId);
}
