package com.banvenez.ast.dto.administracion;


import lombok.Data;

@Data


public class ConsultaFacturaDto {

    private String factura;
    private String fecha_emision;
    private String numero_contrato;
    private String descripcion;
    private Double monto_facturado;
    private Double porcentaje_retencion;
    private Double monto_retenido;
    private Double monto_neto;
    private String status;
    private Integer codigo_sucursal;
    private Double monto_bs;
    private Double monto_retenido_bs;
    private Double monto_neto_bs;
    private Integer certificado;
}
