package com.banvenez.ast.dto.citas;


import lombok.Data;

import java.util.Date;

@Data

public class HistoriaMedicaDto {
    private Integer historiaId;
    private Integer citaId;
    private Integer pacienteId;
    private Integer medicoId;
    private Date fechaCreacion;
    private String diagnostico;
    private String tratamiento;
    private String notasAdicionales;
    private String archivosAdjuntos; // Could be String for JSON, or a more complex type




}
