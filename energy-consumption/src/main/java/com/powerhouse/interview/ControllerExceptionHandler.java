package com.powerhouse.interview;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(BusinessFault.class)
  void handleBusinessFault(HttpServletResponse response) throws IOException {
  	response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());

  }

  @ExceptionHandler(IllegalArgumentException.class)
  void handleIllegalArgumentException(HttpServletResponse response) throws IOException {
  	response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());

  }
  
}
