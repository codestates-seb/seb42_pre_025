package com.stackoverflow.team25.answer.service;

import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.answer.repository.AnswerRepository;
import com.stackoverflow.team25.post.entity.Post;
import com.stackoverflow.team25.post.service.PostServiceImpl;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.question.service.QuestionService;
import com.stackoverflow.team25.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerServiceImpl implements AnswerService{
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final UserService userService;
    private final PostServiceImpl postServiceImpl;
    public Answer createAnswer(Answer answer){
        /**
         * Post 등록하기
         * - postId == answerId
         */
        Post post = new Post();
        post.setPostType("a");
        Post savedPost = postServiceImpl.createPost(post);

        /**
         * 1. PostId 를 AnswerId로 저장
         * 2. Score와 isAccepted 필드에 Null이 아닌 초기값을 저장하도록 설정
         */
        answer.setAnswerId(savedPost.getPostId());
        answer.setScore(0L);
        answer.setIsAccepted(false);
        /**
         * 1. 받아온 questionId를 가진 Question이 존재하는 지 검증
         * 2. Answer를 추가할수록, Question의 answerCount가 증가함.
         */
        Question forPatchingQuestion = questionService.findVerifiedQuestion(answer.getQuestion().getQuestionId());
        forPatchingQuestion.setAnswerCount(forPatchingQuestion.getAnswerCount()+1);
        questionService.updateQuestion(forPatchingQuestion);

        return answerRepository.save(answer);
    }

    public Answer updateAnswer(Answer answer){
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        /**
         * 수정에 용이하도록 Answer Entity의 필드들은 null이 들어갈 수 있는 객체들로 구성하였다.
         *      1. null 값이 들어오면(따로 입력받지 못하면), 수정 되지 않는다.
         *      2. null 이 아닌 값이 들어오면, 수정 된다.
         */
        Optional.ofNullable(answer.getScore())
                .ifPresent(score -> findAnswer.setScore(score));
        Optional.ofNullable(answer.getIsAccepted())
                .ifPresent(isAccepted -> findAnswer.setIsAccepted(isAccepted));
        Optional.ofNullable(answer.getContent())
                .ifPresent(content -> findAnswer.setContent(content));
        Optional.ofNullable(answer.getModifiedAt())
                .ifPresent(modifiedAt -> findAnswer.setModifiedAt(modifiedAt));

        return answerRepository.save(findAnswer);
    }

    public Answer findAnswer(long answerId){
        return findVerifiedAnswer(answerId);
    }

    public Page<Answer> findAnswers(Pageable pageable){
        PageRequest of = PageRequest.of(pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                pageable.getSort());

        return answerRepository.findAll(of);
    }

    public void removeAnswer(long answerId){
        Answer findAnswer = findVerifiedAnswer(answerId);
        /**
         * Answer 삭제시, Question의 AnswerCount 개수를 한개 줄인다.
         */
        Question forPatchingQuestion = questionService.findVerifiedQuestion(findAnswer.getQuestion().getQuestionId());
        forPatchingQuestion.setAnswerCount(forPatchingQuestion.getAnswerCount()-1);
        questionService.updateQuestion(forPatchingQuestion);
        answerRepository.delete(findAnswer);

    }

    public Answer findVerifiedAnswer(long answerId){
        Optional<Answer> optionalAnswer = answerRepository.findById(answerId);
        Answer answer = optionalAnswer.orElseThrow(() -> {throw new RuntimeException("Answer가 DB에 없다.");});

        return answer;
    }
}
