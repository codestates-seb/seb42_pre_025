package com.stackoverflow.team25.question.mapper;

import com.stackoverflow.team25.answer.mapper.AnswerMapper;
import com.stackoverflow.team25.question.dto.QuestionDto;
import com.stackoverflow.team25.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.validation.Valid;
import java.util.List;

@Mapper(componentModel = "spring",uses = AnswerMapper.class)
public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionDto.QuestionPostDto questionPostDto);
    Question questionPatchDtoToQuestion(QuestionDto.QuestionPatchDto questionPatchDto);
    QuestionDto.QuestionResponseDto questionToQuestionResponseDto(Question question);
    List<QuestionDto.QuestionResponseDto> questionsToQuestionResponseDtos(List<Question> questions);
    List<QuestionDto.ResponseForTag> questionsToResponseDtosForTag(List<Question> questions);
}