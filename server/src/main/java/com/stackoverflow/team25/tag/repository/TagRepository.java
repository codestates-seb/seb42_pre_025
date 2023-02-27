package com.stackoverflow.team25.tag.repository;

import com.stackoverflow.team25.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository <Tag, Long>{
    Optional<Tag> findByName(String Name);
    List<Tag> findAllByNameIn(List<String> Names);
}
