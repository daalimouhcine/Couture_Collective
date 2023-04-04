package com.example.backend.controllers;

import com.example.backend.dto.ProjectDto;
import com.example.backend.requests.ProjectRequest;
import com.example.backend.responses.ProjectResponse;
import com.example.backend.responses.SimpleResponse;
import com.example.backend.services.project.ProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    private List<ProjectResponse> dtoToResponse(List<ProjectDto> projectDtos) {
        List<ProjectResponse> projectResponses = new ArrayList<>();
        for(ProjectDto projectDto : projectDtos) {
            ProjectResponse projectResponse = new ProjectResponse();
            BeanUtils.copyProperties(projectDto, projectResponse);
            projectResponses.add(projectResponse);
        }
        return projectResponses;
    }

    @PostMapping("/create")
    public ResponseEntity<SimpleResponse> createProject(@RequestBody ProjectRequest projectRequest) {
        ProjectDto projectDto = new ProjectDto();
        BeanUtils.copyProperties(projectRequest, projectDto);
        Boolean createProjectStatus = projectService.addProject(projectDto);
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setMessage(createProjectStatus ? "Project created successfully" : "Project creation failed");
        simpleResponse.setSuccess(createProjectStatus);
        return ResponseEntity.ok(simpleResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        List<ProjectDto> projectDtos = projectService.getAllProjects();
        List<ProjectResponse> projectResponses = dtoToResponse(projectDtos);
        return ResponseEntity.ok(projectResponses);
    }

    @GetMapping("/byId/{projectId}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long projectId) {
        ProjectDto projectDto = projectService.findProjectById(projectId);
        ProjectResponse projectResponse = new ProjectResponse();
        BeanUtils.copyProperties(projectDto, projectResponse);
        return ResponseEntity.ok(projectResponse);
    }

    @GetMapping("/byName/{projectTitle}")
    public ResponseEntity<List<ProjectResponse>> getProjectByName(@PathVariable String projectTitle) {
        List<ProjectDto> projectDtos = projectService.getAllProjectsByTitle(projectTitle);
        List<ProjectResponse> projectResponses = dtoToResponse(projectDtos);
        return ResponseEntity.ok(projectResponses);
    }

    @GetMapping("/byTailor/{tailorId}")
    public ResponseEntity<List<ProjectResponse>> getAllProjectsByTailorId(@PathVariable Long tailorId) {
        List<ProjectDto> projectDtos = projectService.getAllProjectsByTailorId(tailorId);
        List<ProjectResponse> projectResponses = dtoToResponse(projectDtos);
        return ResponseEntity.ok(projectResponses);
    }

    @GetMapping("/byClient/{clientId}")
    public ResponseEntity<List<ProjectResponse>> getAllProjectsByClientId(@PathVariable Long clientId) {
        List<ProjectDto> projectDtos = projectService.getAllProjectsByClientId(clientId);
        List<ProjectResponse> projectResponses = dtoToResponse(projectDtos);
        return ResponseEntity.ok(projectResponses);
    }

    @GetMapping("/byTailorAndClient/{tailorId}/{clientId}")
    public ResponseEntity<List<ProjectResponse>> getAllProjectsByTailorIdAndClientId(@PathVariable Long tailorId, @PathVariable Long clientId) {
        List<ProjectDto> projectDtos = projectService.getAllProjectsByTailorIdAndClientId(tailorId, clientId);
        List<ProjectResponse> projectResponses = dtoToResponse(projectDtos);
        return ResponseEntity.ok(projectResponses);
    }

    @PutMapping("/update/{projectId}")
    public ResponseEntity<SimpleResponse> updateProject(@PathVariable Long projectId, @RequestBody ProjectRequest projectRequest) {
        ProjectDto projectDto = new ProjectDto();
        BeanUtils.copyProperties(projectRequest, projectDto);
        Boolean updateProjectStatus = projectService.updateProject(projectId, projectDto);
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setMessage(updateProjectStatus ? "Project updated successfully" : "Project updation failed");
        simpleResponse.setSuccess(updateProjectStatus);
        return ResponseEntity.ok(simpleResponse);
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<SimpleResponse> deleteProject(@PathVariable Long projectId) {
        Boolean deleteProjectStatus = projectService.deleteProject(projectId);
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setMessage(deleteProjectStatus ? "Project deleted successfully" : "Project deletion failed");
        simpleResponse.setSuccess(deleteProjectStatus);
        return ResponseEntity.ok(simpleResponse);
    }

    @GetMapping("/done/byTailor/{tailorId}")
    public ResponseEntity<List<ProjectResponse>> getAllDoneProjectsByTailorId(@PathVariable Long tailorId) {
        List<ProjectDto> projectDtos = projectService.getAllDoneProjectsByTailorId(tailorId);
        List<ProjectResponse> projectResponses = dtoToResponse(projectDtos);
        return ResponseEntity.ok(projectResponses);
    }

    @GetMapping("/notDone/byTailor/{tailorId}")
    public ResponseEntity<List<ProjectResponse>> getAllNotDoneProjectsByTailorId(@PathVariable Long tailorId) {
        List<ProjectDto> projectDtos = projectService.getAllNotDoneProjectsByTailorId(tailorId);
        List<ProjectResponse> projectResponses = dtoToResponse(projectDtos);
        return ResponseEntity.ok(projectResponses);
    }
}
