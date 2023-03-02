package com.stackoverflow.team25.tag.service;
import com.stackoverflow.team25.tag.entity.Tag;
import com.stackoverflow.team25.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;


    public void createTag(String tagName){
        Tag tag = Tag.builder().name(tagName).build();
        tagRepository.save(tag);
    }

    public Tag verifyTag(String tagName) {
        Optional<Tag> optionalTag = tagRepository.findByName(tagName);
        return optionalTag.orElseGet(() -> returnTag(tagName));
    }

    public Tag returnTag(String tagName){
        Tag tag = new Tag();
        tag.setName(tagName);
        return tagRepository.save(tag);
    }
}
