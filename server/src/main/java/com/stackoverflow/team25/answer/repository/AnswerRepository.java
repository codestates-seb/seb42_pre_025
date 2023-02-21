package com.stackoverflow.team25.answer.repository;


import com.stackoverflow.team25.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
