package freelancehub.service;
import freelancehub.dto.TimeEntryDTO;
import freelancehub.entity.Task;
import freelancehub.entity.TimeEntry;
import freelancehub.exceptions.ProjectNotFoundException;
import freelancehub.exceptions.TaskNotFoundException;
import freelancehub.mapper.TimeEntryMapper;
import freelancehub.repository.TaskRepository;
import freelancehub.repository.TimeEntryRepository;
import org.springframework.stereotype.Service;

@Service
public class TimeEntryService {

    private final TimeEntryMapper timeEntryMapper;
    private final TimeEntryRepository timeEntryRepository;
    private final TaskRepository taskRepository;

    public TimeEntryService(TimeEntryMapper timeEntryMapper, TaskRepository taskRepository, TimeEntryRepository timeEntryRepository){
        this.timeEntryMapper = timeEntryMapper;
        this.timeEntryRepository = timeEntryRepository;
        this.taskRepository = taskRepository;
    }

    public TimeEntryDTO createTimeEntry(Long taskId, TimeEntryDTO timeEntryDTO){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setTitle(timeEntryDTO.getTitle());
        timeEntry.setDate(timeEntryDTO.getDate());
        timeEntry.setHours(timeEntryDTO.getHours());
        timeEntry.setNote(timeEntryDTO.getNote());
        return timeEntryMapper.timeEntryToTimeEntryDTO(timeEntry);
    }

    public void getTimeEntries(){
        timeEntryRepository.findAll();
    }



}
