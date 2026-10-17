package freelancehub.controller;
import freelancehub.dto.InvoiceDTO;
import freelancehub.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService){
        this.invoiceService = invoiceService;
    }

    @PostMapping("/api/invoices/{clientId}")
    public ResponseEntity<InvoiceDTO> crateInvoice(@Valid  @RequestBody InvoiceDTO invoiceDTO, @PathVariable Long clientId, @AuthenticationPrincipal Long userId){
        InvoiceDTO invoice = invoiceService.createInvoice(clientId, invoiceDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(invoice);
    }

    @GetMapping("/api/clients/invoices/{clientId}")
    public ResponseEntity<List<InvoiceDTO>> getInvoices(@PathVariable Long clientId, @AuthenticationPrincipal Long userId){
       List<InvoiceDTO> invoices = invoiceService.getInvoices(clientId, userId);
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/api/invoices/{invoiceId}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long invoiceId, @AuthenticationPrincipal Long userId){
        InvoiceDTO invoice = invoiceService.getInvoiceById(invoiceId, userId);
        return ResponseEntity.ok(invoice);
    }

    @PutMapping("/api/invoices/{invoiceId}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable Long invoiceId, @Valid @RequestBody InvoiceDTO invoiceDTO, @AuthenticationPrincipal Long userId){
       InvoiceDTO updatedInvoiceDTO = invoiceService.updateInvoice(invoiceId, invoiceDTO, userId);
        return ResponseEntity.ok(updatedInvoiceDTO);
    }
}
