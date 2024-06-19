package com.banvenez.ast.dto.Mappers;

import com.banvenez.ast.dto.Sorteo.ParametrosEntDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class planCronoMapper implements RowMapper<ParametrosEntDTO> {

@Override
public ParametrosEntDTO mapRow(ResultSet rs, int i) throws SQLException {
    ParametrosEntDTO resp = new ParametrosEntDTO();
        resp.setHoraIncio(rs.getString("HORA_INICIO"));
        resp.setHoraFin(rs.getString("HORA_FIN"));


        return resp;
        }
}
