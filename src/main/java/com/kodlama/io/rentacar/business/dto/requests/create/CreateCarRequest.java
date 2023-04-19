package com.kodlama.io.rentacar.business.dto.requests.create;

import com.kodlama.io.rentacar.common.constants.Regex;
import com.kodlama.io.rentacar.common.utils.annotations.NotFutureYear;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
    private int modelId;
    @Min(1)
    private double dailyPrice;
    @Min(1998)
    @NotFutureYear
    private int modelYear;
    @Pattern(regexp = Regex.Plate, message = "Plate pattern is incorrect")
    private String plate;


}
