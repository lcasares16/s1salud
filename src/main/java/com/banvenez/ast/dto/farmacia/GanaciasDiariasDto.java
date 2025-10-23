package com.banvenez.ast.dto.farmacia;

import lombok.Data;

@Data


public class GanaciasDiariasDto {

    private Integer idcliente;
    private String  fechaemision;
    private Integer cedula;
    private String nombre;
    private String telefonocel;
    private String codarticulo;
    private String descripcion;
    private Double precioventa;
    private Double preciocosto;
    private Double ganancia;
    private String cajero;
}
