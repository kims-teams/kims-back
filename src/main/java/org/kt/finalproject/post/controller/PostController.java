package org.kt.finalproject.post.controller;


import lombok.RequiredArgsConstructor;
import org.kt.finalproject.post.request.PostCreateRequest;
import org.kt.finalproject.post.response.PostResponse;
import org.kt.finalproject.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시글 등록
    @PostMapping
    public PostResponse createPost(@RequestBody PostCreateRequest request) {
        return postService.createPost(request);
    }

//    // 게시글 전체 목록 조회
//    @GetMapping
//    public List<PostResponse> getPost() {
//        return postService.
//    }

}
