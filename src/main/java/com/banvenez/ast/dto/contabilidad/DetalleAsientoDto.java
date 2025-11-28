package com.banvenez.ast.dto.contabilidad;


import lombok.Data;

@Data


public class DetalleAsientoDto {

    private Integer idDetalle;
    private Integer idAsiento;
    private String idcta;
    private String tipoMov;
    private Double monto;
    private String referencia;

}
