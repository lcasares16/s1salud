package com.banvenez.ast.dto.citas;


import lombok.Data;

@Data

public class CrearHistoriaMedicaRequestDto {

    private Integer citaId; // Optional, can be null
    private Integer pacienteId; // Required
    private Integer medicoId; // Required
    private String diagnostico;
    private String tratamiento;
    private String notasAdicionales;
    private String archivosAdjuntos; // Could be String for JSON, or a more complex type

    // Constructors
    public CrearHistoriaMedicaRequestDto() {
    }

    public CrearHistoriaMedicaRequestDto(Integer citaId, Integer pacienteId, Integer medicoId, String diagnostico, String tratamiento, String notasAdicionales, String archivosAdjuntos) {
        this.citaId = citaId;
        this.pacienteId = pacienteId;
        this.medicoId = medicoId;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.notasAdicionales = notasAdicionales;
        this.archivosAdjuntos = archivosAdjuntos;
    }


}
