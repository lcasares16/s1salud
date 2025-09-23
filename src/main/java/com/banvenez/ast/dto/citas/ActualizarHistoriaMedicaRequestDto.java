package com.banvenez.ast.dto.citas;

import lombok.Data;

@Data


public class ActualizarHistoriaMedicaRequestDto {
    private String diagnostico;
    private String tratamiento;
    private String notasAdicionales;
    private String archivosAdjuntos; // Could be String for JSON, or a more complex type

    // Constructors
    public ActualizarHistoriaMedicaRequestDto() {
    }

    public ActualizarHistoriaMedicaRequestDto(String diagnostico, String tratamiento, String notasAdicionales, String archivosAdjuntos) {
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.notasAdicionales = notasAdicionales;
        this.archivosAdjuntos = archivosAdjuntos;
    }
}
