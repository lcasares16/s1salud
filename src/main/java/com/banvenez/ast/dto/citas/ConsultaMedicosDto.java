package com.banvenez.ast.dto.citas;

import lombok.Data;

import java.util.Date;

@Data


public class ConsultaMedicosDto {
    private Integer medicoId;
    private String  cedula;
    private String  nombre;
    private String  apellido;
    private Integer especialidadId;
    private String  nombreEspecialidad;
    private String  telefono;
    private String  correoElectronico;
    private Date    fechaRegistro;
    private String  id_estatus;
    private String  estatus;
    private String  idclinica;
    private String  nombreclinica;
    private String    fechareg;

}
