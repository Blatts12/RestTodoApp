package com.gitlab.blatts1234.todoapp.assemblers;

import com.gitlab.blatts1234.todoapp.models.TodoProject;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;

public class TodoProjectAssembler implements SimpleRepresentationModelAssembler<TodoProject> {

    @Override
    public void addLinks(EntityModel<TodoProject> resource) {
        
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<TodoProject>> resources) {
        
    }
    
}
