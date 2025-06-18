package com.banvenez.ast.dto.administracion;

import lombok.Data;

@Data


public class ConsultaCronoDto {

    private Integer nuemrocuota;
    private String fechapago;
    private Double cuotamensualdiv;
    private Double cuotamensualbs;
    private String estatus;
    private Double cuotaanual;
    private String fechaefectivapago;
    private Double tasa;



}
