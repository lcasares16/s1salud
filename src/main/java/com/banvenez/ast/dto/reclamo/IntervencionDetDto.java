package com.banvenez.ast.dto.reclamo;

import lombok.Data;

@Data


public class IntervencionDetDto {

    private Integer cod_intervencion;
    private String descripcion;
    private double monto;
    private String tipo_reclamo;

}
