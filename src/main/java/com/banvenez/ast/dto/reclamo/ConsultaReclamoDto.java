package com.banvenez.ast.dto.reclamo;

import lombok.Data;

@Data


public class ConsultaReclamoDto {
    private Integer  id_reclamo;
    private Integer  codigo_suscripcion;
    private String  codigo_intervencion;
    private String  codigo_tipo_servicio;
    private String  codigo_medico;
    private String  codigo_clinica;
    private String  fecha_ingreso;
    private Integer  numero_servicio;
    private String  tipo_poliza;
    private String  hecho_por;
    private String  aprobado_por;
    private String  fecha_recepcion;
    private String  fecha_ocurrencia;
    private String  status;
    private String  departamento;
    private String  tipo_cheque;
    private String  fecha_egreso;
    private String  fecha_pago;
    private Integer  clase_pago;
    private String  codigo_medico_opinion;
    private String  numero_aval_clave;
    private Double  deducible;
    private Double  gastos_clinica;
    private Double  gastos_no_amparados_neto;
    private Double  honorarios_medicos;
    private Double  gastos_ambulatorios;
    private Double  descuento_clinicas;
    private Double  descuento_medico;
    private Double  descuento_neto;
    private Double  porcentaje_descuento;
    private Double  porcentaje_reembolso;
    private Double  monto_facturado;
    private Double  monto_pagado;
    private Double  neto_a_pagar;
    private Double  total_a_pagar;
    private Double  total_facturado;
    private Double  total_elegible;
    private Double  sub_total;
    private Double  islr_neto;
    private String  autorizado;
    private String  envio_carta;
    private String  fecha_envio_administracion;
    private String  fecha_envio_factura;
    private String  fecha_recepcion_factura;
    private Double  descuento_clinica_compromiso;
    private Double  descuento_medico_compromiso;
    private String  numero_factura;
    private String  monto_facturado_letra;
    private String  monto_pagado_letra;
    private String  diagnostico;
    private String  comentarios;
    private String  observaciones_auditado;
    private String  segunda_opinion_medica;
    private Double  ahorro_opinion;
    private String  observaciones_opinion;
    private String  fecha_registro;
    private Double  ajuste_baremo;
    private String  medico_auditor;
    private Integer  factura;
    private Integer  numero;
    private Double  iva;
    private Integer  control;
    private Integer  ca_rel;
    private String  tipo_reclamo;
    private Double  iva_retenido;
    private Integer  porcentaje_retencion;
    private Integer  comprobante;
    private String  numero_contrato;
    private String  hecho_por_fact;
    private String  modificado_por;
    private String  fecha_modificacion;
    private String  fecha_modif;
    private String  maquina;
    private String  celular;
    private String  cod_area;
    private String  fecha_valor;
    private String  fecha_recepcion_atc;
    private Integer  referencia_atc;
    private String  codigo_filial;
    private String  centro_costo;
    private Integer  nu_servicio_unico;
    private String  fecha_emision_factura;
    private String  remesa;
    private String  operador;
    private String  fecha_operador;
    private String  cod_cobertura;
    private String  factura_global;
    private Integer numero_finiquito;


}
