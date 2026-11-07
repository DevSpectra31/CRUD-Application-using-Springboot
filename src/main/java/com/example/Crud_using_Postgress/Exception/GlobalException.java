package com.example.Crud_using_Postgress.Exception;


import com.example.Crud_using_Postgress.DTO.ExceptionMethodArguementDTO;
import com.example.Crud_using_Postgress.DTO.ExceptionResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.*;

@RestControllerAdvice
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponseDto> handleruntimeException(RuntimeException ex , HttpServletRequest req){

        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                req.getRequestURI()
        );
        return ResponseEntity.status(404).body(exceptionResponseDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleGenericruntimeException(RuntimeException ex,HttpServletRequest req){
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage(),
                req.getRequestURI()
        );
        return  ResponseEntity.status(500).body(exceptionResponseDto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleResourcenotfoundcruntimeException(ResourceNotFoundException ex , HttpServletRequest request){

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return  ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<ExceptionResponseDto> handleDuplicateException (DuplicateException ex , HttpServletRequest e ){

        ExceptionResponseDto exceptionResponse = new ExceptionResponseDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                e.getRequestURI()
        );
        return ResponseEntity.status(409).body(exceptionResponse);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionMethodArguementDTO> MethodArguementHandler (MethodArgumentNotValidException ex , HttpServletRequest e ){

        Map<String,String> fielderrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> fielderrors.put(error.getField(),error.getDefaultMessage()));

        ExceptionMethodArguementDTO exceptionResponse = new ExceptionMethodArguementDTO(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                e.getRequestURI(),
                fielderrors
        );
        return ResponseEntity.status(409).body(exceptionResponse);
    }
}
