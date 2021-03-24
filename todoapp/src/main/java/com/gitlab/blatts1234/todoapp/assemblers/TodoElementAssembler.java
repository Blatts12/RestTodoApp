package com.gitlab.blatts1234.todoapp.assemblers;

import com.gitlab.blatts1234.todoapp.models.TodoElement;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;

public class TodoElementAssembler implements SimpleRepresentationModelAssembler<TodoElement> {

    @Override
    public void addLinks(EntityModel<TodoElement> resource) {
        Long elementId = resource.getContent().getId();
        
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<TodoElement>> resources) {
        
    }
    
}
