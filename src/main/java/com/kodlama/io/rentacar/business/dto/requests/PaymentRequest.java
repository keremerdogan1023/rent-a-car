package com.kodlama.io.rentacar.business.dto.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    @NotBlank(message = "Kart numarası boş bırakılamaz")
    @Length(min = 16, max = 16, message = "Kart Numarası 16 haneden oluşmak zorunda.")
    private String cardNumber;

    @NotBlank
    @Length(min = 5)
    private String cardHolder;

    @Min(value = 2023)
    private int cardExpirationYear;

    @Max(value = 12)
    @Min(value = 1)
    private int cardExpirationMonth;

    @NotBlank
    @Length(min = 3, max = 3)
    private String cardCvv;

}
