package com.kodlama.io.rentacar.business.dto.responses.requests.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateModelRequest {
    private String name;
    private int brandId;

}
