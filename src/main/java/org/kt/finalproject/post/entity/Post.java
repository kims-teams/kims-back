package org.kt.finalproject.post.entity;


import jakarta.persistence.*;
import lombok.*;
import org.kt.finalproject.user.entity.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "post")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // User와 연관관계 (작성자)
    @ManyToOne(fetch = FetchType.LAZY)  // 게시글 여러개가 한 명의 작성자(User)에 연결
    @JoinColumn(name = "user_id")   // DB의 user_id 컬럼과 연결됨
    private User user;  // 자바에서 이 필드는 '작성자 객체(User)' 로 사용

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(name = "created_at" ,nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private boolean deleted;
}
