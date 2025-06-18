package com.banvenez.ast.dto.Bcv;

import lombok.Data;

@Data

public class RecibeTasaBcvDto {

    private String codmoneda;
    private String nombre;
    private String fechaoperacion;
    private Double montoventa;
    private Double montocompra;
}
