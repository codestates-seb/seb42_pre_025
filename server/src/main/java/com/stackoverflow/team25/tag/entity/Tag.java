package com.stackoverflow.team25.tag.entity;

import com.stackoverflow.team25.audit.Auditable;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.Builder.Default;

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
    @Default
    private List<QuestionTag> questionTags = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }
}
