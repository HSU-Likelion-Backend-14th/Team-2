package com.likelion.sns.service;

import com.likelion.sns.entity.Comment;
import com.likelion.sns.entity.Post;
import com.likelion.sns.repository.CommentRepository;
import com.likelion.sns.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 특정 게시글의 댓글 목록 조회
    public List<Comment> getCommentsByPost(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("404-1: 게시글을 찾을 수 없습니다."));
        return commentRepository.findAllByPost(post);
    }

    // 댓글 하나 상세 조회
    public Comment getComment(Integer commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("404-2: 댓글을 찾을 수 없습니다."));
    }

    // 댓글 작성
    @Transactional
    public Comment createComment(Integer postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("404-1: 게시글을 찾을 수 없습니다."));

        comment.setPost(post);
        return commentRepository.save(comment);
    }
}