package com.stackoverflow.team25.answer.entity;

import com.stackoverflow.team25.audit.Auditable;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.user.entity.User;
import lombok.*;
import lombok.Builder.Default;

import javax.persistence.*;

import static com.stackoverflow.team25.answer.entity.Answer.AnswerType.ACTIVATE;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Builder
public class Answer extends Auditable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long answerId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    private Long score;
    private Boolean isAccepted;
    @Lob // Large Object
    @Column(nullable = false)
    private String content;
    @Default
    @Enumerated(EnumType.STRING)
    private AnswerType answerType = ACTIVATE;

    // 편의 메서드를 setter로 이름 지을시 Mapstruct에서 문제 발생!!
    public void addQuestion(Question question){
        this.question = question;
        if(!this.question.getAnswers().contains(this)){
            this.question.addAnswer(this);
        }
    }

    public void addUser(User user){
        this.owner = user;
        if(!this.owner.getAnswers().contains(this)){
            this.owner.addAnswer(this);
        }
    }

    public enum AnswerType {
        ACTIVATE, DELETE
    }
}
