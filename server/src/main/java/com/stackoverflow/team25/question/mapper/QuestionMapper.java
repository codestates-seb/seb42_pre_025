package com.stackoverflow.team25.question.mapper;

import com.stackoverflow.team25.answer.mapper.AnswerMapper;
import com.stackoverflow.team25.question.dto.QuestionDto;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.tag.dto.TagDto;
import com.stackoverflow.team25.tag.entity.QuestionTag;
import com.stackoverflow.team25.tag.entity.Tag;
import com.stackoverflow.team25.tag.mapper.TagMapper;
import com.stackoverflow.team25.user.entity.User;
import com.stackoverflow.team25.user.mapper.UserMapper;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {AnswerMapper.class, TagMapper.class, UserMapper.class})
public interface QuestionMapper {
    QuestionDto.Response questionToQuestionResponseDto(Question question);

    List<QuestionDto.Response> questionsToQuestionResponseDtos(List<Question> questions);

    default Question questionPostDtoToQuestion(QuestionDto.Post post) {
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

    default Question questionPatchDtoToQuestion(QuestionDto.Patch patch) {
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

    default List<QuestionTag> stringListToTagList(List<String> tagNames) {
        return tagNames.stream()
                .map(Tag::new)
                .map(tag -> {
                    QuestionTag questionTag = new QuestionTag();
                    questionTag.setTag(tag);
                    return questionTag;
                }).collect(Collectors.toList());
    }
    default TagDto.Response tagToResponseDtoWithTagIdAndName(Tag tag) {
        return TagDto.Response.builder()
                .tagId(tag.getTagId())
                .name(tag.getName())
                .build();
    }
    List<Tag> responseDtoToTag (List < TagDto.Response > responses);
}