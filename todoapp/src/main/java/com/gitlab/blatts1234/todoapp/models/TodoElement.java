package com.gitlab.blatts1234.todoapp.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class TodoElement {
    
    @Id
    @GeneratedValue()
    @Setter(AccessLevel.NONE)
    private Long id;
    private String title;
    private String description;
    private Integer priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    public TodoElement(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = Status.TODO;
    }

    public TodoElement(String title, String description, Status status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }
}
