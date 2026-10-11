package freelancehub.service;
import freelancehub.dto.TaskDTO;
import freelancehub.entity.Client;
import freelancehub.entity.Project;
import freelancehub.entity.Task;
import freelancehub.exceptions.ProjectNotFoundException;
import freelancehub.exceptions.TaskNotFoundException;
import freelancehub.mapper.ProjectMapper;
import freelancehub.mapper.TaskMapper;
import freelancehub.repository.ProjectRepository;
import freelancehub.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskMapper taskMapper;
    private final ClientService clientService;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, TaskMapper taskMapper, ClientService clientService){
    this.taskRepository = taskRepository;
    this.projectRepository = projectRepository;
    this.taskMapper = taskMapper;

        this.clientService = clientService;
    }

    public TaskDTO createTask(Long projectId, TaskDTO taskDTO, Long userId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        clientService.validationOfClientOwner(project.getClient().getId(), userId);
        Task task = taskMapper.taskDTOToTask(taskDTO);
        task.setProject(project);
        taskRepository.save(task);
        return taskMapper.taskToTaskDTO(task);
    }

    public List<TaskDTO> getTasks(Long projectId, Long userId){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        clientService.validationOfClientOwner(project.getClient().getId(), userId);
        List<Task> tasks = project.getTasks();
        List<TaskDTO> taskDTOS = new ArrayList<TaskDTO>();

        for(int i = 0; i < tasks.size(); i ++ ){
            taskDTOS.add(taskMapper.taskToTaskDTO(tasks.get(i)));
        }
        return taskDTOS;
    }
    public TaskDTO updateTask(Long taskId, TaskDTO taskDTO, Long userId){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        clientService.validationOfClientOwner(task.getProject().getClient().getId(), userId);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setEstimatedHours(taskDTO.getEstimatedHours());
        taskRepository.save(task);
        return taskMapper.taskToTaskDTO(task);
    }

    public TaskDTO getTaskById(Long taskId, Long userId){
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Task not found"));
        clientService.validationOfClientOwner(task.getProject().getClient().getId(), userId);
        return taskMapper.taskToTaskDTO(task);
    }

    public void deleteTask(Long taskId, Long userId){
        Task task  = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        clientService.validationOfClientOwner(task.getProject().getClient().getId(), userId);
        taskRepository.delete(task);

    }
}
