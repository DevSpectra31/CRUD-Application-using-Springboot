package com.example.Crud_using_Postgress.Exception;

public class DuplicateException extends  RuntimeException{

    public DuplicateException(String message){
        super(message);
    }
}
