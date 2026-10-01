package freelancehub.service;

import freelancehub.dto.ClientDTO;
import freelancehub.entity.Client;
import freelancehub.exceptions.ClientNotFoundException;
import freelancehub.mapper.ClientMapper;
import freelancehub.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;

    public ClientService(ClientMapper clientMapper, ClientRepository clientRepository) {
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
    }

    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientMapper.clientDTOtoClient(clientDTO);
        clientRepository.save(client);
        return clientMapper.clientToClientDTO(client);
    }

    public void getClients() {
        clientRepository.findAll();
    }

    public ClientDTO getClientByID(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));
        return clientMapper.clientToClientDTO(client);
    }

    public ClientDTO updateClient(ClientDTO clientDTO){
        Client client = new Client();
        ClientDTO newClient = clientMapper.clientToClientDTO(client);
        newClient.setId(client.getId());
        newClient.setName(clientDTO.getName());
        newClient.setEmail(client.getEmail());
        newClient.setPhone(client.getPhone());
        newClient.setBillingAddress(client.getBillingAddress());
        clientRepository.save(clientMapper.clientDTOtoClient(newClient));
        return  newClient;
    }

    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }
}