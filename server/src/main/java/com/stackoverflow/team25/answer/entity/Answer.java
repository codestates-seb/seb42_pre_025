package com.stackoverflow.team25.answer.entity;

import com.stackoverflow.team25.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Answer extends Auditable {
    @Id
    @GeneratedValue
    private Long answerId;

    //Todo: 사용자 객체
//    @Column(nullable = false)
//    private Owner owner;

    //Todo: 외래키
//    @ManyToOne
//    @JoinColumn(name = "questionId")
//    private Question question;
    private Long score;
    @Column(name = "isAccepted")
    private Boolean isAccepted;

    @Lob // Large Object
    @Column(nullable = false)
    private String content;

}
