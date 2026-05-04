package com.example.Hotelbooking.Exception;

import com.example.Hotelbooking.DTO.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    private static Logger logger = LoggerFactory.getLogger(GlobalException.class);
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        logger.warn("User not found : {} | PathVariable : {}", e.getMessage(), request.getRequestURI());
        ErrorResponse errorResponse = new ErrorResponse("Not Found", 404, e.getMessage(),request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleHotelNotFoundException(HotelNotFoundException e, HttpServletRequest request) {
        logger.warn("Hotel not found : {} | PathVariable : {}", e.getMessage(), request.getRequestURI());
        ErrorResponse errorResponse = new ErrorResponse("Not Found", 404, e.getMessage(),request.getRequestURI());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

