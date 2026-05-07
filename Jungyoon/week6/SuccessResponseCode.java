package com.Likelion.demo.global.response.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.Likelion.demo.global.constant.StaticValue.*;

@Getter
@AllArgsConstructor
public enum SuccessResponseCode implements BaseResponseCode{
    SUCCESS_OK("GLOBAL_200", OK, "호출에 성공하였습니다."),
    SUCCESS_CREATED("GLOBAL_201", CREATED,"생성에 성공하였습니다.");

    private String code;
    private int httpStatus;
    private String message;
}
