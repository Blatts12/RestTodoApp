package com.gitlab.blatts1234.todoapp.exceptions;

public class TaskNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TaskNotFoundException(Long id) {
        super("Could not find todo element " + id);
    }
    
}
