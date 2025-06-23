package org.kt.finalproject.domain.post.response;

import lombok.Builder;
import lombok.Getter;
import org.kt.finalproject.domain.post.entity.PostCategory;

@Getter
public class PostResponse {
    private final Integer id;
    private final String title;
    private final String content;
    private final String writerName;
    private final String writerId;
    private final String createdAt;
    private final String updatedAt;
    private final Boolean deleted;
    private final String categoryName;

    @Builder
    public PostResponse(Integer id, String title, String content, String writerName, String writerId, String createdAt, String updatedAt, Boolean deleted, String categoryName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerName = writerName;
        this.writerId = writerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
        this.categoryName = categoryName;
    }

}

