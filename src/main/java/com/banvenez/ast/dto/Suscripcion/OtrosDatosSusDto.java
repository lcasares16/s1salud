package com.banvenez.ast.dto.Suscripcion;

import lombok.Data;

@Data


public class OtrosDatosSusDto {

    private Integer certificado;
    private String numero_contrato;
    private String nombre_contratante;
    private String nombre_titular;
    private String codigo_plan;
    private String descripcion_plan;
    private String fecha_desde;
    private String fecha_hasta;
}
