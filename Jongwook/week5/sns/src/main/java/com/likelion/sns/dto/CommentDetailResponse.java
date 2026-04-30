package com.likelion.sns.dto;

import com.likelion.sns.entity.Comment;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class CommentDetailResponse {
    private final Integer commentId;
    private final Integer postId;
    private final String content;
    private final AuthorResponse author;
    private final LocalDateTime createdAt;

    public CommentDetailResponse(Comment comment) {
        this.commentId = comment.getCommentId();
        this.postId = comment.getPost().getPostId();
        this.content = comment.getContent();
        this.author = new AuthorResponse(
                comment.getUser().getUserId(),
                comment.getUser().getNickname()
        );
        this.createdAt = comment.getCreatedAt();
    }
}