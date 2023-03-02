package com.stackoverflow.team25.tag.repository;


import com.stackoverflow.team25.tag.entity.QuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTagRepository extends JpaRepository<QuestionTag, Long> {
}
