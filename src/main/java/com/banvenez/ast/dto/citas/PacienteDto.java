package com.banvenez.ast.dto.citas;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PacienteDto {
    private Integer pacienteId;
    private String cedula;
    private String nombre;
    private String apellido;
    private Date   fechaNacimiento;
    private String telefono;
    private String correoElectronico;
    private String direccion;
    private Date   fechaRegistro;

}
