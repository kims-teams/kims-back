package org.kt.finalproject.domain.post.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentResponse {
    private Integer id;
    private String content;
    private String writerName;
    private String writerId;
    private Integer postId;
    private String createdAt;
    private String updatedAt;
    private Boolean deleted;


    @Builder
    public CommentResponse(Integer id, String content, String writerName, String writerId, Integer postId, String createdAt, String updatedAt, Boolean deleted) {
        this.id = id;
        this.content = content;
        this.writerName = writerName;
        this.writerId = writerId;
        this.postId = postId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }
}
