package freelancehub.service;
import freelancehub.dto.ProjectDTO;
import freelancehub.entity.Project;
import freelancehub.exceptions.InvoiceNotFoundException;
import freelancehub.exceptions.ProjectNotFoundException;
import freelancehub.mapper.ProjectMapper;
import freelancehub.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper){
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public void getProjects(){
        projectRepository.findAll();
    }

    public ProjectDTO createProject(ProjectDTO projectDTO){
        Project project = projectMapper.projectDTOToProject(projectDTO);
        projectRepository.save(project);
        return projectMapper.projectToProjectDTO(project);
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO){
        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setHourlyRate(projectDTO.getHourlyRate());
        project.setStatus(projectDTO.getStatus());
        projectRepository.save(project);
        return projectMapper.projectToProjectDTO(project);

    }
}
