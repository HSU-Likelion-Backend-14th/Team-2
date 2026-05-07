package com.likelion.demo.global.constant.exception;

import com.likelion.demo.global.constant.response.code.BaseResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends RuntimeException {
    private final BaseResponseCode baseResponseCode;

}
