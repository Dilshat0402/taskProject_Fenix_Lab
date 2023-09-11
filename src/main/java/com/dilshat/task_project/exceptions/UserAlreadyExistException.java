package com.dilshat.task_project.exceptions;

public class UserAlreadyExistException extends RuntimeException{
    public UserAlreadyExistException(String s){
        super(s);
    }
}
