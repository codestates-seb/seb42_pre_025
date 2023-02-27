package com.stackoverflow.team25.answer.mapper;

import com.stackoverflow.team25.answer.dto.AnswerDto;
import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.user.entity.User;
import com.stackoverflow.team25.user.mapper.UserMapper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-26T21:00:43+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Answer answerPostDtoToAnswer(AnswerDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setOwner( postToUser( requestBody ) );
        answer.setQuestion( postToQuestion( requestBody ) );
        answer.setScore( requestBody.getScore() );
        answer.setIsAccepted( requestBody.getIsAccepted() );
        answer.setContent( requestBody.getContent() );

        return answer;
    }

    @Override
    public Answer answerPatchDtoToAnswer(AnswerDto.Patch requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setAnswerId( requestBody.getAnswerId() );
        answer.setContent( requestBody.getContent() );

        return answer;
    }

    @Override
    public AnswerDto.Response answerToAnswerResponseDto(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto.Response.ResponseBuilder response = AnswerDto.Response.builder();

        response.questionId( answerQuestionQuestionId( answer ) );
        response.answerId( answer.getAnswerId() );
        response.owner( userMapper.userToResponse( answer.getOwner() ) );
        response.score( answer.getScore() );
        response.isAccepted( answer.getIsAccepted() );
        response.content( answer.getContent() );
        response.createdAt( answer.getCreatedAt() );
        response.modifiedAt( answer.getModifiedAt() );

        return response.build();
    }

    @Override
    public List<AnswerDto.Response> answersToAnswerResponseDtos(List<Answer> answers) {
        if ( answers == null ) {
            return null;
        }

        List<AnswerDto.Response> list = new ArrayList<AnswerDto.Response>( answers.size() );
        for ( Answer answer : answers ) {
            list.add( answerToAnswerResponseDto( answer ) );
        }

        return list;
    }

    protected User postToUser(AnswerDto.Post post) {
        if ( post == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userId( post.getUserId() );

        return user.build();
    }

    protected Question postToQuestion(AnswerDto.Post post) {
        if ( post == null ) {
            return null;
        }

        Question.QuestionBuilder question = Question.builder();

        question.questionId( post.getQuestionId() );

        return question.build();
    }

    private Long answerQuestionQuestionId(Answer answer) {
        if ( answer == null ) {
            return null;
        }
        Question question = answer.getQuestion();
        if ( question == null ) {
            return null;
        }
        Long questionId = question.getQuestionId();
        if ( questionId == null ) {
            return null;
        }
        return questionId;
    }
}
