package com.banvenez.ast.dto.reportes;


import lombok.Data;

@Data


public class RepContratoDto {

    private String numerocontrato;
    private String nombre;
    private String cedula;
    private String fechacreacion;
    private String fechadesde;
    private String fechahasta;
    private String estatus;
    private String tipo;

}
