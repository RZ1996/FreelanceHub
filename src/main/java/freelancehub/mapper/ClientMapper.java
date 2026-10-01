package freelancehub.mapper;

import freelancehub.dto.ClientDTO;
import freelancehub.entity.Client;

public interface ClientMapper {

    ClientDTO clientToClientDTO(Client client);

    Client clientDTOtoClient(ClientDTO clientDTO);
}
