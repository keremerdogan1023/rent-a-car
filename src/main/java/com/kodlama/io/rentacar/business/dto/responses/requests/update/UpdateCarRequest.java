package com.kodlama.io.rentacar.business.dto.responses.requests.update;

import com.kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
    private int modelYear;
    private String plate;
    private double dailyPrice;
    private State state;
    private int modelId;
}
