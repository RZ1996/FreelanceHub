package freelancehub.controller;

import freelancehub.dto.ProjectDTO;
import freelancehub.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping("/api/clients/{clientId}/projects")
    public ResponseEntity <List<ProjectDTO>> getProjects(@PathVariable Long clientId, @AuthenticationPrincipal Long userId){
        List<ProjectDTO> projects = projectService.getProjects(clientId, userId);
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/api/clients/{clientId}/projects")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO, @PathVariable Long clientId, @AuthenticationPrincipal Long userId){
        ProjectDTO NewProjectDTO = projectService.createProject(projectDTO, clientId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(NewProjectDTO);
    }

    @PatchMapping("/api/clients/{clientId}/projects")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO, @AuthenticationPrincipal Long userId){
        ProjectDTO updatedProjectDTO = projectService.updateProject(id,projectDTO, userId);
        return ResponseEntity.ok(updatedProjectDTO);
    }
}
