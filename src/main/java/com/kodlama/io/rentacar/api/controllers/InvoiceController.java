package com.kodlama.io.rentacar.api.controllers;

import com.kodlama.io.rentacar.business.abstracts.InvoiceService;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateInvoiceRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateInvoiceResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllInvoicesResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetInvoiceResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateInvoiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoices")
public class InvoiceController {
    private final InvoiceService service;

    @GetMapping
    List<GetAllInvoicesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetInvoiceResponse getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateInvoiceResponse add(@RequestBody CreateInvoiceRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateInvoiceResponse update(@PathVariable int id, @RequestBody UpdateInvoiceRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}
