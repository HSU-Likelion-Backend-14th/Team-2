package com.likelion.sns.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthorResponse {
    private final Integer userId;
    private final String nickname;
}