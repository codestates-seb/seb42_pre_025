package com.stackoverflow.team25.tag.mapper;

import com.stackoverflow.team25.tag.dto.TagDto;
import com.stackoverflow.team25.tag.entity.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-26T23:00:33+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public List<TagDto.Response> tagsToResponseDTos(List<Tag> tags) {
        if ( tags == null ) {
            return null;
        }

        List<TagDto.Response> list = new ArrayList<TagDto.Response>( tags.size() );
        for ( Tag tag : tags ) {
            list.add( tagToResponse( tag ) );
        }

        return list;
    }

    protected TagDto.Response tagToResponse(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto.Response.ResponseBuilder response = TagDto.Response.builder();

        return response.build();
    }
}
