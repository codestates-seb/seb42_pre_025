package com.stackoverflow.team25.answer.mapper;

import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.user.dto.UserDto;
import com.stackoverflow.team25.user.entity.User;
import com.stackoverflow.team25.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.stackoverflow.team25.answer.dto.AnswerDto.*;

@Component
@RequiredArgsConstructor
public class AnswerMapper {
    private final UserMapper userMapper;

    public Answer answerPostDtoToAnswer(Post post) {
        return Answer.builder()
                .owner(User.builder().userId(post.getUserId()).build())
                .score(post.getScore())
                .question(Question.builder().questionId(post.getQuestionId()).build())
                .isAccepted(post.getIsAccepted())
                .content(post.getContent())
                .build();
    }


    public Response answerToAnswerResponseDto(Answer answer) {
        UserDto.Response userResponse = userMapper.userToUserResponseDto(answer.getOwner());

        return Response.builder()
                .answerId(answer.getAnswerId())
                .owner(userResponse)
                .questionId(answer.getQuestion().getQuestionId())
                .score(answer.getScore())
                .isAccepted(answer.getIsAccepted())
                .content(answer.getContent())
                .createdAt(answer.getCreatedAt())
                .modifiedAt(answer.getModifiedAt())
                .build();
    }

    public List<Response> answersToAnswerResponseDtos(List<Answer> answers) {
        return answers.stream().map(Response::new).collect(Collectors.toList());
    }
    public Answer answerPatchDtoToAnswer(Patch patchDto) {
        return Answer.builder()
                .answerId(patchDto.getAnswerId())
                .owner(User.builder().userId(patchDto.getUserId()).build())
                .content(patchDto.getContent())
                .build();
    }
}
