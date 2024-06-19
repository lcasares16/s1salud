package com.banvenez.ast.dto.Sorteo;

import lombok.Data;

@Data
public class DataPagosDto {
    public String accion;
    public String codigoCanal;
    public String cuentaOrigen;
    public String cuentaDestino;
    public String nio;
    public Integer nroMovimientoAnulacion;
    public double monto;
    public String serial;
    public String codOperacionDebito;
    public String codOperacionCredito;
    public String refInterna;
    public String usuario;
    public String moneda;
    public String observacionDebe;
    public String observacionHaber;
    public String datosUAF;
    public String masDatos;
    public String user;


}
