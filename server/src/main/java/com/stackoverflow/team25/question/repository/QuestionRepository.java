package com.stackoverflow.team25.question.repository;

import com.stackoverflow.team25.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT c FROM Question c WHERE c.title LIKE %:title%")
    Optional<Question> findByTitle(String title);
}
