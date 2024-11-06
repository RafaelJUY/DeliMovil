package com.delimovil.backend.shared.configuration.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

//Clase S4
// Clase para devolver el token
@Getter
@AllArgsConstructor
public class JwtResponse {

    private final String jwtToken;

}

