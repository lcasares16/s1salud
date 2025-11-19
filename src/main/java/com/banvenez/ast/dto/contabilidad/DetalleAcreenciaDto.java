package com.banvenez.ast.dto.contabilidad;

import lombok.Data;

@Data


public class DetalleAcreenciaDto {
    private Integer numacre;              // numeric(14,0)
    private Integer numdetacre;           // numeric(3,0)
    private String codgrupoacre;                       // character(6)
    private String codcptoacre;                        // character(6)
    private String codmoneda;                          // character(3)
    private Double mtodetacrelocal;      // numeric(14,2)
    private String natcptoacre;                        // character(1)
    private Double mtocptoacre;          // numeric(14,2)
    private Double porccptoacre;         // numeric(8,4)
    private Double mtodetacremoneda;     // numeric(14,2)
    private Double sldodetacrelocal;     // numeric(14,2)
    private Double sldodetacremoneda;    // numeric(14,2)

}
