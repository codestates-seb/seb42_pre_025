package com.stackoverflow.team25.post.service;

import com.stackoverflow.team25.post.entity.Post;

import com.stackoverflow.team25.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;

    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public Page<Post> findPosts(Pageable pageable){
        PageRequest of = PageRequest.of(pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                pageable.getSort());

        return postRepository.findAll(of);
    }
}
