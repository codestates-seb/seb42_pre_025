package com.stackoverflow.team25.Question.repository;

import com.stackoverflow.team25.Question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByTitle(String title);
    @Query(value = "SELECT c FROM Question c WHERE c.questionId = :questionId")
    Optional<Question> findByQuestion(long questionId);
}
