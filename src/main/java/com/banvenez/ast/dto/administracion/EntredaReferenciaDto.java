package com.banvenez.ast.dto.administracion;

import lombok.Data;

@Data


public class EntredaReferenciaDto {

    private String fechapago;
    private Integer formapago;
    private String referenciapago;
    private Double monto;
    private String codigomoneda;
    private String correo;
    private String contrato;
    private Integer codigosuscripcion;
    private Integer banco;

}
