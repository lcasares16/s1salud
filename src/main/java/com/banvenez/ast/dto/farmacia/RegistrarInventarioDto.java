package com.banvenez.ast.dto.farmacia;

import lombok.Data;

@Data


public class RegistrarInventarioDto {

    private String  codarticulo;
    private String  descripcion;
    private Integer  codtipo;
    private Integer cantidad;
    private Double  preciocosto;
    private Double  precioventa;
    private Double  descuento;
    private Integer volumen;
    private String  status;
    private String  fechamod;
    private String  usuario;
    private Integer codaplicativo;
    private String  fecharegistro;
    private String  descstatus;

}
