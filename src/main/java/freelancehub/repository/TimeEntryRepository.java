package freelancehub.repository;
import freelancehub.entity.Task;
import freelancehub.entity.TimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeEntryRepository extends JpaRepository<TimeEntry, Long> {

    List<TimeEntry>  getTimeEntriesByTask(Task task);


}
