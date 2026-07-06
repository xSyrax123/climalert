package com.climalert.exceptions;

import org.springframework.http.HttpStatus;

public class ExternalServiceException extends ApiException {
  public ExternalServiceException(String message) {
    super(message, HttpStatus.BAD_GATEWAY);
  }
}
