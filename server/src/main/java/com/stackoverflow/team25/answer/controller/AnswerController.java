package com.stackoverflow.team25.answer.controller;

import com.stackoverflow.team25.answer.dto.AnswerDto;
import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.answer.mapper.AnswerMapper;
import com.stackoverflow.team25.answer.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    @PatchMapping("/{answerId}")
    public ResponseEntity patchAnswer(@PathVariable long answerId,
                                      @RequestBody AnswerDto.Patch patchDto) {
        Long userId = (Long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        patchDto.setUserId(userId);
        patchDto.setAnswerId(answerId);
        Answer updatedAnswer = answerService.updateAnswer(answerMapper.answerPatchDtoToAnswer(patchDto));
        AnswerDto.Response response = answerMapper.answerToAnswerResponseDto(updatedAnswer);

        return new ResponseEntity(response, OK);
    }

    @GetMapping("/{answerId}")
    public ResponseEntity getAnswer(@PathVariable long answerId) {
        //TODO: 지워진 질문은 검색이 불가.
        Answer findAnswer = answerService.findAnswer(answerId);
        AnswerDto.Response response = answerMapper.answerToAnswerResponseDto(findAnswer);

        return new ResponseEntity(response, OK);
    }

    @GetMapping
    public ResponseEntity getAnswers(Pageable pageable) {
        //TODO: 지워진 질문은 검색이 불가.
        log.info("pageable: {}", pageable.toString());
        Page<Answer> pageAnswers = answerService.findAnswers(pageable);
        List<Answer> answers = pageAnswers.getContent();
        List<AnswerDto.Response> response = answerMapper.answersToAnswerResponseDtos(answers);

        return new ResponseEntity(response, OK);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable @Positive Long answerId) {
        //TODO::접속된 user랑 글 작성자가 같은지 확인
        answerService.removeAnswer(answerId);

        return new ResponseEntity(NO_CONTENT);
    }
}
