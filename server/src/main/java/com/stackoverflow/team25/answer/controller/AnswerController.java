package com.stackoverflow.team25.answer.controller;

import com.stackoverflow.team25.answer.dto.AnswerDto;
import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.answer.mapper.AnswerMapper;
import com.stackoverflow.team25.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
@Validated
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") long answerId,
                                      @RequestBody AnswerDto.Patch patchDto){
        Answer answer = answerMapper.answerPatchDtoToAnswer(patchDto);
        answer.setAnswerId(answerId);
        Answer updatedAnswer = answerService.updateAnswer(answer);
        AnswerDto.Response response = answerMapper.answerToAnswerResponseDto(updatedAnswer);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") long answerId){
        Answer findAnswer = answerService.findAnswer(answerId);
        AnswerDto.Response response = answerMapper.answerToAnswerResponseDto(findAnswer);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAnswers(Pageable pageable){
        Page<Answer> pageAnswers = answerService.findAnswers(pageable);
        List<Answer> answers = pageAnswers.getContent();
        List<AnswerDto.Response> response = answerMapper.answersToAnswerResponseDtos(answers);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") @Positive Long answerId){
        answerService.removeAnswer(answerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
