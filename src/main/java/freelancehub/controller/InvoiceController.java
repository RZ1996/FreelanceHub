package freelancehub.controller;
import freelancehub.dto.InvoiceDTO;
import freelancehub.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @PostMapping("/api/invoices/{clientId}")
    public ResponseEntity<InvoiceDTO> crateInvoice(@Valid  @RequestBody InvoiceDTO invoiceDTO, @PathVariable Long clientId){
        InvoiceDTO invoice = invoiceService.createInvoice(clientId, invoiceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
    }


    @GetMapping("/api/clients/invoices/{clientId}")
    public ResponseEntity<List<InvoiceDTO>> getInvoices(@PathVariable Long clientId){
       List<InvoiceDTO> invoices = invoiceService.getInvoices(clientId);
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/api/invoices/{invoiceId}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long invoiceId){
        InvoiceDTO invoice = invoiceService.getInvoiceById(invoiceId);
        return ResponseEntity.ok(invoice);
    }

    @PutMapping("/api/invoices/{invoiceId}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable Long invoiceId, @Valid @RequestBody InvoiceDTO invoiceDTO){
       InvoiceDTO updatedInvoiceDTO = invoiceService.updateInvoice(invoiceId, invoiceDTO);
        return ResponseEntity.ok(updatedInvoiceDTO);
    }
}
