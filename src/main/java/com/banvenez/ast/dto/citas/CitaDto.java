package com.banvenez.ast.dto.citas;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class CitaDto {
    private Integer citaId;
    private Integer pacienteId;
    private String  pacienteNombreCompleto;
    private Integer medicoId;
    private String  medicoNombreCompleto;
    private Integer  especialidad;
    private String  medicoEspecialidad;
    private String fechaHora;
    private String  motivo;
    private String  notasMedico;
    private String  notasPaciente;
    private String    fechaCreacion;
    private String    fechaActualizacion;
    private String    fechadesde;
    private String    fechahasta;
    private String  numerocontrato;
    private Integer  codigosuscripcion;
    private Integer  cedula;
    private Integer  claveclinica;
    private Integer  clinicaid;
    private String nombreclinica;
    private Integer  estado;
    private String   estatus;

}
