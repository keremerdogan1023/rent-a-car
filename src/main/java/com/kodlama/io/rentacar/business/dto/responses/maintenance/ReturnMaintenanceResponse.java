package com.kodlama.io.rentacar.business.dto.responses.maintenance;

import com.kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnMaintenanceResponse {
    private int carId;
    private State state;
}

