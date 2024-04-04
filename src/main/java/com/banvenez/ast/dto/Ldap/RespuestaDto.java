package com.banvenez.ast.dto.Ldap;

import lombok.Data;

import java.util.List;

@Data
public class RespuestaDto {
    private String status;
    private String mensaje;
    private UsuarioDto usuario;
    private List<UsuarioDto> usuariosLts;
}
