package com.banvenez.ast.dto.farmacia;

import lombok.Data;

@Data


public class DetFacturaDto {
    public Integer iddetallefactura;
    public String  factura;
    public Integer idcliente;
    public String  idarticulo;
    public String  descripcion;
    public Integer cantidad;
    public Double  preciounitario;
    public Double  preciounitariodolar;
    public Double  preciounitarioeuro;
    public Double  descuento;
    public Double  descuentodolar;
    public Double  descuentoeuro;
    public Double  subtotal;
    public Double  subtotaldolar;
    public Double  subtotaleuro;
    public String  status;
    public String  fechamod;
    public String  usuario;
    public Integer codaplicativo;
    public Integer volumen;
}
