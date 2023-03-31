package com.example.backend.repositories;

import com.example.backend.entities.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {
    ProjectEntity findByProjectId(Long projectId);
    ProjectEntity findByProjectName(String projectName);

}
