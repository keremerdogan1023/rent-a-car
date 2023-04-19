package com.kodlama.io.rentacar.business.rules;

import com.kodlama.io.rentacar.common.constants.Messages;
import com.kodlama.io.rentacar.core.exceptions.BusinessException;
import com.kodlama.io.rentacar.entities.enums.State;
import com.kodlama.io.rentacar.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository rentalRepository;

    public void checkIfRentalExists(int id) {
        if (!rentalRepository.existsById(id))
            throw new BusinessException(Messages.Rental.NotExists);
    }

    public void checkIfCarAvailable(State state) {
        if (!state.equals(State.AVAILABLE)) {
            throw new BusinessException(Messages.Car.NotAvailable);
        }
    }

    public void checkIfNotCarUnderRented(State state) {
        if (!state.equals(State.RENTED))
            throw new BusinessException(Messages.Car.NotRented);
    }
}
