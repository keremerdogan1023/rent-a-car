package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.*;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateInvoiceRequest;
import com.kodlama.io.rentacar.business.dto.requests.create.CreateRentalRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateRentalRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateRentalResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllRentalsResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetCarResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetRentalResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateRentalResponse;
import com.kodlama.io.rentacar.business.rules.MaintenanceBusinessRules;
import com.kodlama.io.rentacar.business.rules.RentalBusinessRules;
import com.kodlama.io.rentacar.common.dto.CreateRentalPaymentRequest;
import com.kodlama.io.rentacar.entities.Rental;
import com.kodlama.io.rentacar.entities.enums.State;
import com.kodlama.io.rentacar.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class RentalManager implements RentalService {

    private final RentalRepository rentalRepository;
    private final ModelMapper mapper;
    private final CarService carService;
    private final MaintenanceService maintenanceService;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;
    private final MaintenanceBusinessRules maintenanceBusinessRules;
    private final RentalBusinessRules rules;

    @Override
    public List<GetAllRentalsResponse> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetAllRentalsResponse> responses = rentals.stream().map(rental -> mapper.map(rental, GetAllRentalsResponse.class)).toList();
        return responses;
    }

    @Override
    public GetRentalResponse getById(int id) {
        rules.checkIfRentalExists(id);
        Rental rental = rentalRepository.findById(id).orElseThrow();
        GetRentalResponse response = mapper.map(rental, GetRentalResponse.class);
        return response;
    }

    @Override
    public GetRentalResponse returnCarFromRented(int id) {
        rules.checkIfRentalExists(id);
        Rental rental = rentalRepository.findById(id).orElseThrow();
        int carId = rental.getCar().getId();
        rules.checkIfCarAvailable(carService.getById(carId).getState());
        rental.setEndDate(LocalDateTime.now());
        carService.changeState(rental.getCar().getId(), State.AVAILABLE);
        rentalRepository.save(rental);
        GetRentalResponse response = mapper.map(rental, GetRentalResponse.class);
        return response;
    }

    @Override
    public CreateRentalResponse add(CreateRentalRequest request) {
        rules.checkIfCarAvailable(carService.getById(request.getCarId()).getState());

        maintenanceBusinessRules.checkIfCarUnderMaintenance(request.getCarId());
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(0);
        rental.setStartDate(LocalDateTime.now());

        getTotalPrice(rental);

        // Payment
        CreateRentalPaymentRequest paymentRequest = new CreateRentalPaymentRequest();
        mapper.map(request.getPaymentRequest(), paymentRequest);
        paymentRequest.setPrice(getTotalPrice(rental));
        paymentService.processRentalPayment(paymentRequest);
        carService.changeState(request.getCarId(), State.RENTED);
        rentalRepository.save(rental);
        CreateRentalResponse response = mapper.map(rental, CreateRentalResponse.class);

        //Invoice
        CreateInvoiceRequest invoiceRequest = new CreateInvoiceRequest();
        createInvoiceRequest(request, invoiceRequest, rental);
        invoiceService.add(invoiceRequest);

        return response;
    }

    @Override
    public UpdateRentalResponse update(int id, UpdateRentalRequest request) {
        rules.checkIfRentalExists(id);
        Rental rental = mapper.map(request, Rental.class);
        rental.setId(id);
        getTotalPrice(rental);
        rentalRepository.save(rental);
        UpdateRentalResponse response = mapper.map(rental, UpdateRentalResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        rules.checkIfRentalExists(id);
        makeCarAvailable(id);
        setEndDate(id);
        rentalRepository.deleteById(id);
    }

    private double getTotalPrice(Rental rental) {
        double totalPrice = rental.getDailyPrice() * rental.getRentedForDays();
        rental.setTotalPrice(totalPrice);
        return totalPrice;
    }


    private void makeCarAvailable(int id) {
        int carId = rentalRepository.findById(id).get().getCar().getId();
        carService.changeState(carId, State.AVAILABLE);
    }

    private void setEndDate(int id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        rental.setEndDate(LocalDateTime.now());
        rentalRepository.save(rental);
    }

    private void createInvoiceRequest(CreateRentalRequest request, CreateInvoiceRequest invoiceRequest, Rental rental) {
        GetCarResponse car = carService.getById(request.getCarId());
        invoiceRequest.setModelName(car.getModelName());
        invoiceRequest.setRentedAt(rental.getStartDate());
        invoiceRequest.setBrandName(car.getModelBrandName());
        invoiceRequest.setDailyPrice(request.getDailyPrice());
        invoiceRequest.setRentedForDays(request.getRentedForDays());
        invoiceRequest.setCardHolder(request.getPaymentRequest().getCardHolder());
        invoiceRequest.setPlate(car.getPlate());
        invoiceRequest.setModelYear(car.getModelYear());


    }
}
