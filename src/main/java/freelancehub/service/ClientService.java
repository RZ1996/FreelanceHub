package freelancehub.service;

import freelancehub.dto.ClientDTO;
import freelancehub.entity.Client;
import freelancehub.exceptions.ClientNotFoundException;
import freelancehub.exceptions.InvalidCredentialsException;
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
        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(client.getPhone());
        client.setBillingAddress(clientDTO.getBillingAddress());
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

    public ClientDTO updateClient(Long id, ClientDTO clientDTO){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setBillingAddress(clientDTO.getBillingAddress());
        clientRepository.save(client);
        return  clientMapper.clientToClientDTO(client);
    }

    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }
}