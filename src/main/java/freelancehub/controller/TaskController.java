package freelancehub.controller;

import freelancehub.dto.TaskDTO;
import freelancehub.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    @PostMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<String> createTask(@PathVariable Long projectId, @RequestBody TaskDTO taskDTO){
        taskService.createTask(projectId,taskDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Task created");
    }

    @PatchMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<String> updateTask(@PathVariable Long projectId, @RequestBody TaskDTO taskDTO){
        taskService.updateTask(projectId,taskDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Task updated");
    }
}
