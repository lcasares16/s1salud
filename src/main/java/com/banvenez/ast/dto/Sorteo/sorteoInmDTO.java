package com.banvenez.ast.dto.Sorteo;


import lombok.Data;

@Data
public class sorteoInmDTO {
    private Integer id_sorteo_pk;
    private String fecha_creacion;
    private String usuario_creacion;
    private String descripcion;
    private String estatus;
    private String usuario_ejecucion;
    private String fecha_ejecucion;
    private String usuario_ult_modificacion;
    private String fecha_ult_modificacion;
    private Integer cantidad_ganadores;
}
