package com.example.backend.services.project;

import com.example.backend.dto.ProjectDto;

import java.util.List;

public interface ProjectService {

    List<ProjectDto> getAllProjects();
    Boolean addProject(ProjectDto projectDto);
    ProjectDto findProjectById(Long id);
    List<ProjectDto> getAllProjectsByTitle(String title);
    Boolean updateProject(Long projectId, ProjectDto projectDto);
    Boolean deleteProject(Long id);
    List<ProjectDto> getAllProjectsByClientId(Long clientId);
    List<ProjectDto> getAllProjectsByTailorId(Long tailorId);
    List<ProjectDto> getAllProjectsByTailorIdAndClientId(Long tailorId, Long clientId);
    List<ProjectDto> getAllDoneProjectsByTailorId(Long tailorId);
    List<ProjectDto> getAllNotDoneProjectsByTailorId(Long tailorId);
    boolean switchCompleteStatus(Long projectId);
    boolean switchPaidStatus(Long projectId);



}
