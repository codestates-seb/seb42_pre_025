package com.stackoverflow.team25.answer.controller;

import com.stackoverflow.team25.answer.dto.AnswerDto;
import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.answer.mapper.AnswerMapper;
import com.stackoverflow.team25.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;
    @PostMapping
    public ResponseEntity postAnswer(@RequestBody AnswerDto.Post postDto){
        Answer findAnswer = answerService.createAnswer(answerMapper.answerPostDtoToAnswer(postDto));
        return new ResponseEntity(answerMapper.answerToAnswerResponseDto(findAnswer), HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id") long answerId,
                                      @RequestBody AnswerDto.Patch patchDto){
        Answer answer = answerMapper.answerPatchDtoToAnswer(patchDto);
        answer.setAnswerId(answerId);
        Answer updatedAnswer = answerService.updateAnswer(answer);
        return new ResponseEntity(answerMapper.answerToAnswerResponseDto(updatedAnswer), HttpStatus.OK);
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(@PathVariable("answer-id") long answerId){
        Answer findAnswer = answerService.findAnswer(answerId);
        return new ResponseEntity(answerMapper.answerToAnswerResponseDto(findAnswer), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAnswers(){
        List<Answer> findAnswers = answerService.findAnswers();
        return new ResponseEntity(answerMapper.answersToAnswerResponseDtos(findAnswers), HttpStatus.OK);
    }

    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@PathVariable("answer-id") long answerId){
        answerService.removeAnswer(answerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
