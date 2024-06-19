package com.banvenez.ast.dto.Mappers;

import com.banvenez.ast.dto.Sorteo.sorteoInmDTO;
import com.banvenez.ast.dto.Vacaciones.VacacionesDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SorteoMapper implements RowMapper<sorteoInmDTO>{

    @Override
    public sorteoInmDTO mapRow(ResultSet rs, int i) throws SQLException {

        sorteoInmDTO resp = new sorteoInmDTO();
        resp.setId_sorteo_pk(rs.getInt("id_sorteo_pk"));
        resp.setFecha_creacion(rs.getString("fecha_creacion"));
        resp.setUsuario_creacion(rs.getString("usuario_creacion"));
        resp.setDescripcion(rs.getString("descripcion"));
        resp.setEstatus(rs.getString("estatus"));
        resp.setUsuario_ejecucion(rs.getString("usuario_ejecucion"));
        resp.setFecha_ejecucion(rs.getString("fecha_ejecucion"));
        resp.setUsuario_ult_modificacion(rs.getString("usuario_ult_modificacion"));
        resp.setFecha_ult_modificacion(rs.getString("fecha_ult_modificacion"));
        resp.setCantidad_ganadores(rs.getInt("cantidad_ganadores"));
        return resp;

    }



}
