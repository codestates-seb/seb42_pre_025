package com.stackoverflow.team25.question.service;

import com.stackoverflow.team25.exception.BusinessLogicException;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.question.repository.QuestionRepository;
import com.stackoverflow.team25.tag.entity.Tag;
import com.stackoverflow.team25.tag.repository.TagRepository;
import com.stackoverflow.team25.tag.service.TagService;
import com.stackoverflow.team25.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.stackoverflow.team25.exception.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final TagService tagService;

    @Override
    @Transactional
    public Question createQuestion(Question question) {
        String title = question.getTitle();
        Long userId = question.getUser().getUserId();

        verifyExistQuestion(title);
        question.setAnswerCount(0);
        question.setUser(userRepository.findById(userId).orElseThrow(() ->
                new BusinessLogicException(MEMBER_NOT_FOUND)));

        question.getQuestionTags().forEach(questionTag -> {
            String name = questionTag.getTag().getName();
            Tag tag = tagService.verifyTag(name);
            questionTag.setTag(tag);
        });

        return questionRepository.save(question);
    }

    @Override
    @Transactional
    public Question updateQuestion(Question question) {
        Long questionId = question.getQuestionId();
        Question verifiedQuestion = verifyQuestionById(questionId);
        if (!Objects.equals(verifiedQuestion.getUser().getUserId(), question.getUser().getUserId())) {
            throw new RuntimeException("수정할 수 있는 회원이 아닙니다.");
        }

        Optional.ofNullable(question.getTitle())
                .ifPresent(verifiedQuestion::setTitle);
        Optional.ofNullable(question.getContent())
                .ifPresent(verifiedQuestion::setContent);
        question.getQuestionTags().forEach(questionTag -> {
            String name = questionTag.getTag().getName();
            Tag tag = tagService.verifyTag(name);
            questionTag.setTag(tag);
        });

        verifiedQuestion.getQuestionTags().clear();
        question.getQuestionTags().iterator().forEachRemaining(tag ->
                verifiedQuestion.getQuestionTags().add(tag)
        );

        return verifiedQuestion;
    }

//    public Question updateAnserCount(Question question) {
//        return qR
//    }

    @Override
    public Question findQuestion(long questionId) {

        return findVerifiedQuestion(questionId);
    }

    @Override
    public Page<Question> findQuestions(Pageable pageable) {
        PageRequest of = PageRequest.of(pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                pageable.getSort());
        return questionRepository.findQuestionPage(of);
    }

    @Override
    @Transactional
    public void deleteQuestion(Long questionId) {
        Question question = verifyQuestionById(questionId);
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.equals(question.getUser().getUserId(), userId)) {
            throw new RuntimeException("삭제할 수 있는 회원이 아닙니다.");
        }
        question.setQuestionType(Question.QuestionType.DELETED);
        questionRepository.save(question);
    }

    @Override
    @Transactional
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        return optionalQuestion.orElseThrow(() ->
                new BusinessLogicException(QUESTION_NOT_FOUND));
    }


    //#### 내부 메서드 ###//
    private Question verifyQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("해당 질문이 존재 하지 않음"));
    }

    private void verifyExistQuestion(String title) {
        Optional<Question> question = questionRepository.findByTitle(title);
        if (question.isPresent())
            throw new BusinessLogicException(TITLE_EXISTS);
    }

    private void verifyExistTag(List<String> tagNames) {
        for (String tagName : tagNames) {
            Optional<Tag> tag = tagRepository.findByName(tagName);
            if (tag.isEmpty()) tagService.createTag(tagName);
        }
    }
}