package org.kt.finalproject.domain.post.repository;

import org.kt.finalproject.domain.post.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByPostId(Integer postId);
}
