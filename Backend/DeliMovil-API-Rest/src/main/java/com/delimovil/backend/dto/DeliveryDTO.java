package com.delimovil.backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {
    @NotNull
    @Min(1)
    private Integer id;
    @NotBlank
    @Size(min = 2, max = 45)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 45)
    private String lastName;
    @NotBlank
    @Size(min = 2, max = 20)
    private String phone;

    @Size(min = 3, max = 250)
    private String imageUrl;
}
