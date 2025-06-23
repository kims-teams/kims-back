package org.kt.finalproject.post.service;

import lombok.RequiredArgsConstructor;
import org.kt.finalproject.domain.user.entity.User;
import org.kt.finalproject.domain.user.repository.UserRepository;
import org.kt.finalproject.post.entity.Comment;
import org.kt.finalproject.post.entity.Post;
import org.kt.finalproject.post.repository.CommentRepository;
import org.kt.finalproject.post.repository.PostRepository;
import org.kt.finalproject.post.request.CommentCreateRequest;
import org.kt.finalproject.post.request.CommentUpdateRequest;
import org.kt.finalproject.post.response.CommentResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> findByPost(Post post) {
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(Integer id) {
        return commentRepository.findById(id);
    }

    public void deleteById(Integer id) {
        commentRepository.deleteById(id);
    }

    public CommentResponse createComment(CommentCreateRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new IllegalArgumentException("작성자 없음"));

        Post post = postRepository.findById(request.getPostId()).orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .user(user)
                .post(post)
                .deleted(false)
                .createdAt(LocalDateTime.now())
                .build();

        Comment saved = commentRepository.save(comment);
        return toCommentResponse(saved);
    }

    public List<CommentResponse> getCommentByPostId(Integer postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        return comments.stream().filter(comment -> !comment.isDeleted()).map(this::toCommentResponse).toList();
    }

    public CommentResponse updateComment(Integer id, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 없음"));

        comment.setContent(request.getContent());
        comment.setUpdatedAt(LocalDateTime.now());
        Comment saved = commentRepository.save(comment);
        return toCommentResponse(saved);
    }

    public void deleteComment(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글 없음"));
        comment.setDeleted(true);
        comment.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }

    public CommentResponse toCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .writerName(comment.getUser() != null ? comment.getUser().getName() : null)
                .writerId(comment.getUser() != null ? comment.getUser().getId().toString() : null)
                .createdAt(comment.getCreatedAt() != null ? comment.getCreatedAt().toString() : null)
                .updatedAt(comment.getUpdatedAt() != null ? comment.getUpdatedAt().toString() : null)
                .deleted(comment.isDeleted())
                .build();
    }

}
