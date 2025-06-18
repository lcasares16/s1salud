package com.banvenez.ast.dto.administracion;


import lombok.Data;

@Data


public class SalidaDisminucionDto {

    private String contrato;
    private String contratante;
    private String fechadesde;
    private String fechahasta;
    private Integer cedula;
    private Integer codigosuscricion;
    private Double sumaasegurada;
    private Double gastosclinicas;
    private Double saldoactual;

}
