package com.banvenez.ast.dto.Suscripcion.reportes;


import lombok.Data;

@Data


public class ConsultaRepReciboDto {

    private String contrato;
    private String fechedesde;
    private String nombrecontratante;
    private Integer rif;
    private String direccion;
    private String plan;
    private Double cuotamensual;
    private Double cuotamensualfami;
    private Double montoanual;
    private String telf_factura;
    private Integer p_total_afilial;

}
