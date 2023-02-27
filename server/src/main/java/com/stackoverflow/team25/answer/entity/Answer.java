package com.stackoverflow.team25.answer.entity;

import com.stackoverflow.team25.audit.Auditable;
import com.stackoverflow.team25.post.entity.Post;
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
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToOne(mappedBy = "answer", cascade = CascadeType.REMOVE)
    private Post post_a;

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
}
