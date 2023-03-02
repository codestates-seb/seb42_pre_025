//package com.stackoverflow.team25.post.entity;
//
//import com.stackoverflow.team25.answer.entity.Answer;
//import com.stackoverflow.team25.audit.Auditable;
//import com.stackoverflow.team25.question.entity.Question;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//
//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//public class Post extends Auditable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long postId;
//    private String postType;
//    @OneToOne
//    @JoinColumn(name = "question_id")
//    private Question question;
//    @OneToOne
//    @JoinColumn(name = "answer_id")
//    private Answer answer;
//    private Integer down_vote_count;
//    private Integer up_vote_count;
//    private Integer score;
//}
