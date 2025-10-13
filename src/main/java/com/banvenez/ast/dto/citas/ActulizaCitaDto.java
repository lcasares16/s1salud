package com.banvenez.ast.dto.citas;

import lombok.Data;

@Data


public class ActulizaCitaDto {

   private Integer citaid;
   private String  fecha_hora;
   private Integer  estado;
   private String  usuario;

}
