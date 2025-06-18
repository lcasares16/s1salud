package com.banvenez.ast.dto.reclamo;

import lombok.Data;

import java.util.List;

@Data

public class ReclamosRequestDto {
    private List<DetReclamoDto> reclamos;

}
