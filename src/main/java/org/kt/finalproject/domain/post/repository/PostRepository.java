package org.kt.finalproject.domain.post.repository;

import org.kt.finalproject.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
