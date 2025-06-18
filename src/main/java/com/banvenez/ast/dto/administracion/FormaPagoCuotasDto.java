package com.banvenez.ast.dto.administracion;

import com.banvenez.ast.dto.Contratos.FormaPagoDto;
import lombok.Data;

import java.util.List;

@Data


public class FormaPagoCuotasDto {
    private Integer p_suscripcion;
    private String  contrato;
    private Double  p_monto_bs_total;
    private Double  p_monto_usd_total;
    private Double  p_monto_eur_total;
    private Integer p_cantidad_total;
    private List<EntradaCobroCuotasDto> detallecuota;
    private List<EntredaReferenciaDto> detallepago;

}
