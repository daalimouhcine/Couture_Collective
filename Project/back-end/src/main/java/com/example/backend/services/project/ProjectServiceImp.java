package com.example.backend.services.project;

import com.example.backend.dto.ClientDto;
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
            projectEntity.setVisibility_code(null);
        }

        projectEntity.setIs_completed(false);
        projectEntity.setIs_paid(false);

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
            ClientEntity clientEntity = clientRepository.findById(projectEntity.getClient().getId()).get();
            ClientDto clientDto = new ClientDto();
            BeanUtils.copyProperties(clientEntity, clientDto);
            projectDto.setClient(clientDto);
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
            System.out.println("not null");
            try {
                System.out.println("before");
                projectEntity.setTitle(projectDto.getTitle());
                projectEntity.setDescription(projectDto.getDescription());
                projectEntity.setType(projectDto.getType());
                projectEntity.setDeadline(projectDto.getDeadline());

//                System.out.println(projectEntity.getKeywords());
//                projectEntity.setKeywords(projectDto.getKeywords());
//                System.out.println(projectEntity.getKeywords());

                projectEntity.setShow_to_public(projectDto.getShow_to_public());
                projectEntity.setVisibility_code(projectDto.getVisibility_code());
                projectEntity.setPrice(projectDto.getPrice());
                projectEntity.setShow_price(projectDto.getShow_price());
                projectEntity.setIs_completed(projectDto.getIs_completed());

                projectEntity.setClient(clientRepository.findById(projectDto.getClient().getId()).get());

                projectRepository.save(projectEntity);
                System.out.println("after");
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
        List<ProjectDto> projectDtos = new ArrayList<>();
        for(ProjectEntity projectEntity : projectEntities) {
            ProjectDto projectDto = new ProjectDto();
            BeanUtils.copyProperties(projectEntity, projectDto);

            ClientEntity clientEntity = clientRepository.findById(projectEntity.getClient().getId()).get();
            ClientDto clientDto = new ClientDto();
            BeanUtils.copyProperties(clientEntity, clientDto);
            projectDto.setClient(clientDto);

            projectDtos.add(projectDto);
        }
        return projectDtos;
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
    @Override
    public List<ProjectDto> getAllNotDoneProjectsByTailorId(Long tailorId) {
        List<ProjectEntity> projectEntities = (List<ProjectEntity>) projectRepository.findAllByTailorIdAndNotDone(tailorId, false);
        return entityToDto(projectEntities);
    }

    @Override
    public boolean switchCompleteStatus(Long projectId) {
        ProjectEntity projectEntity = projectRepository.findById(projectId).isPresent() ? projectRepository.findById(projectId).get() : null;
        if(projectEntity != null) {
            try {
                projectEntity.setIs_completed(!projectEntity.getIs_completed());
                projectRepository.save(projectEntity);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean switchPaidStatus(Long projectId) {
        ProjectEntity projectEntity = projectRepository.findById(projectId).isPresent() ? projectRepository.findById(projectId).get() : null;
        if(projectEntity != null) {
            try {
                projectEntity.setIs_paid(!projectEntity.getIs_paid());
                projectRepository.save(projectEntity);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
