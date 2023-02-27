package com.stackoverflow.team25.tag.service;
import com.stackoverflow.team25.question.repository.QuestionRepository;
import com.stackoverflow.team25.tag.entity.Tag;
import com.stackoverflow.team25.tag.repository.TagRepository;
import com.stackoverflow.team25.user.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class TagService {
    private final TagRepository tagRepository;
    public TagService(TagRepository tagRepository)  {
        this.tagRepository = tagRepository;
    }

    public void createTag(String tagName){
        Tag tag = new Tag();
        tag.setName(tagName);
        tagRepository.save(tag);
    }
}
