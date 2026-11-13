package com.eymm.mmeyact4_t4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String nombre;
    private String email;
}
