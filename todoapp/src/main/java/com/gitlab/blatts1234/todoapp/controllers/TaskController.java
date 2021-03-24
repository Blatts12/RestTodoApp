package com.gitlab.blatts1234.todoapp.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import com.gitlab.blatts1234.todoapp.assemblers.TaskAssembler;
import com.gitlab.blatts1234.todoapp.exceptions.ProjectNotFoundException;
import com.gitlab.blatts1234.todoapp.exceptions.TaskNotFoundException;
import com.gitlab.blatts1234.todoapp.models.Project;
import com.gitlab.blatts1234.todoapp.models.Status;
import com.gitlab.blatts1234.todoapp.models.Task;
import com.gitlab.blatts1234.todoapp.repositories.ProjectRepository;
import com.gitlab.blatts1234.todoapp.repositories.TaskRepository;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskAssembler taskAssembler;

    TaskController(TaskRepository taskRepository, ProjectRepository projectRepository, TaskAssembler taskAssembler) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.taskAssembler = taskAssembler;
    }

    @GetMapping("/projects/{projectId}/tasks")
    public CollectionModel<EntityModel<Task>> allTasks(@PathVariable Long projectId) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new ProjectNotFoundException(projectId));
    
        List<EntityModel<Task>> tasks = taskRepository.findByProject(project).stream()
            .map(taskAssembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(tasks,
            linkTo(methodOn(TaskController.class).allTasks(projectId)).withSelfRel()
        );
    }

    @GetMapping("/projects/{projectId}/tasks/{id}")
    public EntityModel<Task> oneTask(@PathVariable Long projectId, @PathVariable Long id) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new ProjectNotFoundException(projectId));

        Task task = taskRepository.findByIdAndProject(id, project)
            .orElseThrow(() -> new TaskNotFoundException(id));
        
        return taskAssembler.toModel(task);
    }

    @PostMapping("/projects/{projectId}/tasks")
    public ResponseEntity<EntityModel<Task>> newTask(@PathVariable Long projectId, @RequestBody Task task) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new ProjectNotFoundException(projectId));

        task.setProject(project);
        task.setStatus(Status.TODO);
        Task newTask = taskRepository.save(task);

        return ResponseEntity
            .created(linkTo(methodOn(TaskController.class).oneTask(projectId, newTask.getId())).toUri())
            .body(taskAssembler.toModel(newTask));
    }

    @DeleteMapping("/projects/{projectId}/tasks/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long projectId, @PathVariable Long id) {
        projectRepository.findById(projectId)
            .orElseThrow(() -> new ProjectNotFoundException(projectId));

        taskRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/projects/{projectId}/tasks/{id}/drop")
    public ResponseEntity<?> drop(@PathVariable Long projectId, @PathVariable Long id) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new ProjectNotFoundException(projectId));

        Task task = taskRepository.findByIdAndProject(id, project)
            .orElseThrow(() -> new TaskNotFoundException(id));
        
        task.setStatus(Status.DROPPED);
        return ResponseEntity.ok(taskAssembler.toModel(taskRepository.save(task)));
    }

    @PutMapping("/projects/{projectId}/tasks/{id}/complete")
    public ResponseEntity<?> complete(@PathVariable Long projectId, @PathVariable Long id) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new ProjectNotFoundException(projectId));

        Task task = taskRepository.findByIdAndProject(id, project)
            .orElseThrow(() -> new TaskNotFoundException(id));
        
        task.setStatus(Status.COMPLETED);
        return ResponseEntity.ok(taskAssembler.toModel(taskRepository.save(task)));
    }

    @PutMapping("/projects/{projectId}/tasks/{id}/wip")
    public ResponseEntity<?> wip(@PathVariable Long projectId, @PathVariable Long id) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new ProjectNotFoundException(projectId));

        Task task = taskRepository.findByIdAndProject(id, project)
            .orElseThrow(() -> new TaskNotFoundException(id));
        
        task.setStatus(Status.WIP);
        return ResponseEntity.ok(taskAssembler.toModel(taskRepository.save(task)));
    }

    @PutMapping("/projects/{projectId}/tasks/{id}/todo")
    public ResponseEntity<?> todo(@PathVariable Long projectId, @PathVariable Long id) {
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new ProjectNotFoundException(projectId));

        Task task = taskRepository.findByIdAndProject(id, project)
            .orElseThrow(() -> new TaskNotFoundException(id));
        
        task.setStatus(Status.TODO);
        return ResponseEntity.ok(taskAssembler.toModel(taskRepository.save(task)));
    }
}
