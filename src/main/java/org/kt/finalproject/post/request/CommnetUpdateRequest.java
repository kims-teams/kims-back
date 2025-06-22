package org.kt.finalproject.post.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommnetUpdateRequest {
    private String content;
    private Integer postId;
    private Integer userId;
}
