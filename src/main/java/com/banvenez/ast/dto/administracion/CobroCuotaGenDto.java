package com.banvenez.ast.dto.administracion;

import lombok.Data;

@Data


public class CobroCuotaGenDto{

    private Integer pSuscripcion;
    private String  contrato;
    private Double  pMontoBsTotal;
    private Double  pMontoUsdTotal;
    private Double  pMontoEurTotal;
    private Integer pCantidadTotal;

}
