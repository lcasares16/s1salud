package com.banvenez.ast.dto.administracion;

import lombok.Data;

@Data


public class PromesaPagoDto {

    private String fechapromesa;
    private String observaciones;
    private Integer suscripcion;
    private Integer cuota;

}
