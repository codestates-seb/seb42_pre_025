package com.stackoverflow.team25.question.mapper;

import com.stackoverflow.team25.answer.dto.AnswerDto;
import com.stackoverflow.team25.answer.mapper.AnswerMapper;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.tag.entity.QuestionTag;
import com.stackoverflow.team25.tag.entity.Tag;
import com.stackoverflow.team25.user.dto.UserDto;
import com.stackoverflow.team25.user.entity.User;
import com.stackoverflow.team25.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.stackoverflow.team25.question.dto.QuestionDto.*;

//@Mapper(componentModel = "spring", uses = {AnswerMapper.class, TagMapper.class, UserMapper.class})
@Component
@RequiredArgsConstructor
public class QuestionMapper {
    private final UserMapper userMapper;
    private final AnswerMapper answerMapper;

    public Question questionPostDtoToQuestion(Post post) {
        User user = new User();
        user.setUserId(post.getUserId());
        Question question = new Question();
        question.setTitle(post.getTitle());
        question.setContent(post.getContent());
        question.setUser(user);
        List<QuestionTag> questionTags = post.getTagNames()
                .stream()
                .map(Tag::new)
                .map(tag -> {
                    QuestionTag questionTag = new QuestionTag();
                    questionTag.setQuestion(question);
                    questionTag.setTag(tag);
                    return questionTag;
                }).collect(Collectors.toList());
        question.setQuestionTags(questionTags);
        return question;
    }

    public Question questionPatchDtoToQuestion(Patch patch) {
        User user = new User();
        user.setUserId(patch.getUserId());
        Question question = new Question();
        question.setQuestionId(patch.getQuestionId());
        question.setTitle(patch.getTitle());
        question.setContent(patch.getContent());
        question.setUser(user);
        List<QuestionTag> questionTags = patch.getTagNames()
                .stream()
                .map(Tag::new)
                .map(tag -> {
                    QuestionTag questionTag = new QuestionTag();
                    questionTag.setQuestion(question);
                    questionTag.setTag(tag);
                    return questionTag;
                }).collect(Collectors.toList());
        question.setQuestionTags(questionTags);
        return question;
    }

    public Response questionToQuestionResponseDto(Question question) {
        List<String> tagNames = question.getQuestionTags().stream()
                .map(questionTag -> questionTag.getTag().getName())
                .collect(Collectors.toList());
        List<AnswerDto.Response> answerResponses = answerMapper.answersToAnswerResponseDtos(question.getAnswers());
        UserDto.Response userresponse = userMapper.userToUserResponseDto(question.getUser());

        return Response.builder()
                .questionId(question.getQuestionId())
                .answerCount(question.getAnswerCount())
                .title(question.getTitle())
                .content(question.getContent())
                .answers(answerResponses)
                .owner(userresponse)
                .tagNames(tagNames)
                .questionType(question.getQuestionType())
                .build();
    }

    public List<Response> questionsToQuestionResponseDtos(List<Question> questions) {
        return questions.stream().map(Response::new).collect(Collectors.toList());
    }

}