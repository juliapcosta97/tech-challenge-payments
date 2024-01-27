package br.com.fiap.techchallengepayments.exception;

import br.com.fiap.techchallengepayments.exception.dtos.ErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BaseHttpException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpException(BaseHttpException e) {
        logger.error(String.format("GlobalExceptionHandler: ", e.getMessage()));
        return ResponseEntity.status(e.getStatusCode()).body(new ErrorResponseDto(e.getMessage()));
    }
}
