package com.stackoverflow.team25.question.service;

import com.stackoverflow.team25.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {
    Question createQuestion(Question question);

    Question updateQuestion(Question question);

    Question findQuestion(long questionId);

    Page<Question> findQuestions(Pageable pageable);

    void deleteQuestion(Long questionId);

    void saveQuestion(Question question);

    Question findVerifiedQuestion(long questionId);

}
