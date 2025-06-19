package org.kt.finalproject.post.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostUpdateRequest {
    private String tilte;
    private String content;
    private Integer userId;
}
