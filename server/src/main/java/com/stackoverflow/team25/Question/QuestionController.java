package com.stackoverflow.team25.Question;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
// 스프링프레임워크 4 버전 이상부터 사용가능한 어노테이션
// @Controller에 @ResponseBody가 결합
@RequestMapping
// HTTP 요청 메서드(GET, POST, PUT, DELETE 등)와 URL 패턴을 지정하여 해당 요청에 대한 처리 메서드를 매핑
@Validated //  데이터 유효성 검사를 위해 사용되는 어노테이션
public class QuestionController {
    @PostMapping// 주어진 URI 표현식과 일치하는 HTTP POST 요청을 처리
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionPostDto questionDto){
       //@Valid 검사 대상 객체에 대한 유효성 검사
       //RequestBody HTTP 요청 본문의 내용을 Java 객체로 변환
        return new ResponseEntity<>(questionDto, HttpStatus.CREATED);
    }



}
