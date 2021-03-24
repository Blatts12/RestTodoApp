package com.gitlab.blatts1234.todoapp.exceptions.advices;
import com.gitlab.blatts1234.todoapp.exceptions.TodoProjectNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TodoProjectNotFoundAdvice {
    
    @ResponseBody
    @ExceptionHandler(TodoProjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String TodoProjectNotFoundHandler(TodoProjectNotFoundException ex) {
        return ex.getMessage();
    }
}
