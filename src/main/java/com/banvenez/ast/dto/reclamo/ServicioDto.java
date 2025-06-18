package com.banvenez.ast.dto.reclamo;

import lombok.Data;

import java.util.List;

@Data


public class ServicioDto {

    private int servicio;
    private String descripcion;
    private List<IntervencionDetDto> listIntev;

}
