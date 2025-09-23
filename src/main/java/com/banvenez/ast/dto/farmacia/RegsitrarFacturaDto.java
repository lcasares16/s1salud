package com.banvenez.ast.dto.farmacia;

import lombok.Data;

import java.util.List;

@Data


public class RegsitrarFacturaDto {
    private String factura;
    private String  fechaemision;
    private Integer idcliente;
    private String  idarticulo;
    private Integer cantidad;
    private Double  precioventa;
    private Double  descuento;
    private String  descripcion;
    private Double  preciounitario;
    private Double  preciounitariodolar;
    private Double  preciounitarioeuro;
    private Double  subtotal;
    private Double  subtotaldolar;
    private Double  subtotaleuro;
    private Double  montosubtotal;
    private Double  montosubtotaldolar;
    private Double  montosubtotaleuro;
    private Double  montoiva;
    private Double  montoivadolar;
    private Double  montoivaeuro;
    private Double  montoigtf;
    private Double  montoigtfdolar;
    private Double  montoigtfeuro;
    private Double  montototal;
    private Double  montototaldolar;
    private Double  montototaleuro;
    private String  status;
    private String  fechamod;
    private String  usuario;
    private Integer codaplicativo;
    private String  despacho;
    private List<DetFacturaDto> detallefactura;
    private List<FormaPagoDto> detallepago;

}
