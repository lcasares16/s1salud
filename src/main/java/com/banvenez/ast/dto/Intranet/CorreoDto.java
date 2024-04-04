package com.banvenez.ast.dto.Intranet;

import lombok.Data;

@Data
public class CorreoDto {
    private String remitente;
    private String destino;
    private String asunto;
    private String mensaje;
}
