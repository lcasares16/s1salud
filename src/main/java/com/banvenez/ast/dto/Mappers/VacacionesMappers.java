package com.banvenez.ast.dto.Mappers;

import com.banvenez.ast.dto.Vacaciones.VacacionesDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class VacacionesMappers implements RowMapper<VacacionesDTO> {
    @Override
    public VacacionesDTO mapRow(ResultSet rs, int i) throws SQLException {
        VacacionesDTO resp = new VacacionesDTO();
            resp.setIdSolicitud(rs.getInt("ID_SOLICITUD_PK"));
            resp.setCodigoUsuario(rs.getString("COD_USUARIO_EMP"));
            resp.setCedula(rs.getString("CEDULA_EMP"));
            resp.setNombres(rs.getString("NOMBRE_EMP") + " " + rs.getString("APELLIDO_EMP"));
            resp.setCodUnidad(rs.getString("UNIDAD_EMP"));
            resp.setUnidad(rs.getString("DESC_UNIDAD_EMP"));
            resp.setCodigoUsuarioSupervisor(rs.getString("COD_USUARIO_SUP"));
            resp.setCedulaSupervisor(rs.getString("CEDULA_SUP"));
            resp.setNombresSupervisor(rs.getString("NOMBRE_SUP")+" " +rs.getString("APELLIDO_SUP"));
            resp.setCodunidadSupervisor(rs.getString("UNIDAD_SUP"));
            resp.setUnidadSupervisor(rs.getString("DESC_UNIDAD_SUP"));
            resp.setFechaVacaciones(rs.getString("FECHA_VACACIONES"));
            resp.setCantidadDias(rs.getInt("CANTIDAD_DIAS"));
            resp.setEstatusSolicitud(rs.getString("ESTATUS_SOLICITUD"));
        return null;
    }
}
