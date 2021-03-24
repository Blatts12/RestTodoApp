package com.gitlab.blatts1234.todoapp.exceptions;

public class ProjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProjectNotFoundException(Long id) {
        super("Could not find todo project " + id);
    }
    
}
