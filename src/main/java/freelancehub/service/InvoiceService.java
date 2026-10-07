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
        Invoice invoice = new Invoice();
        invoice.setClient(client);
        invoice = invoiceMapper.invoiceDTOToInvoice(invoiceDTO);
        invoiceRepository.save(invoice);
        return invoiceMapper.invoiceToInvoiceDTO(invoice);
    }

    public void getInvoice(Long id){
        invoiceRepository.findById(id);
    }

    public InvoiceDTO updateInvoice(Long id, InvoiceDTO invoiceDTO){
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));
        invoice.setIssueDate(invoiceDTO.getIssueDate());
        invoice.setDueDate(invoiceDTO.getDueDate());
        invoice.setTotalAmount(invoiceDTO.getTotalAmount());
        invoice.setPeriodTo(invoiceDTO.getPeriodTo());
        invoice.setPeriodFrom(invoiceDTO.getPeriodFrom());
        invoice.setStatus(invoiceDTO.getStatus());
        return invoiceMapper.invoiceToInvoiceDTO(invoice);
    }




}
