package com.banvenez.ast.dto;

import lombok.Data;

@Data
public class CreMnuDto {
    private String descripcion_menu;
    private String direccion_menu;
    private Integer padre_menu;
    private Integer codigo_aplicativo;
    private Integer activo_menu;
    private Integer padremenuii;
}
