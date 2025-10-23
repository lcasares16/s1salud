package com.banvenez.ast.dto.farmacia;

import lombok.Data;

@Data


public class RepInventarioDto {
    private String codarticulo;
    private String  descripcion;
    private Integer cantidad;
    private Integer codtipo;
    private Double  preciocosto;
    private Double  precioventa;
    private Double  descuento;
    private String  estatus;
    private String  fecharegistro;

}
