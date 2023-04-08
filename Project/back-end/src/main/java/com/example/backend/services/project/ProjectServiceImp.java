package com.example.backend.services.project;

import com.example.backend.dto.ProjectDto;
import com.example.backend.entities.ClientEntity;
import com.example.backend.entities.ProjectEntity;
import com.example.backend.entities.TailorEntity;
import com.example.backend.repositories.ClientRepository;
import com.example.backend.repositories.ProjectRepository;
import com.example.backend.repositories.TailorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImp implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TailorRepository tailorRepository;
    @Autowired
    private ClientRepository clientRepository;

    private List<ProjectDto> entityToDto(List<ProjectEntity> projectEntities) {
        List<ProjectDto> projectDtos = new ArrayList<>();
        for(ProjectEntity projectEntity : projectEntities) {
            ProjectDto projectDto = new ProjectDto();
            BeanUtils.copyProperties(projectEntity, projectDto);
            projectDtos.add(projectDto);
        }
        return projectDtos;
    }

    @Override
    public List<ProjectDto> getAllProjects() {
        List<ProjectEntity> projectEntities = (List<ProjectEntity>) projectRepository.findAll();
        return entityToDto(projectEntities);
    }

    @Override
    public Boolean addProject(ProjectDto projectDto) {
        ProjectEntity projectEntity = new ProjectEntity();
        BeanUtils.copyProperties(projectDto, projectEntity);
        
        if(projectEntity.getShow_to_public() == true ) {
            projectEntity.setVisibility_code("");
        }

        projectEntity.setTailor(tailorRepository.findById(projectDto.getTailor().getId()).get());
        projectEntity.setClient(clientRepository.findById(projectDto.getClient().getId()).get());

        try {
            projectRepository.save(projectEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ProjectDto findProjectById(Long id) {
        ProjectEntity projectEntity = projectRepository.findById(id).isPresent() ? projectRepository.findById(id).get() : null;
        ProjectDto projectDto = new ProjectDto();
        if(projectEntity != null) {
            BeanUtils.copyProperties(projectEntity, projectDto);
        }
        return projectDto;
    }

    public List<ProjectDto> getAllProjectsByTitle(String title) {
        List<ProjectEntity> projectEntities = (List<ProjectEntity>) projectRepository.findAllByTitle(title);
        return entityToDto(projectEntities);
    }

    @Override
    public Boolean updateProject(Long projectId, ProjectDto projectDto) {
        ProjectEntity projectEntity = projectRepository.findById(projectId).isPresent() ? projectRepository.findById(projectId).get() : null;
        if(projectEntity != null) {
            try {
                BeanUtils.copyProperties(projectDto, projectEntity);
                projectRepository.save(projectEntity);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteProject(Long id) {
        try {
            projectRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ProjectDto> getAllProjectsByClientId(Long clientId) {
        List<ProjectEntity> projectEntities = (List<ProjectEntity>) projectRepository.findAllByClientId(clientId);
        return entityToDto(projectEntities);
    }

    @Override
    public List<ProjectDto> getAllProjectsByTailorId(Long tailorId) {
        List<ProjectEntity> projectEntities = (List<ProjectEntity>) projectRepository.findAllByTailorId(tailorId);
        return entityToDto(projectEntities);
    }

    @Override
    public List<ProjectDto> getAllProjectsByTailorIdAndClientId(Long tailorId, Long clientId) {
        List<ProjectEntity> projectEntities = (List<ProjectEntity>) projectRepository.findAllByTailorIdAndClientId(tailorId, clientId);
        return entityToDto(projectEntities);
    }

    @Override
    public List<ProjectDto> getAllDoneProjectsByTailorId(Long tailorId) {
        List<ProjectEntity> projectEntities = (List<ProjectEntity>) projectRepository.findAllByTailorIdAndDone(tailorId, true);
        return entityToDto(projectEntities);
    }
    public List<ProjectDto> getAllNotDoneProjectsByTailorId(Long tailorId) {
        List<ProjectEntity> projectEntities = (List<ProjectEntity>) projectRepository.findAllByTailorIdAndNotDone(tailorId, false);
        return entityToDto(projectEntities);
    }
}
