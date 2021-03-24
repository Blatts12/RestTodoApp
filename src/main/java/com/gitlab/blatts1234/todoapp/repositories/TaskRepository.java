package com.gitlab.blatts1234.todoapp.repositories;

import java.util.List;
import java.util.Optional;

import com.gitlab.blatts1234.todoapp.models.Project;
import com.gitlab.blatts1234.todoapp.models.Task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProject(Project project);
    Optional<Task> findByIdAndProject(Long id, Project project);
}
