package org.kt.finalproject.post.service;


import lombok.RequiredArgsConstructor;
import org.kt.finalproject.post.entity.Post;
import org.kt.finalproject.post.repository.PostRepository;
import org.kt.finalproject.post.request.PostCreateRequest;
import org.kt.finalproject.post.request.PostUpdateRequest;
import org.kt.finalproject.post.response.PostResponse;
import org.kt.finalproject.user.entity.User;
import org.kt.finalproject.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

//============================= 엔티티 직통, 재사용, 내부 호출, 테스트 용도 =============================
    public Post save(Post post) {
        return postRepository.save(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Optional<Post> findById(Integer id) {
        return postRepository.findById(id);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }
//================================================================================================

    public PostResponse createPost(PostCreateRequest request) {
        // 작성자 찾기 (없으면 예외)
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("작성자 없음"));

        // 엔티티 생성
        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .deleted(false)
                .createdAt(LocalDateTime.now())
                .build();
        Post saved = postRepository.save(post);

        // 매핑 (toPostResponse 분리 추천)
        return toPostResponse(saved);
    }

    public List<PostResponse> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().filter(post -> !post.isDeleted()).map(this::toPostResponse).collect(Collectors.toList());
    }

    public PostResponse getPostById(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
        return toPostResponse(post);
    }

    public PostResponse updatePost(Integer id, PostUpdateRequest request) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUpdatedAt(LocalDateTime.now());

        // 저장 (JPA는 트랜잭션 안에 있으면 save 없어도 flush됨, 그래도 명시적으로 써주면 좋음)
        Post saved = postRepository.save(post);

        return toPostResponse(saved);
    }

    public void deletePost(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
            post.setDeleted(true);
            post.setUpdatedAt(LocalDateTime.now());
            postRepository.save(post);
    }

    public PostResponse toPostResponse(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writerName(post.getUser() != null ? post.getUser().getName() : null)
                .writerId(post.getUser() != null ? post.getUser().getId().toString() : null)
                .createdAt(LocalDateTime.now().toString())
                .updatedAt(post.getUpdatedAt() != null ? post.getUpdatedAt().toString() : null)
                .deleted(post.isDeleted())
                .build();
    }

}
