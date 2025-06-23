package org.kt.finalproject.post.controller;


import lombok.RequiredArgsConstructor;
import org.kt.finalproject.post.request.CommentCreateRequest;
import org.kt.finalproject.post.request.CommentUpdateRequest;
import org.kt.finalproject.post.response.CommentResponse;
import org.kt.finalproject.post.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 등록
    @PostMapping
    public CommentResponse createComment(@RequestBody CommentCreateRequest request) {
        return commentService.createComment(request);
    }

    // 특정 게시글의 댓글 전체 조회
    @GetMapping("/post/{postId}")
    public List<CommentResponse> getCommentByPostId(@PathVariable Integer postId) {
        return commentService.getCommentByPostId(postId);
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public CommentResponse updateComment(@PathVariable Integer id, @RequestBody CommentUpdateRequest request) {
        return commentService.updateComment(id, request);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }
}
