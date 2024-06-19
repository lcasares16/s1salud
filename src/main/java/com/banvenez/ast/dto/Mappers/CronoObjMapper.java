package com.banvenez.ast.dto.Mappers;

import com.banvenez.ast.dto.Sorteo.JsonCronogramaDTO;
import com.banvenez.ast.dto.carnetDto;

import java.util.List;

public class CronoObjMapper {

    public Object[] CronoListaObj(List<JsonCronogramaDTO> data){
        Object[]  struct = new Object[data.size()];
        int arrayIndex = 0;
        for(JsonCronogramaDTO user : data){
            Object[] reportArray = new Object[9];
            reportArray[0] = user.getDatePay() == null ? "  " : user.getDatePay();
            reportArray[1] = user.getModalityCode() == null ? "  " : user.getModalityCode();
            reportArray[2] = user.getProductCode() == null ? "  " : user.getProductCode();
            reportArray[3] = user.getTimePayInit() == null ? "  " : user.getTimePayInit();
            reportArray[4] = user.getTimePayEnd() == null ? "  " : user.getTimePayEnd();

            struct[arrayIndex++] = reportArray;
        }
        return struct;
    }
}
