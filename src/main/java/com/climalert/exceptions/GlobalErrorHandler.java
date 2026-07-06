package com.climalert.exceptions;

import com.climalert.dto.error.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalErrorHandler {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ErrorResponseDTO> handleApiException(ApiException ex) {
    ErrorResponseDTO error = new ErrorResponseDTO(
        LocalDateTime.now(),
        ex.getStatus().value(),
        ex.getStatus().getReasonPhrase(),
        ex.getMessage()
    );
    return ResponseEntity.status(ex.getStatus()).body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDTO> handleGeneral(Exception ex) {
    ErrorResponseDTO error = new ErrorResponseDTO(
        LocalDateTime.now(),
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
        "Ocurrio un error interno"
    );
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }
}
