package com.gitlab.blatts1234.todoapp.exceptions.advices;

import com.gitlab.blatts1234.todoapp.exceptions.TaskNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TaskNotFoundAdvice {
    
    @ResponseBody
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String todoElementNotFoundHandler(TaskNotFoundException ex) {
        return ex.getMessage();
    }
}
