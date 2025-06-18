package com.banvenez.ast.dto.reclamo;

import lombok.Data;

@Data


public class ActualizaServDto {

    private Integer idreclamo;
    private Integer tiposervicio;
    private Integer tipointervencion;
    private Double  monto;

}
