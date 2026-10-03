package freelancehub.service;
import freelancehub.dto.TaskDTO;
import freelancehub.entity.Project;
import freelancehub.entity.Task;
import freelancehub.exceptions.ProjectNotFoundException;
import freelancehub.exceptions.TaskNotFoundException;
import freelancehub.mapper.ProjectMapper;
import freelancehub.mapper.TaskMapper;
import freelancehub.repository.ProjectRepository;
import freelancehub.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository,ProjectRepository projectRepository, TaskMapper taskMapper){
    this.taskRepository = taskRepository;
    this.projectRepository = projectRepository;
    this.taskMapper = taskMapper;

    }

    public TaskDTO createTask(Long projectId, TaskDTO dto) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        Task task = taskMapper.taskDTOToTask(dto);
        task.setProject(project);
        taskRepository.save(task);
        return taskMapper.taskToTaskDTO(task);
    }
    public TaskDTO updateTask(Long projectId, TaskDTO taskDTO){
        Task task = taskRepository.findById(projectId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        task.setStatus(taskDTO.getStatus());
        task.setDescription(taskDTO.getDescription());
        task.setEstimatedHours(taskDTO.getEstimatedHours());
        return taskMapper.taskToTaskDTO(task);

    }
}
