package com.kodlama.io.rentacar.adapters;

import com.kodlama.io.rentacar.business.abstracts.PosService;
import com.kodlama.io.rentacar.common.constants.Messages;
import com.kodlama.io.rentacar.core.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePosServicecAdapter implements PosService {
    @Override
    public void pay() {
        boolean isPaymentSuccessful = new Random().nextBoolean();
        if (!isPaymentSuccessful) throw new BusinessException(Messages.Payment.Failed);
    }
}
