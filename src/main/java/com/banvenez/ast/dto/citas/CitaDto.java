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
    private String  medicoEspecialidad;
    private String fechaHora;
    private String  motivo;
    private String  estado;
    private String  notasMedico;
    private String  notasPaciente;
    private Date    fechaCreacion;
    private Date    fechaActualizacion;
    private Date    fechadesde;
    private Date    fechahasta;
    private String  numerocontrato;
    private Integer  codigosuscripcion;
    private Integer  cedula;

}
