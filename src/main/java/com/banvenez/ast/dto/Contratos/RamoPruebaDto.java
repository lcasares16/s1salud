package com.banvenez.ast.dto.Contratos;

import lombok.Data;

import java.util.ArrayList;


@Data

public class RamoPruebaDto {

    private String cod_ramo_pol;
    public ArrayList<com.banvenez.ast.dto.Contratos.data> data;

}
