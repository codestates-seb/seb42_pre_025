package com.stackoverflow.team25.tag.entity;

import com.stackoverflow.team25.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Getter
@Setter
public class QuestionTag {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long questionTagId;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
