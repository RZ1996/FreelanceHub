package freelancehub.service;
import freelancehub.dto.InvoiceDTO;
import freelancehub.entity.Client;
import freelancehub.entity.Invoice;
import freelancehub.exceptions.ClientNotFoundException;
import freelancehub.exceptions.InvoiceNotFoundException;
import freelancehub.mapper.InvoiceMapper;
import freelancehub.repository.ClientRepository;
import freelancehub.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceMapper invoiceMapper;
    private final InvoiceRepository invoiceRepository;
    private final ClientRepository clientRepository;
    private final ClientService clientService;


    public InvoiceService(InvoiceMapper invoiceMapper, InvoiceRepository invoiceRepository, ClientRepository clientRepository, ClientService clientService){
        this.invoiceMapper =  invoiceMapper;
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    public InvoiceDTO createInvoice(Long clientId, InvoiceDTO invoiceDTO, Long userId){
        Client client =  clientService.validationOfClientOwner(clientId, userId);
        Invoice invoice = invoiceMapper.invoiceDTOToInvoice(invoiceDTO);
        invoice.setClient(client);
        invoiceRepository.save(invoice);
        return invoiceMapper.invoiceToInvoiceDTO(invoice);
    }

    public List<InvoiceDTO> getInvoices(Long clientId, Long userId){
        Client client = clientService.validationOfClientOwner(clientId, userId);
        List<Invoice> invoices = invoiceRepository.getInvoicesByClientId(client.getId());
        List<InvoiceDTO> invoicesDTO = new ArrayList<InvoiceDTO>();
        for(int i = 0; i < invoices.size(); i ++){
            invoicesDTO.add(invoiceMapper.invoiceToInvoiceDTO(invoices.get(i)));
        }

        return invoicesDTO;
    }

    public InvoiceDTO getInvoiceById(Long invoiceId, Long userId){
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));
        clientService.validationOfClientOwner(invoice.getClient().getId(), userId);
        return invoiceMapper.invoiceToInvoiceDTO(invoice);
    }

    public InvoiceDTO updateInvoice(Long invoiceId, InvoiceDTO invoiceDTO, Long userId){
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));
        clientService.validationOfClientOwner(invoice.getClient().getId(), userId);
        invoice.setIssueDate(invoiceDTO.getIssueDate());
        invoice.setDueDate(invoiceDTO.getDueDate());
        invoice.setTotalAmount(invoiceDTO.getTotalAmount());
        invoice.setPeriodFrom(invoiceDTO.getPeriodFrom());
        invoice.setPeriodTo(invoiceDTO.getPeriodTo());
        invoice.setStatus(invoiceDTO.getStatus());
        invoiceRepository.save(invoice);
        return invoiceMapper.invoiceToInvoiceDTO(invoice);

    }

}
