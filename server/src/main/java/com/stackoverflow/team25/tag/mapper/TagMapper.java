package com.stackoverflow.team25.tag.mapper;

import com.stackoverflow.team25.question.mapper.QuestionMapper;
import com.stackoverflow.team25.tag.dto.TagDto;
import com.stackoverflow.team25.tag.entity.Tag;
import com.stackoverflow.team25.tag.repository.TagRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",uses = QuestionMapper.class)
public interface TagMapper {
   // TagDto.Response tagToResponseDto(Tag tag);
    List<TagDto.Response> tagsToResponseDTos(List<Tag> tags);
    @Mapping(target = "questions", ignore = true)
    List<Tag> responseDtoToTag(List<TagDto.Response> responses);

    default List<Tag> stringListToTagList(List<String> tagNames) {
        return tagNames.stream()
                .map(tagName -> {
                    Tag tag = new Tag();
                    tag.setName(tagName);
                    return tag;
                })
                .collect(Collectors.toList());
    }
    default TagDto.Response tagToResponseDtoWithTagIdAndName(Tag tag) {
        return TagDto.Response.builder()
                .tagId(tag.getTagId())
                .name(tag.getName())
                .build();
    }
}
