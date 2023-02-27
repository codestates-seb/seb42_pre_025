package com.stackoverflow.team25.question.service;

import com.stackoverflow.team25.post.entity.Post;
import com.stackoverflow.team25.post.service.PostServiceImpl;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.question.repository.QuestionRepository;
import com.stackoverflow.team25.exception.BusinessLogicException;
import com.stackoverflow.team25.exception.ExceptionCode;


import com.stackoverflow.team25.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final PostServiceImpl postServiceImpl;

    public Question createQuestion(Question question, Long userId) {
        /**
         * Post 등록하기
         */
        // Todo: Post에 QuestionID , AnswerId 넣기
            // (1) 여기서 처리하기
            // (2) 커스텀 save 를 만들어 insert문 만들어주기
        Post post = new Post();
        post.setPostType("q");
        Post savedPost = postServiceImpl.createPost(post); // 이 시점에 postId 생성됨

        /**
         * Question 등록하기
         * - postId == questionId
         * - 등록한 Question을 Post에 넣어줍니다.
         */
        question.setQuestionId(savedPost.getPostId());
        String title = question.getTitle();
//       verifyExistQuestion(title);
        question.setTitle(title);
        question.setAnswerCount(0);
        List<String> tags = question.getTags();
        question.setTags(tags);


        User user = new User();
        user.setUserId(userId);
        question.setUser(user);

        Question savedQuestion = questionRepository.save(question);

        savedPost.setQuestion(savedQuestion);
        postServiceImpl.createPost(savedPost);

        return savedQuestion;
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
        Optional.ofNullable(question.getTags())
                .ifPresent(verifiedQuestion::setTags);

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