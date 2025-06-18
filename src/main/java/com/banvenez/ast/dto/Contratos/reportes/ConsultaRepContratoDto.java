package com.banvenez.ast.dto.Contratos.reportes;

import lombok.Data;

@Data


public class ConsultaRepContratoDto {

    private String contrato;
    private String contratante;
    private String representante;
    private String tipo_documento;
    private Integer cedula;
    private String beneficiario;


}
