package com.gitlab.blatts1234.todoapp.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.gitlab.blatts1234.todoapp.controllers.TaskController;
import com.gitlab.blatts1234.todoapp.models.Task;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class TaskAssembler implements RepresentationModelAssembler<Task, EntityModel<Task>> {

    @Override
    public EntityModel<Task> toModel(Task task) {
        
        Long projectId = task.getProject().getId();

        EntityModel<Task> taskModel = EntityModel.of(task,
            linkTo(methodOn(TaskController.class).oneTask(projectId, task.getId())).withSelfRel(),
            linkTo(methodOn(TaskController.class).allTasks(projectId)).withRel("tasks")
        );

        switch (task.getStatus()) {
            case TODO:
                addWipTaskLink(taskModel, task, projectId);
                addCompleteTaskLink(taskModel, task, projectId);
                addDropTaskLink(taskModel, task, projectId);
            break;
            case COMPLETED:
                addTodoTaskLink(taskModel, task, projectId);
                addWipTaskLink(taskModel, task, projectId);
                addDropTaskLink(taskModel, task, projectId);
            break;
            case WIP:
                addTodoTaskLink(taskModel, task, projectId);
                addCompleteTaskLink(taskModel, task, projectId);
                addDropTaskLink(taskModel, task, projectId);
            break;
            case DROPPED:
                addTodoTaskLink(taskModel, task, projectId);
                addWipTaskLink(taskModel, task, projectId);
                addCompleteTaskLink(taskModel, task, projectId);
            break;
        }

        return taskModel;
    }

    private void addDropTaskLink(EntityModel<Task> taskModel, Task task, Long projectId) {
        taskModel.add(linkTo(methodOn(TaskController.class).drop(projectId, task.getId())).withRel("drop"));
    }

    private void addCompleteTaskLink(EntityModel<Task> taskModel, Task task, Long projectId) {
        taskModel.add(linkTo(methodOn(TaskController.class).complete(projectId, task.getId())).withRel("complete"));
    }

    private void addWipTaskLink(EntityModel<Task> taskModel, Task task, Long projectId) {
        taskModel.add(linkTo(methodOn(TaskController.class).wip(projectId, task.getId())).withRel("wip"));
    }

    private void addTodoTaskLink(EntityModel<Task> taskModel, Task task, Long projectId) {
        taskModel.add(linkTo(methodOn(TaskController.class).todo(projectId, task.getId())).withRel("todo"));
    }
    
}
