package com.banvenez.ast.dto.reclamo;

import lombok.Data;

import java.util.List;

@Data

public class ListaTipoServicioDto {

    private Integer cod_servicio;
    private String descripcion;
    private List<IntervencionesDto> listIntev;


}
