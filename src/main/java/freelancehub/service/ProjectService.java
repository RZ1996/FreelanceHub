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
    private final ClientService clientService;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper, ClientRepository clientRepository, ClientService clientService){
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
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

    public ProjectDTO createProject(ProjectDTO projectDTO, Long clientId, Long userId){
        Client client = clientService.validationOfClientOwner(clientId, userId);
        Project project = projectMapper.projectDTOToProject(projectDTO);
        project.setClient(client);
        projectRepository.save(project);
        return projectMapper.projectToProjectDTO(project);
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO, Long userId){
        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found"));
        Client client = clientService.validationOfClientOwner(project.getClient().getId(), userId);
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setHourlyRate(projectDTO.getHourlyRate());
        project.setStatus(projectDTO.getStatus());
        projectRepository.save(project);
        return projectMapper.projectToProjectDTO(project);

    }
}
