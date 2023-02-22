package com.stackoverflow.team25.question.mapper;

import com.stackoverflow.team25.question.dto.QuestionPatchDto;
import com.stackoverflow.team25.question.dto.QuestionResponseDto;
import com.stackoverflow.team25.question.entity.Question;
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