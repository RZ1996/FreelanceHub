package freelancehub.controller;
import freelancehub.dto.InvoiceDTO;
import freelancehub.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @PostMapping("/api/invoices")
    public ResponseEntity<String> crateInvoice(@RequestBody InvoiceDTO invoiceDTO, Long clientId){
        invoiceService.createInvoice(clientId, invoiceDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Invoice created");
    }

    @GetMapping("/api/invoices/{id}/pdf")
    public ResponseEntity<String> getInvoice(@RequestParam Long id){
        invoiceService.getInvoice(id);
        return ResponseEntity.status(HttpStatus.OK).body("Invoice created");
    }

    @PatchMapping("/api/invoices/{id}/status")
    public ResponseEntity<String> updateInvoice(@RequestParam Long id, @RequestBody InvoiceDTO invoiceDTO){
        invoiceService.updateInvoice(id,invoiceDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Invoice updated");
    }
}
