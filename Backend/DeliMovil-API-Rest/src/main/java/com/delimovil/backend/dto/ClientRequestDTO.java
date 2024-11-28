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
public class ClientRequestDTO {

    @Size(min = 2, max = 45)
    private String first_name;

    @Size(min = 2, max = 45)
    private String last_name;


    @Size(min = 6, max = 20)
    private String phone;

    @Size(min = 6, max = 45)
    private String latitude;

    @Size(min = 6, max = 45)
    private String longitude;

    @Size(min = 3, max = 45)
    private String name_street;


    @Size(min = 1, max = 45)
    private String number_street;

    @Size(min = 1, max = 5)
    private String floor_department;


}
