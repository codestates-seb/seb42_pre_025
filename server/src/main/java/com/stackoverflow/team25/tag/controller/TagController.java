package com.stackoverflow.team25.tag.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stackoverflow.team25.dto.MultiResponseDto;
import com.stackoverflow.team25.dto.SingleResponseDto;
import com.stackoverflow.team25.question.dto.QuestionDto;
import com.stackoverflow.team25.question.entity.Question;
import com.stackoverflow.team25.question.mapper.QuestionMapper;
import com.stackoverflow.team25.tag.dto.TagDto;
import com.stackoverflow.team25.tag.entity.Tag;
import com.stackoverflow.team25.tag.mapper.TagMapper;
import com.stackoverflow.team25.tag.repository.TagRepository;
import com.stackoverflow.team25.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tags")
@Validated
@RequiredArgsConstructor
public class TagController {
    private final TagMapper tagMapper;
    private final TagService tagService;
    private final QuestionMapper questionMapper;

    @GetMapping("/{tag-name}")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public ResponseEntity getTag(@PathVariable("tag-name") String name) {
        Tag tag = tagService.findTagByName(name);
        TagDto.Response response = tagMapper.tagToResponseDtoWithTagIdAndName(tag);
        response.setQuestions(questionMapper.questionsToResponseDtosForTag(tag.getQuestions()));
        return new ResponseEntity<>(new SingleResponseDto<>(response),HttpStatus.OK);
    }
}
