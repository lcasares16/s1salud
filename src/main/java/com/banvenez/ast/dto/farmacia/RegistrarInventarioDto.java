package com.banvenez.ast.dto.farmacia;

import lombok.Data;

import java.math.BigDecimal;

@Data


public class RegistrarInventarioDto {

    private String  codarticulo;
    private String  descripcion;
    private Integer  codtipo;
    private Long  cantidad;
    private BigDecimal   preciocosto;
    private BigDecimal   precioventa;
    private Double  descuento;
    private Integer volumen;
    private String  status;
    private String  fechamod;
    private String  usuario;
    private Integer codaplicativo;
    private String  fecharegistro;
    private String  descstatus;

}
