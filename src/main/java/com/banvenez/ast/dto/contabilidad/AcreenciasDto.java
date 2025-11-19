package com.banvenez.ast.dto.contabilidad;

import lombok.Data;

@Data


public class AcreenciasDto {

    private Integer numacre;         // numeric(14,0)
    private String stsacre;                       // character(3)
    private String fecsts;           // date
    private String tipoacre;                      // character(3)
    private String codmoneda;                     // character(3)
    private Double mtoacrelocal;    // numeric(14,2)
    private Double mtoacremoneda;   // numeric(14,2)
    private String fecvencacre;      // date
    private Double sldoacrelocal;   // numeric(14,2)
    private Double sldoacremoneda;  // numeric(14,2)
    private String codinterlider;                 // character(6)
    private String tipoid;                        // character(3)
    private Integer numid;           // numeric(9,0)
    private String dvid;                          // character(1)
    private Integer idefact;         // numeric(14,0)
    private String dptoemi;                       // character(6)
    private String indtarj;                       // character(2)
    private String indgenrec;                     // character(1)
    private String textacre;                      // character(1000)
    private Integer seriecttorea;    // numeric(4,0)

}
