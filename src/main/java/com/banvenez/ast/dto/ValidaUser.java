package com.banvenez.ast.dto;


import lombok.Data;

@Data
public class ValidaUser {

    private String cod_respuesta;
    private String descripcion;
    private Integer cod_aplicacion;
    private String nombre;
    private String apellido;
    private String perfil;
    private String token;
}
