package freelancehub.controller;

import freelancehub.dto.ClientDTO;
import freelancehub.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping("/api/clients")
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO, @AuthenticationPrincipal Long userId){
        ClientDTO client = clientService.createClient(userId, clientDTO);
        return  ResponseEntity.ok(client);
    }

    @GetMapping("/api/clients")
    public ResponseEntity<List<ClientDTO>> getClients(@AuthenticationPrincipal Long userId){
        List<ClientDTO> clients = clientService.getClients(userId);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/api/clients/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable long id){
        ClientDTO clientDTO = clientService.getClientByID(id);
        return ResponseEntity.ok(clientDTO);
    }

    @PutMapping("/api/clients/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable long id, @RequestBody ClientDTO clientDTO){
        ClientDTO client = clientService.updateClient(id,clientDTO);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/api/clients/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable long id){
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).body("Client removed");
    }



}
