package com.banvenez.ast.dto.citas;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class CrearCitaRequestDto {

    private Integer citaId;
    private Integer pacienteId;
    private Integer medicoId;
    private String  fecha;
    private String  motivo;
    private Integer estatus;
    private String  notasMedico;
    private String  notasPaciente;
    private Date    fechaCreacion;
    private Date    fechaActualizacion;
    private String  contrato;
    private Integer suscripcion;
    private Integer cedula;
    private String  clinica;

}
