package freelancehub.controller;
import freelancehub.dto.TimeEntryDTO;
import freelancehub.service.TimeEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TimeEntryController {

    private final TimeEntryService  timeEntryService;

    public TimeEntryController(TimeEntryService timeEntryService){
        this.timeEntryService = timeEntryService;
    }

    @PostMapping("/api/tasks/{taskId}/time-entries")
    public ResponseEntity<String> createTimeEntry(@PathVariable Long taskId, @RequestBody TimeEntryDTO timeEntryDTO){
        timeEntryService.createTimeEntry(taskId,timeEntryDTO);
        return ResponseEntity.status(HttpStatus.OK).body("TimeEntry created");

    }

    @GetMapping("/api/time-entries/summary")
    public ResponseEntity<String> getTimeEntries(){
        timeEntryService.getTimeEntries();
        return ResponseEntity.status(HttpStatus.OK).body("TimeEntries listed");
    }
}
