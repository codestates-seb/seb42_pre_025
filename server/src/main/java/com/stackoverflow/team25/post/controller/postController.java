package com.stackoverflow.team25.post.controller;

import com.stackoverflow.team25.comment.dto.CommentDto;
import com.stackoverflow.team25.comment.entity.Comment;
import com.stackoverflow.team25.comment.mapper.CommentMapper;
import com.stackoverflow.team25.comment.service.CommentService;
import com.stackoverflow.team25.post.entity.Post;
import com.stackoverflow.team25.post.service.PostService;
import com.stackoverflow.team25.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping
public class postController {
    private final static String POST_DEFAULT_URL = "/posts";
    private final PostService postService;
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    @GetMapping("/posts")
    public ResponseEntity getPosts(Pageable pageable){
        Page<Post> posts = postService.findPosts(pageable);
        List<Post> content = posts.getContent();

        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    @PostMapping("/posts/{post-id}/comments/add")
    public ResponseEntity postComment(@PathVariable("post-id") Long postId,
                                      @RequestBody CommentDto.POST commentDto){
        commentDto.setPostId(postId);
        Comment comment = commentMapper.postDtoToComment(commentDto);
        Comment findComment = commentService.createComment(comment);

//        UriCreator.createUri(POST_DEFAULT_URL +"/" + postId + "/addComment", findComment.get)
        URI location = UriCreator.createCommentUri(POST_DEFAULT_URL, findComment.getPost().getPostId());
        return ResponseEntity.created(location).build();
    }

//    //Todo: PostId를 통해 댓글을 조회해오는 메서드
//    @GetMapping("/posts/{post-id}/comments")
//    public ResponseEntity getComments(@PathVariable("post-id") Long postId){
//        List<Comment> commentsByPost = commentService.findCommentsByPost(postId);
//        return
//    }
}
