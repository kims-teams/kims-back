package org.kt.finalproject.domain.post.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostUpdateRequest {
    private String title;
    private String content;
    private Integer userId;
}
