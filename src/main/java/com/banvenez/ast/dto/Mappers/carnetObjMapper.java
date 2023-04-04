package com.banvenez.ast.dto.Mappers;

import com.banvenez.ast.dto.carnetDto;

import java.util.List;

public class carnetObjMapper {

    public Object[] transformaListaObj(List<carnetDto> data){
        Object[]  struct = new Object[data.size()];
        int arrayIndex = 0;
        for(carnetDto user : data){
            Object[] reportArray = new Object[9];
            reportArray[0] = user.getFecha() == null ? "  " : user.getFecha();
            reportArray[1] = user.getNumCarnet() == null ? "  " : user.getNumCarnet();
            reportArray[2] = user.getEmpleado() == null ? "  " : user.getEmpleado();
            reportArray[3] = user.getCodUsuario() == null ? "  " : user.getCodUsuario();
            reportArray[4] = user.getCargo() == null ? "  " : user.getCargo();
            reportArray[5] = user.getDepartamento() == null ? "  " : user.getDepartamento();
            reportArray[6] = user.getPanel() == null ? "  " : user.getPanel();
            reportArray[7] = user.getLectora() == null ? "  " : user.getLectora();
            reportArray[8] = user.getCi() == null ? "  " : user.getCi();
            struct[arrayIndex++] = reportArray;
        }
        return struct;
    }

}
