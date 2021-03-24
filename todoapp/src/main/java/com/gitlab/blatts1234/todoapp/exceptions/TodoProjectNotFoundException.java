package com.gitlab.blatts1234.todoapp.exceptions;

public class TodoProjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TodoProjectNotFoundException(Long id) {
        super("Could not find todo project " + id);
    }
    
}
