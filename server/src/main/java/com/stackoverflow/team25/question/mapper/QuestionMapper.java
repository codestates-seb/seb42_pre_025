package com.stackoverflow.team25.question.mapper;

import com.stackoverflow.team25.answer.mapper.AnswerMapper;
import com.stackoverflow.team25.question.dto.QuestionDto;
import com.stackoverflow.team25.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = AnswerMapper.class)
public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionDto.QuestionPostDto questionPostDto);
    Question questionPatchDtoToQuestion(QuestionDto.QuestionPatchDto questionPatchDto);
//    @Mapping(target = "tagNames", expression = "java(tags.stream().foreach.)")
//    @Mapping(source = "user", target = "userDto")
    QuestionDto.QuestionResponseDto questionToQuestionResponseDto(Question question);
    List<QuestionDto.QuestionResponseDto> questionsToQuestionResponseDtos(List<Question> questions);
}