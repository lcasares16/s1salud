package com.banvenez.ast.dto.administracion;


import lombok.Data;

@Data


public class EntradaCobroCuotasDto {

    private String  p_numero_contrato;
    private Integer p_codigo_suscricion;
    private Integer p_cantidad;
    private Integer cuota;
    private Double montobs;
    private Double montodiv;
    private String fechacobro;
    private String usuariomod;
}
