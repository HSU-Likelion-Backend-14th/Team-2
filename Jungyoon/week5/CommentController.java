package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CommentResponse;
import com.example.demo.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "댓글 API", description = "게시글 댓글 관리 API")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "댓글 목록 조회", description = "특정 게시글의 모든 댓글을 조회합니다.")
    @GetMapping("/{postId}/comments")
    public ApiResponse<List<CommentResponse>> getComments(
            @Parameter(description = "게시글 ID") @PathVariable Integer postId) {
        return ApiResponse.onSuccess("댓글 목록 조회에 성공했습니다", commentService.getCommentsByPost(postId));
    }

    @Operation(summary = "댓글 상세 조회", description = "댓글 ID로 하나의 댓글을 상세 조회합니다.")
    @GetMapping("/comments/{commentId}")
    public ApiResponse<CommentResponse> getCommentDetail(
            @Parameter(description = "댓글 ID") @PathVariable Long commentId) {
        return ApiResponse.onSuccess("댓글 상세 조회에 성공했습니다", commentService.getCommentDetail(commentId));
    }

    @Operation(summary = "댓글 작성", description = "게시글에 새로운 댓글을 작성합니다.")
    @PostMapping("/{postId}/comments")
    public ApiResponse<CommentResponse> createComment(
            @PathVariable Integer postId,
            @RequestBody String content) {
        return ApiResponse.onSuccess("댓글 작성에 성공했습니다", commentService.createComment(postId, content));
    }
}