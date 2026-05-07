package com.likelion.sns.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {
    private String content;
    private Integer authorId;
}