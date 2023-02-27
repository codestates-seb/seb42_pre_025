package com.stackoverflow.team25.post.service;

import com.stackoverflow.team25.exception.BusinessLogicException;
import com.stackoverflow.team25.exception.ExceptionCode;
import com.stackoverflow.team25.post.entity.Post;

import com.stackoverflow.team25.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;

    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public Post findPost(Long postId){
        Optional<Post> optionalPost = postRepository.findById(postId);
        Post findPost = optionalPost.orElseThrow(() -> new RuntimeException("잘못된 Post ID 입니다."));
        return findPost;
    }

    public Page<Post> findPosts(Pageable pageable){
        PageRequest of = PageRequest.of(pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                pageable.getSort());

        return postRepository.findAll(of);
    }
}
