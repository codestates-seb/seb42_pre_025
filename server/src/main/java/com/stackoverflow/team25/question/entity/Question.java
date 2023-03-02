package com.stackoverflow.team25.question.entity;

import com.stackoverflow.team25.answer.entity.Answer;
import com.stackoverflow.team25.audit.Auditable;
import com.stackoverflow.team25.tag.entity.QuestionTag;
import com.stackoverflow.team25.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.stackoverflow.team25.question.entity.Question.QuestionType.*;
import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question extends Auditable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long questionId;
    private Integer answerCount;
    @Column(nullable = false)
    private String title;
    @Lob
    @Column(nullable = false)
    private String content;
    @OneToMany(mappedBy = "question", cascade = ALL, fetch = EAGER)
    private List<Answer> answers = new ArrayList<>();
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "question", cascade = ALL, orphanRemoval = true, fetch = LAZY)
    private List<QuestionTag> questionTags;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private QuestionType questionType = ACTIVATE;

    public void addAnswer(Answer answer) {
        answers.add(answer);
        if (answer.getQuestion() != this) {
            answer.addQuestion(this);
        }
    }

    public enum QuestionType {
        ACTIVATE, DELETED
    }
}
