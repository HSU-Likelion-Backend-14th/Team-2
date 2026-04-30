package com.likelion.sns.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.likelion.sns.entity.Comment;
import com.likelion.sns.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "댓글 API", description = "게시글의 댓글 목록 조회, 상세 조회, 작성을 담당합니다.") // 그룹화
@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 목록 조회", description = "특정 게시글에 달린 모든 댓글을 조회합니다.") // 메서드 설명[cite: 7]
    @GetMapping
    public ResponseEntity<List<Comment>> getComments(
            @Parameter(description = "게시글 ID", example = "1") @PathVariable Integer postId) { // 파라미터 설명[cite: 7]
        List<Comment> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }

    @Operation(summary = "댓글 상세 조회", description = "댓글 ID를 통해 특정 댓글의 상세 내용을 조회합니다.")
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentDetail(
            @Parameter(description = "댓글 ID", example = "1") @PathVariable Integer commentId) {
        Comment comment = commentService.getComment(commentId);
        return ResponseEntity.ok(comment);
    }

    @Operation(summary = "댓글 작성", description = "특정 게시글에 새로운 댓글을 작성합니다.")
    @PostMapping
    public ResponseEntity<Comment> createComment(
            @PathVariable Integer postId,
            @RequestBody Comment comment) {
        Comment savedComment = commentService.createComment(postId, comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }
}