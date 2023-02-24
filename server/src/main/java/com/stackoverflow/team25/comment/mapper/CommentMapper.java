package com.stackoverflow.team25.comment.mapper;

import com.stackoverflow.team25.comment.dto.CommentDto;
import com.stackoverflow.team25.comment.entity.Comment;
import com.stackoverflow.team25.user.mapper.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface CommentMapper {
    @Mapping(source = "postId", target = "post.postId")
    @Mapping(source = "userId", target = "owner.userId")
    Comment postDtoToComment(CommentDto.POST post);

    Comment patchDtoToComment(CommentDto.PATCH patch);
    @Mapping(source = "post.postId", target = "postId")
    CommentDto.Response commentToResponseDto(Comment comment);

    List<CommentDto.Response> commentsToResponseDtos(List<Comment> allComments);
}
