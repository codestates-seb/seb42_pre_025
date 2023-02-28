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
import com.stackoverflow.team25.question.service.QuestionServiceImpl;
import com.stackoverflow.team25.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/questions")
@Validated
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final static String QUESTION_DEFAULT_URL = "/api/questions";
    private final QuestionServiceImpl questionServiceImpl;
    private final AnswerService answerService;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post post) {
        Question question = questionServiceImpl.createQuestion(questionMapper.questionPostDtoToQuestion(post));
        URI location = UriCreator.createUri(QUESTION_DEFAULT_URL, question.getQuestionId());

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{question-id}")
    public ResponseEntity patchQuestion(@PathVariable("question-id") @Positive long questionId,
                                        @Valid @RequestBody QuestionDto.Patch patch) {
        patch.setQuestionId(questionId);
        Question question = questionServiceImpl.updateQuestion(questionMapper.questionPatchDtoToQuestion(patch));
        QuestionDto.Response response = questionMapper.questionToQuestionResponseDto(question);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") long questionId) {
        //TODO: 지워진 질문은 검색이 불가.
        Question question = questionServiceImpl.findQuestion(questionId);
        QuestionDto.Response response = questionMapper.questionToQuestionResponseDto(question);

        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getQuestions(Pageable pageable) {
        //TODO: 지워진 질문은 검색이 불가.
        Page<Question> pageQuestions = questionServiceImpl.findQuestions(pageable);
        List<Question> questions = pageQuestions.getContent();
        List<QuestionDto.Response> responses = questionMapper.questionsToQuestionResponseDtos(questions);
//        for (QuestionDto.Response response : responses) {
//            Question question = questionService.findQuestion(response.getQuestionId());
//            response.setUserDto(userMapper.userToResponse(question.getUser()));
//        }
//        for (QuestionDto.Response response : responses) {
//            Question question = questionService.findQuestion(response.getQuestionId());
//            response.setTagNames(question.getTags().stream().map(Tag::getName).collect(Collectors.toList()));
//        }
        return new ResponseEntity<>(new MultiResponseDto<>(responses,pageQuestions),HttpStatus.OK);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable long questionId) {
        questionServiceImpl.deleteQuestion(questionId);
        //TODO::접속된 user랑 글 작성자가 같은지 확인
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{question-id}/add")
    public ResponseEntity postAnswer(@PathVariable("question-id") Long questionId,
                                     @RequestBody AnswerDto.Post postDto) {
        //TODO: userId Authentication 에서 받아올 것.
        postDto.setQuestionId(questionId);
        Answer findAnswer = answerService.createAnswer(answerMapper.answerPostDtoToAnswer(postDto));
        URI location = UriCreator.createUri(QUESTION_DEFAULT_URL + "/" + questionId + "/add", findAnswer.getAnswerId());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{question-id}/answers")
    public ResponseEntity getAnswersByQuestionId(@PathVariable("question-id") Long questionId) {
        List<Answer> answerByQuestion = answerService.findAnswersByQuestion(questionId);
        List<AnswerDto.Response> responses = answerMapper. answersToAnswerResponseDtos(answerByQuestion);

        return new ResponseEntity(new SingleResponseDto<>(responses), HttpStatus.OK);
    }
}
