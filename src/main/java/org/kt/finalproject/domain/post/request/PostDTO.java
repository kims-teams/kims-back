package org.kt.finalproject.domain.post.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Integer id;
    private String title;
    private String content;
    private Integer writerId;
    private String writerName;
    private String writerEmail;
    private String categoryName;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
