package com.gitlab.blatts1234.todoapp.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Project {
    
    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private Long id;
    private String title;
    private String description;
    
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Task> elements = new ArrayList<>();

    public Project(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
