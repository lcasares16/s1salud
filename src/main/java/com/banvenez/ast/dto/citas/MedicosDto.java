package com.banvenez.ast.dto.citas;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MedicosDto {
    private Integer medicoId;
    private String  cedula;
    private String  nombre;
    private String  apellido;
    private Integer especialidadId;
    private String  nombreEspecialidad;
    private String  telefono;
    private String  correoElectronico;
    private Date fechaRegistro;
    private String  estatus;

}
