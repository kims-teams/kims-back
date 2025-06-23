package org.kt.finalproject.domain.post.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponse {
    private Integer id;
    private String title;
    private String content;
    private String writerName;
    private String writerId;
    private String createdAt;
    private String updatedAt;
    private Boolean deleted;

    @Builder
    public PostResponse(Integer id, String title, String content, String writerName, String writerId, String createdAt, String updatedAt, Boolean deleted) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerName = writerName;
        this.writerId = writerId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

}

