package org.kt.finalproject.domain.post.request;


import lombok.Getter;
import lombok.Setter;
import org.kt.finalproject.domain.post.entity.PostCategory;

@Setter
@Getter
public class PostCreateRequest {

    private String title;
    private String content;
    private Integer userId;
    private String categoryName;
}
