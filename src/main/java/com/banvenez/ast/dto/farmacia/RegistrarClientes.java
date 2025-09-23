package com.banvenez.ast.dto.farmacia;

import lombok.Data;

@Data


public class RegistrarClientes {
    private Integer idcliente;
    private String  tipoidentificacion;
    private Integer cedula;
    private String  nombres ;
    private String  apellidos;
    private String  estado;
    private String  municipio;
    private String  parroquia;
    private String  comunidad;
    private String  calleavenida;
    private String  casaapto;
    private String  direccion;
    private String  telefonoloc;
    private String  telefonocel;
    private String  correo;
    private String  fechanacimiento;
    private String  observaciones;
    private String  fechacreacion;
    private String  estatus;
    private String  credito;
    private Integer codaplicativo;
    private String  fechamod;
    private String  usuario;
    private String edificio;
    private String residencia;

}
