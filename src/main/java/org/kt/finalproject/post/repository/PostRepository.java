package org.kt.finalproject.post.repository;

import org.kt.finalproject.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
