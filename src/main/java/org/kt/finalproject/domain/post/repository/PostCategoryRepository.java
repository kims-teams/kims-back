package org.kt.finalproject.domain.post.repository;

import org.kt.finalproject.domain.post.entity.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostCategoryRepository extends JpaRepository<PostCategory, Integer> {

    Optional<PostCategory> findByName(String categoryName);
}
