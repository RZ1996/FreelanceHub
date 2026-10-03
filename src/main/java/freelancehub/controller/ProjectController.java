package freelancehub.controller;

import freelancehub.dto.ProjectDTO;
import freelancehub.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @GetMapping("/api/projects")
    public ResponseEntity<String> getProjects(){
        projectService.getProjects();
        return ResponseEntity.status(HttpStatus.OK).body("Projects listed");
    }

    @PostMapping("/api/projects")
    public ResponseEntity<String> createProject(@RequestBody ProjectDTO projectDTO){
        projectService.createProject(projectDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Project created");
    }

    @PatchMapping("/api/projects/{id}/status")
    public ResponseEntity<String> updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO){
        projectService.updateProject(id,projectDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Project updated");
    }
}
