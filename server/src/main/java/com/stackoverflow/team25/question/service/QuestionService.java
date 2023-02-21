package com.stackoverflow.team25.question.service;

import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.question.repository.QuestionRepository;
import com.stackoverflow.team25.exception.BusinessLogicException;
import com.stackoverflow.team25.exception.ExceptionCode;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;




@Service
public class QuestionService {
    private final QuestionRepository questionRepository;


    public QuestionService(QuestionRepository questionRepository)  {
        this.questionRepository = questionRepository;
    }
//c
    public Question createQuestion(Question question) {
        String title = question.getTitle();
        verifyExistQuestion(title);
        question.setTitle(title);

        return questionRepository.save(question);
    }

    private void verifyExistQuestion(String title) {
        Optional<Question> question = questionRepository.findByTitle(title);
        if(question.isPresent())
            throw new BusinessLogicException(ExceptionCode.TITLE_EXISTS);
    }
//r
    public Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }

    public Question findQuestion(long questionId) {
        return findVerifiedQuestionByQuery(questionId);
    }
    private Question findVerifiedQuestionByQuery(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }

    public Page<Question> findQuestions(Pageable pageable) {
        PageRequest of = PageRequest.of(pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                pageable.getSort());

        return questionRepository.findAll(of);
    }
//u
    public Question updateQuestion(Question question) {
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());

        return questionRepository.save(findQuestion);
    }


//d
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
     
 
}