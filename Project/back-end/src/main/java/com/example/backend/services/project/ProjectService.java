package com.example.backend.services.project;

import com.example.backend.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> getAllProjects();
    ProjectDto addProject(ProjectDto projectDto);
    ProjectDto findProjectById(Long id);
    ProjectDto updateProject(ProjectDto projectDto);
    Boolean deleteProject(Long id);
    List<ProjectDto> getAllProjectsByClientId(Long id);
    List<ProjectDto> getAllProjectsByTailorId(Long id);
    List<ProjectDto> getAllProjectsByTailorIdAndClientId(Long tailorId, Long clientId);
    List<ProjectDto> getAllDoneProjectsByTailorId(Long id);

}
