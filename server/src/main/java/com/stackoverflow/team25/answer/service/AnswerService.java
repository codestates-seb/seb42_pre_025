package com.stackoverflow.team25.answer.service;


import com.stackoverflow.team25.answer.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface
AnswerService {
    Answer createAnswer(Answer answer);
    Answer updateAnswer(Answer answer);
    Answer findAnswer(long answerId);
    Page<Answer> findAnswers(Pageable pageable);
    void removeAnswer(long answerId);
    List<Answer> findAnswersByQuestion(Long questionId);
}
