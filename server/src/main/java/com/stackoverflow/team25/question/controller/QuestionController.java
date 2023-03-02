package com.stackoverflow.team25.question.controller;

import com.stackoverflow.team25.answer.dto.AnswerDto;
import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.answer.mapper.AnswerMapper;
import com.stackoverflow.team25.answer.service.AnswerService;
import com.stackoverflow.team25.dto.MultiResponseDto;
import com.stackoverflow.team25.dto.SingleResponseDto;
import com.stackoverflow.team25.question.dto.QuestionDto;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.question.mapper.QuestionMapper;
import com.stackoverflow.team25.question.service.QuestionService;
import com.stackoverflow.team25.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/questions")
@Validated
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final static String QUESTION_DEFAULT_URL = "/api/questions";
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;

    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post post) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUserId(userId);
        Question question = questionService.createQuestion(questionMapper.questionPostDtoToQuestion(post));
        URI location = UriCreator.createUri(QUESTION_DEFAULT_URL, question.getQuestionId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive long questionId,
                                        @Valid @RequestBody QuestionDto.Patch patch) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        patch.setUserId(userId);
        patch.setQuestionId(questionId);
        Question question = questionService.updateQuestion(questionMapper.questionPatchDtoToQuestion(patch));
        QuestionDto.Response response = questionMapper.questionToQuestionResponseDto(question);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") long questionId) {
        Question question = questionService.findQuestion(questionId);
        List<Answer> answers = question.getAnswers().stream()
                .filter(answer -> answer.getAnswerType().equals(Answer.AnswerType.ACTIVATE))
                .collect(Collectors.toList());
        question.setAnswers(answers);
        QuestionDto.Response response = questionMapper.questionToQuestionResponseDto(question);

        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getQuestions(Pageable pageable) {
        Page<Question> pageQuestions = questionService.findQuestions(pageable);
        List<Question> questions = pageQuestions.getContent();
        List<QuestionDto.Response> responses = questionMapper.questionsToQuestionResponseDtos(questions);

        return new ResponseEntity<>(new MultiResponseDto<>(responses,pageQuestions),HttpStatus.OK);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable long questionId) {
        questionService.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{question-id}/add")
    public ResponseEntity postAnswer(@PathVariable("question-id") Long questionId,
                                     @RequestBody AnswerDto.Post postDto) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postDto.setUserId(userId);
        postDto.setQuestionId(questionId);
        Answer findAnswer = answerService.createAnswer(answerMapper.answerPostDtoToAnswer(postDto));
        URI location = UriCreator.createUri(QUESTION_DEFAULT_URL + "/" + questionId + "/add", findAnswer.getAnswerId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{question-id}/answers")
    public ResponseEntity getAnswersByQuestionId(@PathVariable("question-id") Long questionId) {
        List<Answer> answerByQuestion = answerService.findAnswersByQuestion(questionId);
        List<AnswerDto.Response> responses = answerMapper. answersToAnswerResponseDtos(answerByQuestion);

        return new ResponseEntity<>(new SingleResponseDto<>(responses), HttpStatus.OK);
    }
}
