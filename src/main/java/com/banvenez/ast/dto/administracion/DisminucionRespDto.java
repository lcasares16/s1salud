package com.banvenez.ast.dto.administracion;


import lombok.Data;

@Data

public class DisminucionRespDto {

    private String numerocontrato;
    private Integer idreclamo;
    private String descripcionserv;
    private String descripcioninterv;
    private Double monto;
    private String plan;
    private String status;

}
