package com.banvenez.ast.dto.Sorteo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CronogramaPDTO {

    private  List<JsonCronogramaDTO> CronoLst;

    private List<CronoParDatosDTO> CronoParLst;

}
