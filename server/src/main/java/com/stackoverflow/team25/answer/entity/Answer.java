package com.stackoverflow.team25.answer.entity;

import com.stackoverflow.team25.audit.Auditable;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.user.entity.User;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;
//    @ManyToOne
//    @JoinColumn(name = "user")
//    private User user;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    private Long score;
    private Boolean isAccepted;
    @Lob // Large Object
    @Column(nullable = false)
    private String content;

    // 편의 메서드를 setter로 이름 지을시 Mapstruct에서 문제 발생!!
    public void addQuestion(Question question){
        this.question = question;
        if(!this.question.getAnswers().contains(this)){
            this.question.addAnswer(this);
        }
    }

//    public void addUser(User user){
//        this.user = user;
//        if(!this.user.getAnswers().contains(this)){
//            this.user.addAnswer(this);
//        }
//    }
}
