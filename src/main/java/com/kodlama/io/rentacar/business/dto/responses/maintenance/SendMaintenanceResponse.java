package com.kodlama.io.rentacar.business.dto.responses.maintenance;

import com.kodlama.io.rentacar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SendMaintenanceResponse {
    private int carId;
    private State state;
}
