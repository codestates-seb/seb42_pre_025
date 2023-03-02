//package com.stackoverflow.team25.comment.entity;
//
//import com.stackoverflow.team25.audit.Auditable;
//import com.stackoverflow.team25.post.entity.Post;
//import com.stackoverflow.team25.user.entity.User;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@NoArgsConstructor
//@Setter
//@Getter
//public class Comment extends Auditable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long commentId;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User owner;
//    @ManyToOne
//    @JoinColumn(name = "post_id")
//    private Post post;
//    private Boolean isEdited;
//    @Column(nullable = false)
//    private String text;
//}
