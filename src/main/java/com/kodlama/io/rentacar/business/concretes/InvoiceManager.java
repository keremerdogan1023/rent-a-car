package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.InvoiceService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateInvoiceRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateInvoiceResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllInvoicesResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetInvoiceResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateInvoiceResponse;
import com.kodlama.io.rentacar.business.rules.InvoiceBusinessRules;
import com.kodlama.io.rentacar.entities.Invoice;
import com.kodlama.io.rentacar.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository repository;
    private final ModelMapper mapper;
    private final InvoiceBusinessRules rules;


    @Override
    public List<GetAllInvoicesResponse> getAll() {
        List<Invoice> invoices = repository.findAll();
        List<GetAllInvoicesResponse> responses = invoices.stream()
                .map(invoice -> mapper.map(invoice, GetAllInvoicesResponse.class)).toList();
        return responses;
    }

    @Override
    public GetInvoiceResponse getById(int id) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = repository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapper.map(invoice, GetInvoiceResponse.class);
        return response;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request) {
        Invoice invoice = mapper.map(request, Invoice.class);
        invoice.setId(0);
        invoice.setTotalPrice(getTotalPrice(invoice));
        repository.save(invoice);

        CreateInvoiceResponse response = mapper.map(invoice, CreateInvoiceResponse.class);

        return response;

    }

    @Override
    public UpdateInvoiceResponse update(int id, UpdateInvoiceRequest request) {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = mapper.map(request, Invoice.class);
        invoice.setId(id);
        invoice.setTotalPrice(getTotalPrice(invoice));
        repository.save(invoice);

        UpdateInvoiceResponse response = mapper.map(invoice, UpdateInvoiceResponse.class);

        return response;

    }

    @Override
    public void delete(int id) {
        rules.checkIfInvoiceExists(id);
        repository.deleteById(id);
    }

    private double getTotalPrice(Invoice invoice) {
        double totalPrice = invoice.getDailyPrice() * invoice.getRentedForDays();
        invoice.setTotalPrice(totalPrice);
        return totalPrice;
    }


}
