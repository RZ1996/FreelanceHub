package freelancehub.controller;
import freelancehub.dto.TimeEntryDTO;
import freelancehub.service.TimeEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private final TimeEntryService  timeEntryService;

    public TimeEntryController(TimeEntryService timeEntryService){
        this.timeEntryService = timeEntryService;
    }

    @PostMapping("/api/tasks/{taskId}/time-entries")
    public ResponseEntity<String> createTimeEntry(@PathVariable Long taskId, @AuthenticationPrincipal Long userId, @RequestBody TimeEntryDTO timeEntryDTO){
        timeEntryService.createTimeEntry(taskId,userId,timeEntryDTO);
        return ResponseEntity.status(HttpStatus.OK).body("TimeEntry created");

    }

    @GetMapping("/api/time-entries/summary")
    public ResponseEntity <List<TimeEntryDTO>> getTimeEntries(@PathVariable Long taskId, @AuthenticationPrincipal Long userId){
        List<TimeEntryDTO> timeEntries = timeEntryService.getTimeEntries(taskId, userId);
        return ResponseEntity.ok(timeEntries);
    }
}
