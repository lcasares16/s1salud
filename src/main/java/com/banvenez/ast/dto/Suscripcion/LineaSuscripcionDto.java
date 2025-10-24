package com.banvenez.ast.dto.Suscripcion;

import lombok.Data;

@Data


public class LineaSuscripcionDto {

    private String numero_contrato;
    private Integer  cedula;
    private Integer  certificado;
    private String  nombre;
    private String fecha_nacimiento;
    private String sexo;
    private String parentesco;
    private String  status;


}
