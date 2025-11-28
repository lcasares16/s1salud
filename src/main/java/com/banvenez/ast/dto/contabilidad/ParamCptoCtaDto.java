package com.banvenez.ast.dto.contabilidad;

import lombok.Data;

@Data


public class ParamCptoCtaDto {

    private String codcptoacre; // CHAR(6)
    private String idecta;      // CHAR(37)
    private String tipmovDef;   // CHAR(1)
    private String codcia;      // CHAR(2)

}
