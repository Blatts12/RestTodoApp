package com.gitlab.blatts1234.todoapp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.gitlab.blatts1234.todoapp.assemblers.ProjectAssembler;
import com.gitlab.blatts1234.todoapp.exceptions.ProjectNotFoundException;
import com.gitlab.blatts1234.todoapp.models.Project;
import com.gitlab.blatts1234.todoapp.repositories.ProjectRepository;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {
    
    private final ProjectRepository projectRepository;
    private final ProjectAssembler projectAssembler;

    ProjectController(ProjectRepository projectRepository, ProjectAssembler projectAssembler) {
        this.projectRepository = projectRepository;
        this.projectAssembler = projectAssembler;
    }

    @GetMapping("/projects")
    public CollectionModel<EntityModel<Project>> allProjects() {
        List<EntityModel<Project>> projects = projectRepository.findAll().stream()
            .map(projectAssembler::toModel)
            .collect(Collectors.toList());
        
        return CollectionModel.of(projects,
            linkTo(methodOn(ProjectController.class).allProjects()).withSelfRel()
        );
    }

    @PostMapping("/projects")
    public ResponseEntity<?> newProject(@RequestBody Project project) {
        EntityModel<Project> projectModel = projectAssembler.toModel(projectRepository.save(project));

        return ResponseEntity
            .created(projectModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(projectModel);
    }

    @GetMapping("/projects/{id}")
    public EntityModel<Project> oneProject(@PathVariable Long id) {
        Project project = projectRepository.findById(id)
            .orElseThrow(() -> new ProjectNotFoundException(id));
    
        return projectAssembler.toModel(project);
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<?> replaceProject(@RequestBody Project newProject, @PathVariable Long id) {
        Project updatedProject = projectRepository.findById(id)
            .map(project -> {
                project.setTitle(newProject.getTitle());
                project.setDescription(newProject.getDescription());
                project.setElements(newProject.getElements());
                return projectRepository.save(project);
            }).orElseGet(() -> {
                newProject.setId(id);
                return projectRepository.save(newProject);
            });
        
        EntityModel<Project> projectModel = projectAssembler.toModel(updatedProject);

        return ResponseEntity
            .created(projectModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(projectModel);
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
