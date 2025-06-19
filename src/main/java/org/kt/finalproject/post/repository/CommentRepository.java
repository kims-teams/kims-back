package org.kt.finalproject.post.repository;

import org.kt.finalproject.post.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
