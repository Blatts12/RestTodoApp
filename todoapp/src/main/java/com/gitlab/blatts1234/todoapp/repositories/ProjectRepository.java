package com.gitlab.blatts1234.todoapp.repositories;

import com.gitlab.blatts1234.todoapp.models.Project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
}
