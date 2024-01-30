package br.com.fiap.techchallengepayments.service.units.exception;

import br.com.fiap.techchallengepayments.exception.BaseHttpException;
import br.com.fiap.techchallengepayments.exception.GlobalExceptionHandler;
import br.com.fiap.techchallengepayments.exception.dtos.ErrorResponseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void testHandleHttpException() {
        BaseHttpException exception = new BaseHttpException(HttpStatus.NOT_FOUND, "Test exception message");

        globalExceptionHandler = new GlobalExceptionHandler();

        ResponseEntity<ErrorResponseDTO> responseEntity = globalExceptionHandler.handleHttpException(exception);

        assertEquals(404, responseEntity.getStatusCodeValue());
        assertEquals("Test exception message", responseEntity.getBody().error_message());
    }
}
