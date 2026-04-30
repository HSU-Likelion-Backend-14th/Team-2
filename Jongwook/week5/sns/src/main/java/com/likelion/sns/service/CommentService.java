package com.likelion.sns.service;

import com.likelion.sns.dto.*;
import com.likelion.sns.entity.*;
import com.likelion.sns.repository.*;
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
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public List<CommentResponse> getComments(Integer postId) {
        return commentRepository.findAllByPost_PostId(postId).stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CommentResponse getCommentDetail(Integer commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다"));
        return new CommentResponse(comment);
    }

    @Transactional
    public CommentResponse createComment(Integer postId, CreateCommentRequest request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 작성할 게시글을 찾을 수 없습니다"));


        User user = userRepository.findById(1)
                .orElseThrow(() -> new IllegalArgumentException("작성자가 존재하지 않습니다"));

        Comment comment = new Comment(request.getContent(), post, user);
        return new CommentResponse(commentRepository.save(comment));
    }
}