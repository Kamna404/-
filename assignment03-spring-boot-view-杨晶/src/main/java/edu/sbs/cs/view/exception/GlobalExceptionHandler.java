package edu.sbs.cs.view.exception;

import edu.sbs.cs.view.dto.ResponseWrapper;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理全局异常
    @ExceptionHandler(Exception.class)
    public ResponseWrapper<?> handleException(Exception ex) {
        // 日志记录可添加在此
        return ResponseWrapper.error(500, ex.getMessage());
    }

    // 处理参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseWrapper<?> handleValidationException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseWrapper.error(400, message);
    }
}
