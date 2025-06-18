package com.banvenez.ast.dto.reclamo;

import lombok.Data;

import java.math.BigDecimal;


@Data



public class DetReclamoDto {

    private Integer idReclamo;
    private String numeroContrato;
    private String fechaMovimiento;
    private Integer tipoServicio;
    private Integer tipoIntervecion;
    private BigDecimal monto;
    private String estatus;
}
