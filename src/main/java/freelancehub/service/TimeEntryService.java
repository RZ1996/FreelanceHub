package freelancehub.service;
import freelancehub.dto.TimeEntryDTO;
import freelancehub.entity.Task;
import freelancehub.entity.TimeEntry;
import freelancehub.entity.User;
import freelancehub.exceptions.ProjectNotFoundException;
import freelancehub.exceptions.TaskNotFoundException;
import freelancehub.mapper.TimeEntryMapper;
import freelancehub.repository.TaskRepository;
import freelancehub.repository.TimeEntryRepository;
import freelancehub.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeEntryService {

    private final TimeEntryMapper timeEntryMapper;
    private final TimeEntryRepository timeEntryRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TimeEntryService(TimeEntryMapper timeEntryMapper, TaskRepository taskRepository, TimeEntryRepository timeEntryRepository, UserRepository userRepository){
        this.timeEntryMapper = timeEntryMapper;
        this.timeEntryRepository = timeEntryRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TimeEntryDTO createTimeEntry(Long taskId, Long userId, TimeEntryDTO timeEntryDTO){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        TimeEntry timeEntry = timeEntryMapper.timeEntryDTOToTimeEntry(timeEntryDTO);
        timeEntry.setUser(user);
        timeEntry.setTask(task);
        timeEntryRepository.save(timeEntry);
        timeEntry = timeEntryMapper.timeEntryDTOToTimeEntry(timeEntryDTO);
        return timeEntryMapper.timeEntryToTimeEntryDTO(timeEntry);
    }

    public List<TimeEntryDTO> getTimeEntries(Long taskId, Long userId){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<TimeEntry> timeEntries = timeEntryRepository.getTimeEntriesByTask(task);
        List<TimeEntryDTO> timeEntryDTOS = new ArrayList<TimeEntryDTO>();
        for(int i = 0; i < timeEntries.size(); i ++){
            timeEntryDTOS.add(timeEntryMapper.timeEntryToTimeEntryDTO(timeEntries.get(i)));
        }

        return timeEntryDTOS;

    }



}
