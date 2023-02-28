package com.stackoverflow.team25.tag.mapper;

import com.stackoverflow.team25.question.mapper.QuestionMapper;
import com.stackoverflow.team25.tag.dto.TagDto;
import com.stackoverflow.team25.tag.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = QuestionMapper.class)
public interface TagMapper {

    Tag tagPostDtoToTag(TagDto.Post tagPostDto);

    List<TagDto.Response> tagsToResponseDTos(List<Tag> tags);
}
