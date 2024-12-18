package com.delimovil.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestDTO {

    @Size(min = 2, max = 45)
    private String firstName;

    @Size(min = 2, max = 45)
    private String lastName;

    @Size(min = 2, max = 20)
    private String phone;

}
