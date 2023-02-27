package com.stackoverflow.team25.question.entity;

import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.audit.Auditable;
import com.stackoverflow.team25.post.entity.Post;
import com.stackoverflow.team25.tag.entity.Tag;
import com.stackoverflow.team25.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question extends Auditable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private Integer answerCount;
    @Column(nullable = false)
    private String title;
    @Lob
    @Column(nullable = false)
    private String content;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answers = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Post post_q;
    @ManyToMany
    @JoinTable(name = "Question_Tag")
    private List<Tag> tags;


    public void addAnswer(Answer answer){
        answers.add(answer);
        if(answer.getQuestion() != this){
            answer.addQuestion(this);
        }
    }
    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getQuestions().remove(this);
    }
}
