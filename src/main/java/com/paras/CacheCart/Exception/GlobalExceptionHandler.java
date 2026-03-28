package com.paras.CacheCart.Exception;


import com.paras.CacheCart.DTO.ApiResponse;
import com.paras.CacheCart.DTO.ErrorResponse;

import com.paras.CacheCart.DTO.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );

        ApiResponse<ErrorResponse> apiResponse = ApiResponse.<ErrorResponse>builder()
                .success(false)
                .message("Resource not Found Error")
                .data(errorResponse)
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleGlobalException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Something went wrong",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        ApiResponse<ErrorResponse> apiResponse = ApiResponse.<ErrorResponse>builder()
                .success(false)
                .message("Global Exception Error")
                .data(errorResponse)
                .timeStamp(LocalDateTime.now()).build();

        return new ResponseEntity<>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> handleStockException(InsufficientStockException exception) {

        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );

        ApiResponse<ErrorResponse> apiResponse = ApiResponse.<ErrorResponse>builder()
                .success(false)
                .message("Insufficient Stock Error")
                .data(errorResponse)
                .timeStamp(LocalDateTime.now()).build();

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<ValidationError>>> handleValidationErrors(MethodArgumentNotValidException exception) {

        List<ValidationError> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream().map(fieldError -> new ValidationError(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .toList();

        ApiResponse<List<ValidationError>> apiResponse = ApiResponse.<List<ValidationError>>builder()
                .success(false)
                .message("Validation Failed")
                .data(errors)
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

}
