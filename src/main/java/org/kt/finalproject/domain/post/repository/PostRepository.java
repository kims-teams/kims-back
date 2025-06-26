package org.kt.finalproject.domain.post.repository;

import org.kt.finalproject.domain.post.entity.Post;
import org.kt.finalproject.domain.post.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByCategory(PostCategory category);
}
