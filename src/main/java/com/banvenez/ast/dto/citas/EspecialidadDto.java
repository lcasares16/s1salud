package com.banvenez.ast.dto.citas;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EspecialidadDto {
    private Integer especialidadId;
    private String nombre;
    private String descripcion;

}
