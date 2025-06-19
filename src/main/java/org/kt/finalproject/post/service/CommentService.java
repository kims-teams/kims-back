package org.kt.finalproject.post.service;

import lombok.RequiredArgsConstructor;
import org.kt.finalproject.post.entity.Comment;
import org.kt.finalproject.post.entity.Post;
import org.kt.finalproject.post.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

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
}
