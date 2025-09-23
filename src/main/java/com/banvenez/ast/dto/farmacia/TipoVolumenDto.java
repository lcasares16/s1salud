package com.banvenez.ast.dto.farmacia;

import lombok.Data;

@Data


public class TipoVolumenDto {
    private Integer  codvolumen;
    private String   descripcion;
    private String   status;
    private String   fechacreacion;
    private String   usuario;
    private Integer  volumen;
    private Integer idaplicativo;
    private Double  precio;

}
