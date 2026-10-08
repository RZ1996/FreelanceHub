package freelancehub.controller;

import freelancehub.dto.ProjectDTO;
import freelancehub.service.ProjectService;
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
    public ResponseEntity<String> createProject(@RequestBody ProjectDTO projectDTO, @PathVariable Long clientId){
        projectService.createProject(projectDTO, clientId);
        return ResponseEntity.status(HttpStatus.OK).body("Project created");
    }

    @PatchMapping("/api/clients/{clientId}/projects")
    public ResponseEntity<String> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO){
        projectService.updateProject(id,projectDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Project updated");
    }
}
