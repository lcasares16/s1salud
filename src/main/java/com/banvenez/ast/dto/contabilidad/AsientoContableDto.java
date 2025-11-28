package com.banvenez.ast.dto.contabilidad;

import lombok.Data;

@Data


public class AsientoContableDto {

    private Integer idAsiento;
    private String fechaAsiento;
    private String descripcion;
    private Integer idOperacion;
    private Integer idUsuario;
    private String estado;

}
