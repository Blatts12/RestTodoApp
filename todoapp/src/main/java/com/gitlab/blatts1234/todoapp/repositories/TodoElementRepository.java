package com.gitlab.blatts1234.todoapp.repositories;

import com.gitlab.blatts1234.todoapp.models.TodoElement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoElementRepository extends JpaRepository<TodoElement, Long> {
    
}
