package com.banvenez.ast.dto.Sorteo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class RespuestPCIDto {

    private int code;
    private String message;
    private dataPciDto data;
    private String status;
}


