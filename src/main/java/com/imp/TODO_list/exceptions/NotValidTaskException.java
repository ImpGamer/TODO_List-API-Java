package com.imp.TODO_list.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotValidTaskException extends RuntimeException{
    public NotValidTaskException(String message) {
        super(message);
    }
}
