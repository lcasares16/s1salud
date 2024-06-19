package com.banvenez.ast.dto.Mappers;

import com.banvenez.ast.dto.Sorteo.DataPagosDto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataPagoMappers implements RowMapper<DataPagosDto> {


    @Override
    public DataPagosDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        DataPagosDto resp = new DataPagosDto();
            resp.setAccion(rs.getString("accion"));
            resp.setCodigoCanal(rs.getString("codigoCanal"));
            resp.setCuentaOrigen(rs.getString("cuentaOrigen"));
            resp.setCuentaDestino(rs.getString("cuentaDestino"));
            resp.setNio(rs.getString("nio"));
            resp.setNroMovimientoAnulacion(rs.getInt("nroMovimientoAnulacion"));
            resp.setMonto(rs.getInt("monto"));
            resp.setSerial(rs.getString("serial"));
            resp.setCodOperacionDebito(rs.getString("codOperacionDebito"));
            resp.setCodOperacionCredito(rs.getString("codOperacionCredito"));
            resp.setRefInterna(rs.getString("efInterna"));
            resp.setUsuario(rs.getString("usuario"));
            resp.setMoneda(rs.getString("moneda"));
            resp.setObservacionDebe(rs.getString("observacionDebe"));
            resp.setObservacionHaber(rs.getString("observacionHaber"));
            resp.setDatosUAF(rs.getString("datosUAF"));
            resp.setMasDatos(rs.getString("masDatos"));
            resp.setUser(rs.getString("useri"));
        return resp;
    }
}
