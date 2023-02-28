package com.stackoverflow.team25.question.service;

import com.stackoverflow.team25.exception.BusinessLogicException;
import com.stackoverflow.team25.exception.ExceptionCode;
import com.stackoverflow.team25.post.entity.Post;
import com.stackoverflow.team25.post.service.PostServiceImpl;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.question.repository.QuestionRepository;
import com.stackoverflow.team25.tag.entity.QuestionTag;
import com.stackoverflow.team25.tag.entity.Tag;
import com.stackoverflow.team25.tag.repository.QuestionTagRepository;
import com.stackoverflow.team25.tag.repository.TagRepository;
import com.stackoverflow.team25.tag.service.TagService;
import com.stackoverflow.team25.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final TagService tagService;
    private final QuestionTagRepository questionTagRepository;

    public Question createQuestion(Question question) {
        String title = question.getTitle();
        Long userId = question.getUser().getUserId();

        verifyExistQuestion(title);
        question.setAnswerCount(0);
        question.setUser(userRepository.findById(userId).orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND)));

        question.getQuestionTags().forEach(questionTag -> {
            String name = questionTag.getTag().getName();
            Tag tag = tagService.verifyTag(name);
            questionTag.setTag(tag);
        });

        Question savedQuestion = questionRepository.save(question);

        return savedQuestion;
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
        Optional.ofNullable(question.getQuestionTags())
                .ifPresent(verifiedQuestion::setQuestionTags);


        Optional<List<QuestionTag>> questionTags = Optional.ofNullable(question.getQuestionTags());
        questionTags.ifPresent
                (qTags -> {
                    List<Tag> originalTags = findQuestion.getTags();

                    List<Tag> newTags = patchTags.get().stream()
                            .filter(tag -> !originalTags.contains(tag))
                            .collect(Collectors.toList());

                    newTags.forEach(tag -> {
                        tag.getQuestions().add(findQuestion);
                    });


                    List<Tag> removedTags = originalTags.stream()
                            .filter(tag -> !patchTags.get().contains(tag))
                            .collect(Collectors.toList());

                    removedTags.forEach(tag -> {
                        tag.getQuestions().remove(findQuestion);
                    });
                    findQuestion.setTags(patchTags.get());
                });

        List<Tag> originalTags = verifiedQuestion.getQuestionTags().stream()
                .map(QuestionTag::getTag).collect(Collectors.toList());
        List<Tag> newTags = question.getQuestionTags().stream()
                .map(QuestionTag::getTag)
                .filter(tag -> !originalTags.contains(tag))
                .collect(Collectors.toList());

        newTags.forEach(tag -> {
            tag.getQuestionTags().stream().map(questionTag -> {
                questionTag.getQuestion()
            });
        });

        questionTagRepository.deleteAll(verifiedQuestion.getQuestionTags());

        verifiedQuestion.getQuestionTags().stream()

        question.getQuestionTags().forEach(questionTag -> {
            String name = questionTag.getTag().getName();
            Tag tag = tagService.verifyTag(name);
            questionTag.setTag(tag);
        });









        List<Tag> removedTags = originalTags.stream()
                .filter(tag -> !newTags.contains(tag))
                .collect(Collectors.toList());


        return questionRepository.save(findQuestion);
    }

    public Question findQuestion(long questionId) {
        return findVerifiedQuestion(questionId);
    }

    @Transactional(readOnly = true)
    public Page<Question> findQuestions(Pageable pageable) {
        PageRequest of = PageRequest.of(pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                pageable.getSort());
        log.info("find questions");
        return questionRepository.findAll(of);
    }

    public void deleteQuestion(Long questionId) {
        Question question = verifyQuestionById(questionId);
        question.setQuestionType(Question.QuestionType.DELETED);
        questionRepository.save(question);
    }

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }


    public Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion =
                optionalQuestion.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

        return findQuestion;
    }

    private Question verifyQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> {
                    throw new RuntimeException("해당 질문이 존재 하지 않음");
                });
    }

    private void verifyExistQuestion(String title) {
        Optional<Question> question = questionRepository.findByTitle(title);
        if(question.isPresent())
            throw new BusinessLogicException(ExceptionCode.TITLE_EXISTS);
    }

    private void verifyExistTag(List<String> tagNames) {
        for (String tagName : tagNames) {
            Optional<Tag> tag = tagRepository.findByName(tagName);
            if(!tag.isPresent()) tagService.createTag(tagName);
        }
    }
}