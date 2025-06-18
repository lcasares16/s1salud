package com.banvenez.ast.dto.Cobertura;


import lombok.Data;

@Data

public class BaremosSalidaDto {

    private String cod_plan;
    private String cod_ramo_cont;
    private String cod_cobertura;
    private Integer cod_servicio;
    private String descripcion;
    private Integer plazo_espera;
    private String edad_minima;
    private String edad_min_calculado;
    private String edad_maxima;
    private String edad_max_calculado;

}
