package com.banvenez.ast.dto.citas;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class CrearCitaRequestDto {
    private Integer pacienteId;
    private Integer medicoId;
    private Date    fechaHora;
    private String  motivo;
    private String  notasPaciente;

}
