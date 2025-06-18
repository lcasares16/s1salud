package com.banvenez.ast.dto.Contratos;

import lombok.Data;

import java.util.Date;


@Data

public class ContratoAuxDto {
    private String numero_contrato;
    private String nombre;
    private String rif;
    private String fecha_creacion;
    private String responsable;
    private String contacto;
    private String nit;
    private String agente;
    private String observacion_agente;
    private String direccion;
    private String observacion;
    private String fecha_desde;
    private String fecha_hasta;
    private String fecha_desde_exceso;
    private String fecha_hasta_exceso;
    private String status;
    private String fecha_anulacion;
    private Double porcentaje_comision;
    private Double comision_qualitas;
    private Double comision_agente;
    private String observaciones_qualitas;
    private Double monto_restitucion;
    private String exento_iva;
    private String cont_especial;
    private String sucursal;
    private String forma_pago;
    private String tipo;
    private String vigencia_desde;
    private String vigencia_hasta;
    private Integer codigo_sucursal;
    private String cod_ramo;
    private String cod_sub_ramo;
    private String modificado_por;
    private String fecha_modificacion;
    private String fecha_modif;
    private String maquina;
    private String hecho_por;
    private String origen;
    private String beneficiario;
    private String matrix;
    private Integer auxiliar;
    private String cod_loc;
    private String estado_cuenta;
    private String asistencia;
    private String ambulancia;
    private String fecha_asistencia;
    private String fecha_ambulancia;
    private String fecha_registro;
    private String atencion;
    private String qualimed;
    private String fecha_atencion;
    private String fecha_qualimed;
    private String tipo_qualimed;
    private Integer cantidad_qualimed;
    private String masivo;
    private String digitalizacion ;
    private String poliza_fronti;
    private String nombre_web;
    private String tipo_producto_web;
    private String cronico;
    private String mil_razones;
    private Double comision_qualimed;
    private String tipo_cobranza;
    private Integer codigo_sucursal_operativa;
    private String huma_ticket;
    private String cod_fact;
    private String proveedor_amd;
    private String facturacion_global;
    private String orden_compra;
    private String fecha_tope_sntro;
    private String factura_nombre_de;
    private String rif_factura;
    private String direccion_fiscal_fact;
    private String telf_factura;
    private String activa_sntros;
}
