package org.kt.finalproject.domain.post.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateRequest {
    private String content;
    private Integer postId;
    private Integer userId;
}
