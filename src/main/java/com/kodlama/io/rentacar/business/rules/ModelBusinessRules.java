package com.kodlama.io.rentacar.business.rules;

import com.kodlama.io.rentacar.common.constants.Messages;
import com.kodlama.io.rentacar.core.exceptions.BusinessException;
import com.kodlama.io.rentacar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private final ModelRepository repository;

    public void checkIfModelExists(int id) {
        if (!repository.existsById(id)) throw new BusinessException(Messages.Model.NotExists);
    }
}
