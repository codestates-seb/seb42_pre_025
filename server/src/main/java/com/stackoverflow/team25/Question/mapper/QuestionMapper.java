package com.stackoverflow.team25.Question.mapper;

import com.stackoverflow.team25.Question.dto.QuestionPatchDto;
import com.stackoverflow.team25.Question.dto.QuestionResponseDto;
import com.stackoverflow.team25.Question.entity.Question;
import org.mapstruct.Mapper;

import javax.validation.Valid;
import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    Question questionPostDtoToQuestion(@Valid QuestionPatchDto questionPostDto);
    Question questionPatchDtoToQuestion(QuestionPatchDto questionPatchDto);
    QuestionResponseDto questionToQuestionResponseDto(Question question);
    List<QuestionResponseDto> questionsToQuestionResponseDtos(List<Question> questions);
}