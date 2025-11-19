package com.banvenez.ast.dto.contabilidad;

import lombok.Data;

@Data


public class MovCuentaDto {
    private String idecta;               // character(37)
    private String fecmov; // date
    private Integer ncompr; // numeric(14,0)
    private String codplani;            // character(6)
    private Integer nummov; // numeric(5,0)
    private String codcia;              // character(2)
    private String cta1;                // character(3)
    private String cta2;                // character(2)
    private String cta3;                // character(2)
    private String cta4;                // character(2)
    private String cta5;                // character(2)
    private String cta6;                // character(2)
    private String cta7;                // character(2)
    private String zoncta;              // character(6)
    private String auxcta;              // character(14)
    private String detmov;              // character(4000)
    private String tipmov;              // character(2)
    private Double moncnt; // numeric(22,2)
    private String zoncnt;              // character(4)
    private Integer recibo; // numeric(10,0)
    private String tipdoc;              // character(4)
    private String numdoc;              // character(20)
    private String fecconci; // date
    private String ingcaja;             // character(7)
    private Double corasien; // numeric(14,0)
    private String detcta2;             // character(25)
    private Integer coractiv;           // numeric(4,0)
    private String cta8;                // character(2)
    private String cta9;                // character(2)
    private String cta10;               // character(2)
    private String tipcomp;             // character(3)
    private String indauto;             // character(1)
    private String fecinc; // date
    private Integer numrel; // numeric(20,0)
    private String tipodoc;             // character(2)
    private String conciliado;          // character(1)
    private Integer ideop; // numeric(14,0)
    private String monedaoficialBsf;    // character(3)
    private Integer ncompror; // numeric(14,0)

}
