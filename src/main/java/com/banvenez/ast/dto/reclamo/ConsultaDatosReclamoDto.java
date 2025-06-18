package com.banvenez.ast.dto.reclamo;


import lombok.Data;

@Data


public class ConsultaDatosReclamoDto {

    private String tipo_poliza;
    private Integer certificado_titular;
    private String numero_contrato;
    private String nombre_contratante;
    private String nombre_titular;
    private String codigo_plan;
    private String descricion_plan;
    private String nombre_beneficiario;
    private Integer cedula_beneficiario;
    private String parentesco_beneficiario;
    private String edad;
    private String ciudad_titular;
    private String sexo;
    private String responsable;
    private String matrix;
    private String codigo_suscripcion;
    private String subramo;


}
