package com.banvenez.ast.dto.Suscripcion.reportes;

import lombok.Data;

@Data


public class RetornaBenefiDto {
    private String numerocontrato;
    private String nombre;
    private String fechadesde;
    private String fechahasta;
    private String statussuscripcion;
    private String sexo;
    private String estadocivil;
    private String  parentesco;
    private String statuscontrato;
    private String fechanacimiento;
    private Integer cuotaspendiente;
    private Double sumaasegurada;
    private Double gastosclinicas;
    private Double saldoactual;
    private String plan;
    private Double gastoscitas;
    private Double gastosfarmacia;



}
