package com.kodlama.io.rentacar.business.dto.responses.create;

import com.kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarResponse {
    private int id;
    private int modelId;
    private int modelYear;
    private double dailyPrice;
    private String plate;
    private State state;

}
