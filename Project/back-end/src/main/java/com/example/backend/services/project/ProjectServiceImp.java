package com.example.backend.services.project;

import com.example.backend.dto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService{
    @Override
    public List<ProjectDto> getAllProjects() {
        return null;
    }

    @Override
    public ProjectDto addProject(ProjectDto projectDto) {
        return null;
    }

    @Override
    public ProjectDto findProjectById(Long id) {
        return null;
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        return null;
    }

    @Override
    public Boolean deleteProject(Long id) {
        return null;
    }

    @Override
    public List<ProjectDto> getAllProjectsByClientId(Long id) {
        return null;
    }

    @Override
    public List<ProjectDto> getAllProjectsByTailorId(Long id) {
        return null;
    }

    @Override
    public List<ProjectDto> getAllProjectsByTailorIdAndClientId(Long tailorId, Long clientId) {
        return null;
    }

    @Override
    public List<ProjectDto> getAllDoneProjectsByTailorId(Long id) {
        return null;
    }
}
