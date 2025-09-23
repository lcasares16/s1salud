package com.banvenez.ast.dto.farmacia;

import lombok.Data;

@Data


public class FormaPagoFDto {
    private Integer formapago;
    private String  fechapago;
    private String  referenciapago;
    private Double  monto;
    private String  codmoneda;
    private String  correo;
    private String  factura;
    private Integer idcliente;
    private String  fechamod;
    private String  usuariomod;
    private Integer idreferencia;
    private Integer idbanco;
    private Integer status;
    private Integer codaplicativo;
    private Integer idpuntoventa;
    private String descripcionbco;
    private String estatus_factura;
    private String descripcionfp;
}
