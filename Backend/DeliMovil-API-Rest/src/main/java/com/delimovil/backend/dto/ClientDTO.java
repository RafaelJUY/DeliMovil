package com.delimovil.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Min;
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
public class ClientDTO {
    @NotNull
    @Min(1)
    private Integer id;

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


    @Size(min = 3, max = 250)
    private String imageUrl;
}
