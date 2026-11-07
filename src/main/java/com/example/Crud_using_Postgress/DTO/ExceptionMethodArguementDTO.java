package com.example.Crud_using_Postgress.DTO;

import java.time.LocalDateTime;
import java.util.Map;

public class ExceptionMethodArguementDTO {


    private LocalDateTime timestamp;
    private int status;
    private  String Error;
    private String Message;
    private String path;

    public ExceptionMethodArguementDTO(LocalDateTime timestamp, int status, String error, String message, String path, Map<String, String> fieldErrors) {
        this.timestamp = timestamp;
        this.status = status;
        Error = error;
        Message = message;
        this.path = path;
        this.fieldErrors = fieldErrors;
    }

    Map<String,String> fieldErrors;
    public ExceptionMethodArguementDTO(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public ExceptionMethodArguementDTO(LocalDateTime timestamp, int status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        Error = error;
        Message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
