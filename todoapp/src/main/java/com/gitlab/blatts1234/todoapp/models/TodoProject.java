package com.gitlab.blatts1234.todoapp.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class TodoProject {
    
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    private String title;
    private String description;
    
    @ManyToOne
    private List<TodoElement> elements;

}
