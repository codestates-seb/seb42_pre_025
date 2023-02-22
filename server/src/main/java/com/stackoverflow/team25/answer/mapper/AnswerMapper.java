package com.stackoverflow.team25.answer.mapper;

import com.stackoverflow.team25.answer.dto.AnswerDto;
import com.stackoverflow.team25.answer.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper {
    Answer answerPostDtoToAnswer(AnswerDto.Post requestBody);
    Answer answerPatchDtoToAnswer(AnswerDto.Patch requestBody);
    @Mapping(source = "question.questionId", target = "questionId")
    AnswerDto.Response answerToAnswerResponseDto(Answer answer);
    List<AnswerDto.Response> answersToAnswerResponseDtos(List<Answer> answers);
}
