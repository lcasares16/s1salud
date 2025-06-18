package com.banvenez.ast.dto.Suscripcion;

import lombok.Data;

import java.util.ArrayList;


@Data

public class RespConsSuscripDto {

    private String numero_contrato;
    public ArrayList<com.banvenez.ast.dto.Suscripcion.data> data;

}


