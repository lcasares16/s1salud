package com.banvenez.ast.dto.administracion;

import lombok.Data;

@Data


public class RetornaReferenciaDto {

    private String fechapago;
    private String formapago;
    private String referenciapago;
    private Double monto;
    private String moneda;
    private String correo;
    private String contrato;
    private Integer suscripcion;
    private Integer idreferencia;
    private String  banco;
}
