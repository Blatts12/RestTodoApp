package com.gitlab.blatts1234.todoapp;

import com.gitlab.blatts1234.todoapp.models.Project;
import com.gitlab.blatts1234.todoapp.models.Status;
import com.gitlab.blatts1234.todoapp.models.Task;
import com.gitlab.blatts1234.todoapp.repositories.ProjectRepository;
import com.gitlab.blatts1234.todoapp.repositories.TaskRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader {

    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    @Bean
    CommandLineRunner initDatabase(ProjectRepository projectRepository, TaskRepository taskRepository) {

        return args -> {
            Project p1 = projectRepository.save(new Project("Bieganie", "Lubie biegac"));
            Project p2 = projectRepository.save(new Project("Skakanie", "Lubie skakac"));

            taskRepository.save(new Task("Tytul 1-1", "Opis 1-1", p1, Status.TODO));
            taskRepository.save(new Task("Tytul 2-1", "Opis 2-1", p1, Status.WIP));
            taskRepository.save(new Task("Tytul 3-1", "Opis 3-1", p1, Status.WIP));
            taskRepository.save(new Task("Tytul 4-1", "Opis 4-1", p1, Status.COMPLETED));
            taskRepository.save(new Task("Tytul 5-1", "Opis 5-1", p1, Status.COMPLETED));
            taskRepository.save(new Task("Tytul 6-1", "Opis 6-1", p1, Status.DROPPED));

            taskRepository.save(new Task("Tytul 1-2", "Opis 1-2", p2, Status.WIP));
            taskRepository.save(new Task("Tytul 2-2", "Opis 2-2", p2, Status.TODO));
            taskRepository.save(new Task("Tytul 3-2", "Opis 3-2", p2, Status.WIP));
            taskRepository.save(new Task("Tytul 4-2", "Opis 4-2", p2, Status.COMPLETED));
            taskRepository.save(new Task("Tytul 5-2", "Opis 5-2", p2, Status.WIP));
            taskRepository.save(new Task("Tytul 6-2", "Opis 6-2", p2, Status.TODO));

            projectRepository.findAll().forEach(project -> {
                log.info("Project preload " + project);
            });

            taskRepository.findAll().forEach(task -> {
                log.info("Task preload " + task);
            });
        };
    }
    
}
