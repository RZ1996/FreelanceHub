package freelancehub.mapper;
import freelancehub.dto.TimeEntryDTO;
import freelancehub.entity.TimeEntry;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper(componentModel = "spring")
public interface TimeEntryMapper {
    TimeEntryDTO timeEntryToTimeEntryDTO(TimeEntry timeEntry);
    TimeEntry timeEntryDTOToTimeEntry(TimeEntryDTO timeEntryDTO);
}
