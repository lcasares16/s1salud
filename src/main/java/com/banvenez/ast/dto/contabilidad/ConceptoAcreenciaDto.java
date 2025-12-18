package com.banvenez.ast.dto.contabilidad;

import lombok.Data;

@Data


public class ConceptoAcreenciaDto {

    private String codGrupoAcre;        // character(6) NOT NULL
    private String codCptoAcre;         // character(6) NOT NULL
    private String descCptoAcre;        // character(50) NOT NULL
    private String natCptoAcre;         // character(1)
    private String indTipoCpto;         // character(1)
    private Double porcCptoAcre;    // numeric(7,4)
    private Double mtoCptoAcre;     // numeric(14,2)
    private String indAuto;             // character(1)
    private String indGenOper;          // character(1)
    private String tipoId;              // character(3)
    private String numId;                 // numeric(10,0)
    private String dvId;                // character(1)
    private String codCptoAcreCoa;      // character(6)
    private String codGrupoAcreCoa;     // character(6)
    private String indGtoMan;           // character(1)
    private String indImpGtoMan;        // character(1)
    private String indCptoPServicio;    // character(1)
    private String indCptoIServicio;    // character(1)
    private String tipoacre;

}
