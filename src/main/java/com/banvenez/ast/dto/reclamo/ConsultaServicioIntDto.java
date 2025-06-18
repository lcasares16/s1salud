package com.banvenez.ast.dto.reclamo;

import lombok.Data;

@Data


public class ConsultaServicioIntDto {

    private Integer codigoTipoServicio;
    private String descripcionServ;
    private Integer codigointervencion;
    private String descripcionInterv;
    private Double monto;
}
