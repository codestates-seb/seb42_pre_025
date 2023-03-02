package com.stackoverflow.team25.tag.mapper;

import com.stackoverflow.team25.question.mapper.QuestionMapper;
import com.stackoverflow.team25.tag.dto.TagDto;
import com.stackoverflow.team25.tag.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

//@Mapper(componentModel = "spring")
//public interface TagMapper {
//    Tag tagPostDtoToTag(TagDto.Post tagPostDto);
//
//    TagDto.Response tagToTagResponseDto(Tag tag);
//
//    List<TagDto.Response> tagsToResponseDTos(List<Tag> tags);
//}