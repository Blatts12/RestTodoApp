package com.gitlab.blatts1234.todoapp.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Task {
    
    @Id
    @GeneratedValue()
    private Long id;
    private String title;
    private String description;
    private Integer priority = 0;

    @ManyToOne
    @JoinColumn(name = "project_id")
    @ToString.Exclude
    @JsonIgnore
    private Project project;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Task(String title, String description, Project project) {
        this.title = title;
        this.description = description;
        this.project = project;
        this.status = Status.TODO;
    }

    public Task(String title, String description, Project project, Status status) {
        this.title = title;
        this.description = description;
        this.project = project;
        this.status = status;
    }
}
