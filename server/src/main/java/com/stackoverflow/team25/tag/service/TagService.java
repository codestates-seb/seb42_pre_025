package com.stackoverflow.team25.tag.service;
import com.stackoverflow.team25.exception.BusinessLogicException;
import com.stackoverflow.team25.exception.ExceptionCode;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.question.service.QuestionService;
import com.stackoverflow.team25.tag.entity.Tag;
import com.stackoverflow.team25.tag.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public void createTag(String tagName){
        Tag tag = new Tag();
        tag.setName(tagName);
        tagRepository.save(tag);
    }


    @Nullable
    public void patchTag(List<Tag> patchTags, Question patchQuestion) {
        if (patchTags!=null) {
            patchTags.stream().forEach(tag -> {

                if (!tagRepository.existsByName(tag.getName())) {
                    createTag(tag.getName());
                    tag.getQuestions().add(patchQuestion);
                }
            });

        }
    }

    public Tag findTagByName(String name) {
        Optional<Tag> optionalTag = tagRepository.findByName(name);
        Tag findTagByName =
                optionalTag.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));

        return findTagByName;
    }


}
