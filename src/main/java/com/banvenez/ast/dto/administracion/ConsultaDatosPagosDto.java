package com.banvenez.ast.dto.administracion;

import lombok.Data;

@Data


public class ConsultaDatosPagosDto {

    private String contrato;
    private String nombre;
    private Integer suscripcion;
    private String fechadesde;
    private String fechahasta;
    private String estatus;


}
