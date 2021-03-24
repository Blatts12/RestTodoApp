package com.gitlab.blatts1234.todoapp.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.gitlab.blatts1234.todoapp.controllers.ProjectController;
import com.gitlab.blatts1234.todoapp.controllers.TaskController;
import com.gitlab.blatts1234.todoapp.models.Project;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProjectAssembler implements RepresentationModelAssembler<Project, EntityModel<Project>> {

    @Override
    public EntityModel<Project> toModel(Project project) {
        return EntityModel.of(project,
            linkTo(methodOn(ProjectController.class).oneProject(project.getId())).withSelfRel(),
            linkTo(methodOn(TaskController.class).allTasks(project.getId())).withSelfRel().withRel("tasks"),
            linkTo(methodOn(ProjectController.class).allProjects()).withRel("projects")
        );
    }

    
}
