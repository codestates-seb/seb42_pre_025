package com.stackoverflow.team25.question.mapper;

import com.stackoverflow.team25.question.dto.QuestionDto;
import com.stackoverflow.team25.question.entity.Question;
import org.mapstruct.Mapper;

import javax.validation.Valid;
import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionDto.QuestionPostDto questionPostDto);
    Question questionPatchDtoToQuestion(QuestionDto.QuestionPatchDto questionPatchDto);
    QuestionDto.QuestionResponseDto questionToQuestionResponseDto(Question question);
    List<QuestionDto.QuestionResponseDto> questionsToQuestionResponseDtos(List<Question> questions);
}