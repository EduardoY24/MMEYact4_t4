package com.eymm.mmeyact4_t4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VideojuegoResponse {

    private Integer id;
    private String titulo;
    private String genero;
    private Double precio;
    private String plataforma;
}
