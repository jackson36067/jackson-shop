package com.jackson.hanlder;

import com.jackson.exception.BaseException;
import com.jackson.exception.NoAuthenticationException;
import com.jackson.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 捕获业务异常
     *
     * @param be
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Result<String>> ExceptionHandler(NoAuthenticationException be) {
        be.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST) // 返回 400 状态码
                .body(Result.error(be.getMessage()));
    }

    /**
     * 处理所有其他异常
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Result<String>> handleException(BaseException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 返回 500 状态码
                .body(Result.error(e.getMessage()));
    }
}
