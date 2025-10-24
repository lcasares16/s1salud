package com.banvenez.ast.dto.Seguridad;

import lombok.Data;

@Data

public class RegistrarUserDto {
    private String  ideUsuario;
    private Integer docUsuario;
    private String  nomUsuario;
    private String  apeUsuario;
    private String  emaUsuario;
    private Integer estUsuario;
    private String  password;
    private String  fechaNacimiento;
    private String  tipoIdentificacion;
    private String  telefonoLocal;
    private String  celular;

}
