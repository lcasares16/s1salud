package com.banvenez.ast.dto;

import lombok.Data;

@Data
public class InsertAplicDto {
  private String descripcion_aplicativo;
  private String  acronimo_aplicativo ;
  private String nombre_imagen_aplicativo;
  private String dir_url_aplicativo;
  private String resumen_aplicativo;
  private String activo_aplicativo ;
  private String ima_archivo_aplicativo ;
  private Integer cod_libra;

}
