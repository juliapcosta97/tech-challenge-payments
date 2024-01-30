package br.com.fiap.techchallengepayments.exception;

import br.com.fiap.techchallengepayments.exception.dtos.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseHttpException.class)
    public ResponseEntity<ErrorResponseDTO> handleHttpException(BaseHttpException e) {
        return ResponseEntity.status(e.getStatusCode()).body(new ErrorResponseDTO(e.getMessage()));
    }
}
