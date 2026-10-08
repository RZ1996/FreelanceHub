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

import java.util.ArrayList;
import java.util.List;

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

    public TaskDTO createTask(Long projectId, TaskDTO taskDTO) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        Task task = taskMapper.taskDTOToTask(taskDTO);
        task = taskMapper.taskDTOToTask(taskDTO);
        task.setProject(project);
        taskRepository.save(task);
        return taskMapper.taskToTaskDTO(task);
    }

    public List<TaskDTO> getTasks(Long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        List<Task> tasks = project.getTasks();
        List<TaskDTO> taskDTOS = new ArrayList<TaskDTO>();

        for(int i = 0; i < tasks.size(); i ++ ){
            taskDTOS.add(taskMapper.taskToTaskDTO(tasks.get(i)));
        }

        return taskDTOS;
    }
    public TaskDTO updateTask(Long projectId, TaskDTO taskDTO){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        Task task = taskMapper.taskDTOToTask(taskDTO);
        task.setProject(project);
        return taskMapper.taskToTaskDTO(task);

    }
}
