package com.banvenez.ast.dto.administracion;

import lombok.Data;

@Data


public class SalidaCobranzaDto {

    private String contrato;
    private String contratante;
    private Integer codigosuscricion;
    private String telefono;
    private Integer cuota;
    private String fechapago;
    private Double montocuota;
    private String fechapromesa;

}
