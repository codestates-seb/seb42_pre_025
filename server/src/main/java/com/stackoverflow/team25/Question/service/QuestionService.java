package com.stackoverflow.team25.Question.service;

import com.stackoverflow.team25.Question.dto.QuestionPatchDto;
import com.stackoverflow.team25.Question.dto.QuestionPostDto;
import com.stackoverflow.team25.Question.dto.QuestionResponseDto;
import com.stackoverflow.team25.Question.entity.Question;
import com.stackoverflow.team25.Question.mapper.QuestionMapper;
import com.stackoverflow.team25.Question.repository.QuestionRepository;
import com.stackoverflow.team25.exception.BusinessLogicException;
import com.stackoverflow.team25.exception.ExceptionCode;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<Question> optionalQuestion = questionRepository.findByQuestion(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }

    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size,
                Sort.by("questionId").descending()));
    }
//u
    public Question updateQuestion(Question question) {
        // 조회하려는 커피가 검증된 커피인지 확인(존재하는 커피인지 확인 등)
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());

        return questionRepository.save(findQuestion);
    }


//d
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
     
 
}