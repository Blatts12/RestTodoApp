package com.gitlab.blatts1234.todoapp.repositories;

import com.gitlab.blatts1234.todoapp.models.TodoProject;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TodoProjectRepository extends PagingAndSortingRepository<TodoProject, Long> {
    
}
