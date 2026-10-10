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


    public InvoiceService(InvoiceMapper invoiceMapper, InvoiceRepository invoiceRepository, ClientRepository clientRepository){
        this.invoiceMapper =  invoiceMapper;
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
    }

    public InvoiceDTO createInvoice(Long clientId, InvoiceDTO invoiceDTO){
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException("Client not found"));
        Invoice invoice = invoiceMapper.invoiceDTOToInvoice(invoiceDTO);
        invoice.setClient(client);
        invoiceRepository.save(invoice);
        return invoiceMapper.invoiceToInvoiceDTO(invoice);
    }

    public List<InvoiceDTO> getInvoices(Long clientId){
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException("Client not found"));
        List<Invoice> invoices = invoiceRepository.getInvoicesByClientId(client.getId());
        List<InvoiceDTO> invoicesDTO = new ArrayList<InvoiceDTO>();
        for(int i = 0; i < invoices.size(); i ++){
            invoicesDTO.add(invoiceMapper.invoiceToInvoiceDTO(invoices.get(i)));
        }

        return invoicesDTO;
    }

    public InvoiceDTO getInvoiceById(Long invoiceId){
       Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));
       return invoiceMapper.invoiceToInvoiceDTO(invoice);
    }

    public InvoiceDTO updateInvoice(Long invoiceId, InvoiceDTO invoiceDTO){
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));
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
