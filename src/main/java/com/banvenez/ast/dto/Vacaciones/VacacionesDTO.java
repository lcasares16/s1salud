package com.banvenez.ast.dto.Vacaciones;

import lombok.Data;

@Data
public class VacacionesDTO {

    private Integer idSolicitud;
    private String codigoUsuario;
    private String cedula;
    private String nombres;
    private String codUnidad;
    private String unidad;
    private String codigoUsuarioSupervisor;
    private String cedulaSupervisor;
    private String nombresSupervisor;
    private String codunidadSupervisor;
    private String unidadSupervisor;
    private String fechaVacaciones;
    private Integer cantidadDias;
    private String estatusSolicitud;
}
