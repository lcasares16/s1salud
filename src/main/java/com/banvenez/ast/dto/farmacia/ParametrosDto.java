package com.banvenez.ast.dto.farmacia;

import lombok.Data;

@Data


public class ParametrosDto {
    private String fechaCreacion;
    private double islr;
    private double iva;
    private double igtf;
    private double descuentoMedico;
    private double descuentoFactura;
    private String usuarioMod;
}
