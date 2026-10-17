package freelancehub.controller;
import freelancehub.dto.TaskDTO;
import freelancehub.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    @PostMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<TaskDTO> createTask(@PathVariable Long projectId, @RequestBody TaskDTO taskDTO, @AuthenticationPrincipal Long userId){
        TaskDTO NewTaskDTO = taskService.createTask(projectId,taskDTO, userId);
        return ResponseEntity.ok(NewTaskDTO);
    }

    @GetMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<List<TaskDTO>> getTasks(@PathVariable Long projectId, @AuthenticationPrincipal Long userId){
        List<TaskDTO> tasks = taskService.getTasks(projectId, userId);
        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/api/tasks/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long taskId, @AuthenticationPrincipal Long userId){
        TaskDTO task = taskService.getTaskById(taskId, userId);
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long projectId, @RequestBody TaskDTO taskDTO, @AuthenticationPrincipal Long userId){
        TaskDTO updatedTaskDTO = taskService.updateTask(projectId,taskDTO, userId);
        return ResponseEntity.ok(updatedTaskDTO);
    }

    @DeleteMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<String> deleteTask(@PathVariable Long projectId, @AuthenticationPrincipal Long userId){
        taskService.deleteTask(projectId, userId);
        return ResponseEntity.status(HttpStatus.OK).body("Task updated");
    }
}
