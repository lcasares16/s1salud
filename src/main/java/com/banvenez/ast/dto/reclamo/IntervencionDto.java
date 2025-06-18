package com.banvenez.ast.dto.reclamo;

import lombok.Data;

@Data

public class IntervencionDto {
    private Integer cod_intervencion;
    private String descripcion;
    private Double monto;
    private String tipo_reclamo;
}
