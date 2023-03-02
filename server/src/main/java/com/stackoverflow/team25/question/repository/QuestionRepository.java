package com.stackoverflow.team25.question.repository;

import com.stackoverflow.team25.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT c FROM Question c WHERE c.title LIKE %:title%")
    Optional<Question> findByTitle(String title);

    @Query(value = "SELECT q FROM Question q WHERE q.questionType = 'ACTIVATE'")
    Page<Question> findQuestionPage(Pageable pageable);
}
