package com.banvenez.ast.dto.contabilidad;

import lombok.Data;

@Data


public class DetalleObligacionDto {
    private Integer numoblig;
    private Integer numdetoblig;
    private String codgrupooblig;
    private String codcptooblig;
    private String codmoneda;
    private Double mtodetobliglocal;
    private String natcptooblig;
    private Double mtodetobligmoneda;
    private Double porccptooblig;
    private Double sldodetobliglocal;
    private Double sldodetobligmoneda;

}
