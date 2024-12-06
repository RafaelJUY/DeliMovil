package com.delimovil.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

//    @NotNull
    private Integer idUser;

    @Pattern(regexp = "^ROLE_.*$", message = "El rol debe comenzar con 'ROLE_'")
    private String roleName;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 50)
    @JsonProperty(value = "user_name")
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 10, max = 60)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

//    @NotNull
    private boolean enabled;
}

