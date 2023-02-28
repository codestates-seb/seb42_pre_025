package com.stackoverflow.team25.tag.entity;

import com.stackoverflow.team25.audit.Auditable;
import com.stackoverflow.team25.question.entity.Question;
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
public class Tag extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    private String name;
    @OneToMany(mappedBy="tag")
    private List<QuestionTag> questionTags = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }
}
