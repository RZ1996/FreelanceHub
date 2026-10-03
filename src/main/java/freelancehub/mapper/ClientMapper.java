package freelancehub.mapper;

import freelancehub.dto.ClientDTO;
import freelancehub.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO clientToClientDTO(Client client);

    Client clientDTOtoClient(ClientDTO clientDTO);
}
