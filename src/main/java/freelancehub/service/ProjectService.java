package freelancehub.service;
import freelancehub.dto.ProjectDTO;
import freelancehub.entity.Client;
import freelancehub.entity.Project;
import freelancehub.exceptions.ClientNotFoundException;
import freelancehub.exceptions.InvoiceNotFoundException;
import freelancehub.exceptions.ProjectNotFoundException;
import freelancehub.mapper.ProjectMapper;
import freelancehub.repository.ClientRepository;
import freelancehub.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final ClientRepository clientRepository;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper, ClientRepository clientRepository){
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.clientRepository = clientRepository;
    }

    public List<ProjectDTO> getProjects(Long clientId){
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException("Client not found"));
        List<Project> projects = client.getProjects();
        List<ProjectDTO> projectDTOS = new ArrayList<ProjectDTO>();
        for(int i = 0; i < projects.size(); i ++ ){
            projectDTOS.add(projectMapper.projectToProjectDTO(projects.get(i)));
        }
        return projectDTOS;
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
