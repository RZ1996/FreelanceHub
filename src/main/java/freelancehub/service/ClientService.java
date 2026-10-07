package freelancehub.service;

import freelancehub.dto.ClientDTO;
import freelancehub.entity.Client;
import freelancehub.entity.User;
import freelancehub.exceptions.ClientNotFoundException;
import freelancehub.exceptions.InvalidCredentialsException;
import freelancehub.mapper.ClientMapper;
import freelancehub.repository.ClientRepository;
import freelancehub.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final ClientMapper clientMapper;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    public ClientService(ClientMapper clientMapper, ClientRepository clientRepository, UserRepository userRepository) {
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    public ClientDTO createClient(Long userId, ClientDTO clientDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Client client = clientMapper.clientDTOtoClient(clientDTO);
        client.setUser(user);
        clientRepository.save(client);
        return clientMapper.clientToClientDTO(client);
    }

    public List<ClientDTO> getClients(Long userId) {
       User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
       List<Client> clients = user.getClients();
       List<ClientDTO> clientDTOs = new ArrayList<ClientDTO>();
       for(int i = 0; i < clients.size(); i ++){
           clientDTOs.add(clientMapper.clientToClientDTO(clients.get(i)));
       }
       return clientDTOs;

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