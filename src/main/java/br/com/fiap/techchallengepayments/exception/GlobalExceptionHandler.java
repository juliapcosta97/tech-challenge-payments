package br.com.fiap.techchallengepayments.exception;

import br.com.fiap.techchallengepayments.exception.dtos.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseHttpException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpException(BaseHttpException e) {
        //TODO adicionar logs
        return ResponseEntity.status(e.getStatusCode()).body(new ErrorResponseDto(e.getMessage()));
    }
}
