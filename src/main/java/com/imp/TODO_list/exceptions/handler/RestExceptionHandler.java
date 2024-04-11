package com.imp.TODO_list.exceptions.handler;

import com.imp.TODO_list.Model.ErrorDetail;
import com.imp.TODO_list.exceptions.EmptyTasksException;
import com.imp.TODO_list.exceptions.NotValidTaskException;
import com.imp.TODO_list.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NotValidTaskException.class)
    public ResponseEntity<?> handlerNotValidTaskException(NotValidTaskException exception) {
        ErrorDetail errorDetail = new ErrorDetail("Los valores introducidos son invalidos",
                HttpStatus.NOT_ACCEPTABLE.value(),exception.getClass().getName(),exception.getMessage());

        return new ResponseEntity<>(errorDetail,null, HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(EmptyTasksException.class)
    public ResponseEntity<String> handlerEmptyTasksException(EmptyTasksException exception) {
        return ResponseEntity.ok(exception.getMessage());
    }
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDetail> handlerTaskNotFoundException(TaskNotFoundException exception) {
        ErrorDetail errorDetail = new ErrorDetail("Tarea no encontrada",HttpStatus.NOT_FOUND.value(),
                exception.getClass().getName(),exception.getMessage());
        return new ResponseEntity<>(errorDetail,null,HttpStatus.NOT_FOUND);
    }
}
