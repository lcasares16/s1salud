package com.banvenez.ast.dto.Sorteo;

import lombok.Data;

import java.util.List;
@Data
public class DatosPagoPDto {

    private respuestaDscDTO respuesta;

    private List<DataPagosDto> pagosLst;
}
