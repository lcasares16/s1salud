package com.banvenez.ast.dto.citas;

import lombok.Data;

@Data

public class CitasBenefiDto {
    private String contrato;
    private String contratante;
    private Integer cedula;
    private String nombre;
    private String fechadesde;
    private String fechahasta;
    private Integer suscripcion;
    private String estatus;
}
