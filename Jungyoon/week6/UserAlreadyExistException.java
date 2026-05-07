package com.Likelion.demo.domain.user.exception;

import com.Likelion.demo.global.exception.BaseException;

public class UserAlreadyExistException extends BaseException{
    public UserAlreadyExistException() {
        super(UserErrorCode.USER_ALREADY_EXIST_409);
    }
}
