package freelancehub.controller;
import freelancehub.dto.ClientDTO;
import freelancehub.service.ClientService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO, @AuthenticationPrincipal Long userId){
        ClientDTO client = clientService.createClient(userId, clientDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @GetMapping("/api/clients")
    public ResponseEntity<List<ClientDTO>> getClients(@AuthenticationPrincipal Long userId){
        List<ClientDTO> clients = clientService.getClients(userId);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/api/clients/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id, @AuthenticationPrincipal Long userId){
        ClientDTO clientDTO = clientService.getClientById(id, userId);
        return ResponseEntity.ok(clientDTO);
    }

    @PutMapping("/api/clients/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO, @AuthenticationPrincipal Long userId){
        ClientDTO client = clientService.updateClient(id,clientDTO, userId);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/api/clients/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id, @AuthenticationPrincipal Long userId){
        clientService.deleteClient(id, userId);
        return ResponseEntity.noContent().build();
    }



}
