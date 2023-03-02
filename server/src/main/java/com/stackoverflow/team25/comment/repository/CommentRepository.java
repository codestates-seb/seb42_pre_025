//package com.stackoverflow.team25.comment.repository;
//
//import com.stackoverflow.team25.comment.entity.Comment;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
//public interface CommentRepository extends JpaRepository<Comment,Long> {
//    @Query(value = "SELECT c FROM Comment c WHERE c.post.postId = :postId")
//    List<Comment> findAllByPostId(Long postId);
//}
