package com.kodlama.io.rentacar.business.rules;

import com.kodlama.io.rentacar.common.constants.Messages;
import com.kodlama.io.rentacar.core.exceptions.BusinessException;
import com.kodlama.io.rentacar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository repository;

    public void checkIfCarExists(int id) {
        if (!repository.existsById(id)) throw new BusinessException(Messages.Car.NotExists);
    }
}
