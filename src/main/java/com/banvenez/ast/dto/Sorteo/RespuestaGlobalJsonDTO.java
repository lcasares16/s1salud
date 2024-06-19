package com.banvenez.ast.dto.Sorteo;

import lombok.Data;

@Data
public class RespuestaGlobalJsonDTO {
    private RespuestaAmiDTO response;
    private String responseInternalId;
    private String authType;
    private String data;

}
