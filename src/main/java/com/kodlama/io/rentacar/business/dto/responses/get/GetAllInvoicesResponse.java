package com.kodlama.io.rentacar.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllInvoicesResponse {
    private int id;
    private String paymentCardHolder;
    private String rentalCarModelName;
    private String rentalCarModelBrandName;
    private String rentalCarPlate;
    private int rentalCarModelYear;
    private double rentalDailyPrice;
    private double rentalTotalPrice;
    private int rentalRentedForDays;
    private LocalDateTime rentalStartDate;

}
