package com.example.demo.service;

import com.example.demo.dto.CommentResponse;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByPost(Integer postId) { // Integer로 통일
        postRepository.findById(postId) // .longValue() 삭제
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        return commentRepository.findAllByPostPostId(postId).stream() // 메서드명 변경[cite: 8]
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse createComment(Integer postId, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글 없음"));

        // 빌더 에러 방지를 위해 new 키워드 사용[cite: 10]
        Comment comment = new Comment();
        comment.setContent(content); // 엔티티 필드명 Content 대응
        comment.setPost(post);
        comment.setAuthorId(1L);

        return convertToDto(commentRepository.save(comment));
    }

    private CommentResponse convertToDto(Comment comment) {
        // AuthorDto 빌더 에러 방지
        CommentResponse.AuthorDto authorDto = new CommentResponse.AuthorDto();
        authorDto.setUserId(comment.getAuthorId());
        authorDto.setNickname("이정윤");

        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(authorDto)
                .build();
    }

    @Transactional(readOnly = true)
    public CommentResponse getCommentDetail(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글 없음"));
        return convertToDto(comment);
    }
}