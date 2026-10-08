package freelancehub.controller;
import freelancehub.dto.TaskDTO;
import freelancehub.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<List<TaskDTO>> getTasks(@PathVariable Long projectId){
        List<TaskDTO> tasks = taskService.getTasks(projectId);
        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long projectId){
        TaskDTO task = taskService.getTaskById(projectId);
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<String> updateTask(@PathVariable Long projectId, @RequestBody TaskDTO taskDTO){
        taskService.updateTask(projectId,taskDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Task updated");
    }

    @DeleteMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<String> deleteTask(@PathVariable Long projectId){
        taskService.deleteTask(projectId);
        return ResponseEntity.status(HttpStatus.OK).body("Task updated");
    }
}
