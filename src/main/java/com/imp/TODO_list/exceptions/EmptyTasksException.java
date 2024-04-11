package com.imp.TODO_list.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class EmptyTasksException extends RuntimeException{
    public EmptyTasksException(String message) {
        super(message);
    }
}
