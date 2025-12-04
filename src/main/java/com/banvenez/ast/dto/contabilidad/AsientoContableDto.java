package com.banvenez.ast.dto.contabilidad;

import lombok.Data;

@Data


public class AsientoContableDto {

    private Integer idAsiento;
    private String fechaAsiento;
    private String descripcion;
    private String idOperacion;
    private String idUsuario;
    private String estado;
    private Integer idorigen;
    private String codcia;
    private Double monto;

}
