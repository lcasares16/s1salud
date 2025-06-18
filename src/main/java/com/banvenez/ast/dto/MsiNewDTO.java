package com.banvenez.ast.dto;

import lombok.Data;

@Data
public class MsiNewDTO {

    private Integer cod_menu;
    private String DES_MENU;
    private String DIR_MENU;
    private Integer MEN_PADRE;
    private Integer COD_APLICATIVO;
    private Integer ORD_MENU;
    private Integer ACTIVO;
}
