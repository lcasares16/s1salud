package com.banvenez.ast.dto.Sorteo;


import lombok.Data;

import java.util.ArrayList;

@Data
public class ResulatdoPushDTO {

    public String estatus;
    public Object mensaje;
    public ArrayList<dataPushDTO> data;

}
