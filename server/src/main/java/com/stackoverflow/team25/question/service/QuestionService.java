package com.stackoverflow.team25.question.service;

import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.question.repository.QuestionRepository;
import com.stackoverflow.team25.exception.BusinessLogicException;
import com.stackoverflow.team25.exception.ExceptionCode;


import com.stackoverflow.team25.tag.entity.Tag;
import com.stackoverflow.team25.tag.repository.TagRepository;
import com.stackoverflow.team25.tag.service.TagService;
import com.stackoverflow.team25.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;




@Service

public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final TagService tagService;


    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository, TagRepository tagRepository, TagService tagService)  {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.tagService = tagService;
    }

    public Question createQuestion(Question question, Long userId, List<String> tagNames) {
        String title = question.getTitle();
//       verifyExistQuestion(title);
        question.setTitle(title);
        question.setAnswerCount(0);
        question.setUser(userRepository.findById(userId).orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND)));
        if (tagNames != null && !tagNames.isEmpty()) {
            verifyExistTag(tagNames);
            List<Tag> tags = tagRepository.findAllByNameIn(tagNames);
            question.setTags(tags);
        }
        return questionRepository.save(question);
    }

    private void verifyExistTag(List<String> tagNames) {
        for (String tagName : tagNames) {
            Optional<Tag> tag = tagRepository.findByName(tagName);
            if(!tag.isPresent()) tagService.createTag(tagName);
        }
    }

    private void verifyExistQuestion(String title) {
        Optional<Question> question = questionRepository.findByTitle(title);
        if(question.isPresent())
            throw new BusinessLogicException(ExceptionCode.TITLE_EXISTS);
    }

    public Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }

    public Question findQuestion(long questionId) {
        return findVerifiedQuestion(questionId);
    }

    public Page<Question> findQuestions(Pageable pageable) {
        PageRequest of = PageRequest.of(pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                pageable.getSort());

        return questionRepository.findAll(of);
    }

    public Question updateQuestion(Question question) {
        Long questionId = question.getQuestionId();
        Question findQuestion = findVerifiedQuestion(question.getQuestionId());
        Question verifiedQuestion = verifyQuestionById(questionId);

        Optional.ofNullable(question.getTitle())
                .ifPresent(verifiedQuestion::setTitle);
        Optional.ofNullable(question.getContent())
                .ifPresent(verifiedQuestion::setContent);
        Optional.ofNullable(question.getAnswerCount())
                .ifPresent(verifiedQuestion::setAnswerCount);

        return questionRepository.save(findQuestion);
    }

    private Question verifyQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> {
                    throw new RuntimeException("해당 질문이 존재 하지 않음");
                });
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }


}