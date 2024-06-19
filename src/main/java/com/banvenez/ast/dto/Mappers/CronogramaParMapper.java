package com.banvenez.ast.dto.Mappers;

import com.banvenez.ast.dto.Sorteo.CronoParDatosDTO;
import com.banvenez.ast.dto.Sorteo.CronogramaPDTO;
import com.banvenez.ast.dto.Sorteo.JsonCronogramaDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CronogramaParMapper implements RowMapper<CronoParDatosDTO> {

    @Override
    public CronoParDatosDTO mapRow(ResultSet rs, int i) throws SQLException {
        CronoParDatosDTO resp = new CronoParDatosDTO();

        resp.setId_sorteo(rs.getInt("id_sorteo"));
        resp.setHora_inicio(rs.getString("hora_inicio_par"));
        resp.setHora_fin(rs.getString("hora_fin_par"));

        return resp;
    }

}
