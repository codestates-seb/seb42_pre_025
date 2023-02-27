package com.stackoverflow.team25.comment.controller;

import com.stackoverflow.team25.comment.dto.CommentDto;
import com.stackoverflow.team25.comment.entity.Comment;
import com.stackoverflow.team25.comment.mapper.CommentMapper;
import com.stackoverflow.team25.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentMapper commentMapper;
    private final CommentService commentService;
    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("comment-id") Long commentId,
                                       @RequestBody CommentDto.PATCH patchDto){
        patchDto.setCommentId(commentId);
        Comment comment = commentMapper.patchDtoToComment(patchDto);
        Comment findComment = commentService.updateComment(comment);
        CommentDto.Response response = commentMapper.commentToResponseDto(findComment);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/{comment-id}")
    public ResponseEntity getComment(@PathVariable("comment-id") Long commentId){
        Comment findComment = commentService.findComment(commentId);
        CommentDto.Response response = commentMapper.commentToResponseDto(findComment);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getComments(){
        List<Comment> allComments = commentService.findAllComment();
        List<CommentDto.Response> responses = commentMapper.commentsToResponseDtos(allComments);

        return new ResponseEntity(responses, HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("comment-id") Long commentId) {
        commentService.removeComment(commentId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
