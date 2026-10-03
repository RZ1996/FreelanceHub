package freelancehub.controller;

import freelancehub.dto.ClientDTO;
import freelancehub.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    private final ClientService clientService;
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping("/api/clients")
    public ResponseEntity<String> createClient(@RequestBody ClientDTO clientDTO){
        clientService.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Client created");
    }

    @GetMapping("/api/clients")
    public ResponseEntity<String> getClients(){
        clientService.getClients();
        return ResponseEntity.status(HttpStatus.OK).body("Clients listed");
    }

    @GetMapping("/api/clients/{id}")
    public ResponseEntity<String> getClient(@PathVariable  long id){
        clientService.getClientByID(id);
        return ResponseEntity.status(HttpStatus.OK).body("Client listed");
    }

    @PutMapping("/api/clients/{id}")
    public ResponseEntity<String> updateClient(@PathVariable  long id, @RequestBody ClientDTO clientDTO){
        clientService.updateClient(id,clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Client updated");
    }

    @DeleteMapping("/api/clients/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable long id){
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).body("Client removed");
    }



}
