package com.banvenez.ast.dto.Seguridad;

import lombok.Data;

@Data


public class ValidaDatosUser {
    private String cod_respuesta;
    private String descripcion;
    private Integer cod_aplicacion;
    private String nombre;
    private String apellido;
    private String perfil;
    private Integer cedula;
    private String clave;
    private String correo;
}
