package com.gitlab.blatts1234.todoapp.exceptions;

public class TodoElementNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TodoElementNotFoundException(Long id) {
        super("Could not find todo element " + id);
    }
    
}
