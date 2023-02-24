package com.stackoverflow.team25.comment.service;

import com.stackoverflow.team25.comment.entity.Comment;
import com.stackoverflow.team25.comment.repository.CommentRepository;
import com.stackoverflow.team25.exception.BusinessLogicException;
import com.stackoverflow.team25.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public Comment createComment(Comment comment){
        /**
         * 초기화
         */
        comment.setIsEdited(false);
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment){
       // 괜찮은 Comment 인지 검증
       Comment verifiedComment = findVerifiedComment(comment.getCommentId());
       Optional.ofNullable(comment.getText())
               .ifPresent(text -> verifiedComment.setText(text));
       verifiedComment.setIsEdited(true);
       return commentRepository.save(verifiedComment);
    }

    public Comment findComment(Long commentId) {
        Comment verifiedComment = findVerifiedComment(commentId);
        return verifiedComment;
    }

    private Comment findVerifiedComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment findComment = optionalComment.orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        return findComment;
    }

    public List<Comment> findCommentsByPost(Long postId) {
        //todo : 커스텀 해야함
        List<Comment> findComments = commentRepository.findAllByPostId(postId);
        return findComments;
    }

    public void removeComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<Comment> findAllComment() {
        return commentRepository.findAll();
    }
}
