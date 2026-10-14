package freelancehub.controller;

import freelancehub.dto.ProjectDTO;
import freelancehub.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping("/api/clients/{clientId}/projects")
    public ResponseEntity <List<ProjectDTO>> getProjects(@PathVariable Long clientId){
        List<ProjectDTO> projects = projectService.getProjects(clientId);
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/api/clients/{clientId}/projects")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO, @Valid @PathVariable Long clientId){
        ProjectDTO NewProjectDTO = projectService.createProject(projectDTO, clientId);
        return ResponseEntity.ok(NewProjectDTO);
    }

    @PatchMapping("/api/clients/{clientId}/projects")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectDTO projectDTO){
        ProjectDTO updatedProjectDTO = projectService.updateProject(id,projectDTO);
        return ResponseEntity.ok(updatedProjectDTO);
    }
}
