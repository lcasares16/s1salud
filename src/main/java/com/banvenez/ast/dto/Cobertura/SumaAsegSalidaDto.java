package com.banvenez.ast.dto.Cobertura;

import lombok.Data;

@Data

public class SumaAsegSalidaDto {

    private String cod_ramo_cont;
    private String cod_cobertura;
    private String descripcion;
    private Double suma_asegurada;
    private Double montomensual;


}
