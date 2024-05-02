package com.banvenez.ast.dto.Intranet;

import lombok.Data;

@Data
public class RespuestaDto {
    private String status;
    private String mensaje;
    private Object data;
}
