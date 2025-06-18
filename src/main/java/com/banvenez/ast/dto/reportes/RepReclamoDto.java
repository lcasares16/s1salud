package com.banvenez.ast.dto.reportes;

import lombok.Data;

@Data


public class RepReclamoDto {

    private String numerocontrato;
    private String cedula;
    private String nombre;
    private Integer idreclamo;
    private Integer certificado;
    private String fechaingreso;
    private String fecharecepcion;
    private String fechaocurrencia;
    private String status;
    private String fechaegreso;
    private Double montofacturado;
    private Integer cantidadservicios;
    private Integer cantidadinter;

}
