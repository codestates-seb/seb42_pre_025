package com.stackoverflow.team25.question.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stackoverflow.team25.answer.dto.AnswerDto;
import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.answer.mapper.AnswerMapper;
import com.stackoverflow.team25.answer.service.AnswerService;
import com.stackoverflow.team25.question.dto.QuestionDto;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.question.mapper.QuestionMapper;
import com.stackoverflow.team25.question.service.QuestionService;
import com.stackoverflow.team25.user.dto.UserDto;
import com.stackoverflow.team25.user.entity.User;
import com.stackoverflow.team25.user.mapper.UserMapper;
import com.stackoverflow.team25.user.service.UserService;
import com.stackoverflow.team25.utils.UriCreator;
import com.stackoverflow.team25.dto.MultiResponseDto;
import com.stackoverflow.team25.dto.SingleResponseDto;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/questions")
@Validated
@RequiredArgsConstructor
public class QuestionController {
    private final static String QUESTION_DEFAULT_URL = "/questions";
    private final QuestionService questionService;
    private final QuestionMapper mapper;
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.QuestionPostDto questionPostDto) {
        Question question = questionService.createQuestion(mapper.questionPostDtoToQuestion(questionPostDto),questionPostDto.getUserId());
        URI location = UriCreator.createUri(QUESTION_DEFAULT_URL, question.getQuestionId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive long questionId,
                                        @Valid @RequestBody QuestionDto.QuestionPatchDto questionPatchDto) {
        questionPatchDto.setQuestionId(questionId);
        Question question = questionService.updateQuestion(mapper.questionPatchDtoToQuestion(questionPatchDto));
        QuestionDto.QuestionResponseDto response = mapper.questionToQuestionResponseDto(question);
        response.setUserDto(UserDto.Response.builder().user(question.getUser()).build());

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }
    @GetMapping("/{question-id}")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResponseEntity getQuestion(@PathVariable("question-id") long questionId) {
        Question question = questionService.findQuestion(questionId);
        QuestionDto.QuestionResponseDto response = mapper.questionToQuestionResponseDto(question);
        response.setUserDto(UserDto.Response.builder().user(question.getUser()).build());

        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getQuestions(Pageable pageable) {
        Page<Question> pageQuestions = questionService.findQuestions(pageable);
        List<Question> questions = pageQuestions.getContent();
        List<QuestionDto.QuestionResponseDto> responses = mapper.questionsToQuestionResponseDtos(questions);
        for (QuestionDto.QuestionResponseDto response : responses) {
            Question question = questionService.findQuestion(response.getQuestionId());
            response.setUserDto(UserDto.Response.builder().user(question.getUser()).build());
        }

        return new ResponseEntity<>(new MultiResponseDto<>(responses,pageQuestions),HttpStatus.OK);
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") long questionId) {
        questionService.deleteQuestion(questionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{question-id}/add")
    public ResponseEntity postAnswer(@PathVariable("question-id") Long questionId,
                                     @RequestBody AnswerDto.Post postDto) {
        postDto.setQuestionId(questionId);
        Answer findAnswer = answerService.createAnswer(answerMapper.answerPostDtoToAnswer(postDto));

        URI location = UriCreator.createUri(QUESTION_DEFAULT_URL + "/" + questionId + "/add", findAnswer.getAnswerId());
        return ResponseEntity.created(location).build();
    }
}
