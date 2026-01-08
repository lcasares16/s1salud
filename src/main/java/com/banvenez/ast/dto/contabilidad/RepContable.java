package com.banvenez.ast.dto.contabilidad;

import lombok.Data;

@Data


public class RepContable {

    private String idecta;
    private String codigocuenta;
    private String nombrecuenta;
    private Double debitos;
    private Double creditos;
    private Double saldo;
    private String tipo;
    private String codigo;
    private Double total;
    private String concepto;
    private Double monto;

    private String fecha;
    private Integer nrocomprobante;
    private String tipocomprobante;
    private String detalle;
}
