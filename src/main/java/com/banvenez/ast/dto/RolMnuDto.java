package com.banvenez.ast.dto;

import lombok.Data;

@Data
public class RolMnuDto {
    private Integer CODIGO_MENU;
    private String DESCRIPCION_MENU;
    private String DIRECCION_MENU;
    private Integer MENU_PADRE;
    private Integer CODIGO_APLICATIVO;
    private Integer ORDEN_MENU;
    private Integer ACTIVO;
    private Integer PADRE;
}
