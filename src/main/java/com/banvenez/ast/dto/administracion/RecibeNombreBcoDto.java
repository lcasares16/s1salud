package com.banvenez.ast.dto.administracion;

import lombok.Data;

@Data


public class RecibeNombreBcoDto {

     private Integer idbanco;
     private String  banco;
     private String  numero_cuenta;
     private Double saldo_anterior;
     private Double saldo_actual;

}
