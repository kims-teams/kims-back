package org.kt.finalproject.domain.post.controller;


import lombok.RequiredArgsConstructor;
import org.kt.finalproject.domain.post.entity.PostCategory;
import org.kt.finalproject.domain.post.repository.PostCategoryRepository;
import org.kt.finalproject.domain.post.request.PostCreateRequest;
import org.kt.finalproject.domain.post.request.PostUpdateRequest;
import org.kt.finalproject.domain.post.response.PostResponse;
import org.kt.finalproject.domain.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostCategoryRepository postCategoryRepository;

    // 게시글 등록
    @PostMapping
    public PostResponse createPost(@RequestParam("email") String subject, @RequestBody PostCreateRequest request) {
        return postService.createPost(subject, request);
    }

    // 게시글 전체 목록 조회
    @GetMapping
    public List<PostResponse> getAllPost() {
        return postService.getAllPost();
    }

    // 게시글 상세 조회
    @GetMapping("/{id}")
    public PostResponse getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

    // 게시글 수정
    @PutMapping("/{id}")
    public PostResponse updatePost(@PathVariable Integer id, @RequestBody PostUpdateRequest request) {
        return postService.updatePost(id, request);
    }

    // 게시글 삭제
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Integer id) {
        postService.deletePost(id);
    }

    @GetMapping("/category")
    public ResponseEntity<List<PostCategory>> getAllCategory(){
        return ResponseEntity.ok(postCategoryRepository.findAll());
    }
}
