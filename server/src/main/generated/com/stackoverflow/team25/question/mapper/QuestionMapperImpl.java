package com.stackoverflow.team25.question.mapper;

import com.stackoverflow.team25.answer.mapper.AnswerMapper;
import com.stackoverflow.team25.question.dto.QuestionDto;
import com.stackoverflow.team25.question.entity.Question;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-26T22:30:40+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public Question questionPostDtoToQuestion(QuestionDto.QuestionPostDto questionPostDto) {
        if ( questionPostDto == null ) {
            return null;
        }

        Question.QuestionBuilder question = Question.builder();

        question.questionId( questionPostDto.getQuestionId() );
        question.title( questionPostDto.getTitle() );
        question.content( questionPostDto.getContent() );

        return question.build();
    }

    @Override
    public Question questionPatchDtoToQuestion(QuestionDto.QuestionPatchDto questionPatchDto) {
        if ( questionPatchDto == null ) {
            return null;
        }

        Question.QuestionBuilder question = Question.builder();

        question.questionId( questionPatchDto.getQuestionId() );
        question.title( questionPatchDto.getTitle() );
        question.content( questionPatchDto.getContent() );

        return question.build();
    }

    @Override
    public QuestionDto.QuestionResponseDto questionToQuestionResponseDto(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto.QuestionResponseDto.QuestionResponseDtoBuilder questionResponseDto = QuestionDto.QuestionResponseDto.builder();

        questionResponseDto.questionId( question.getQuestionId() );
        questionResponseDto.answerCount( question.getAnswerCount() );
        questionResponseDto.title( question.getTitle() );
        questionResponseDto.content( question.getContent() );
        questionResponseDto.answers( answerMapper.answersToAnswerResponseDtos( question.getAnswers() ) );

        return questionResponseDto.build();
    }

    @Override
    public List<QuestionDto.QuestionResponseDto> questionsToQuestionResponseDtos(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionDto.QuestionResponseDto> list = new ArrayList<QuestionDto.QuestionResponseDto>( questions.size() );
        for ( Question question : questions ) {
            list.add( questionToQuestionResponseDto( question ) );
        }

        return list;
    }
}
