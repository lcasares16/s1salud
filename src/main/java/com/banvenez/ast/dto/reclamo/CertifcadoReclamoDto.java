package com.banvenez.ast.dto.reclamo;

import lombok.Data;

@Data


public class CertifcadoReclamoDto {

    private Integer idreclamo;
    private Integer cedula;
    private String  nombre;
    private Integer codigosuscripcion;
    private String  numero_contrato;
    private String  estatus;
    private String  fecha_ingreso;
    private String  parentesco;


}
