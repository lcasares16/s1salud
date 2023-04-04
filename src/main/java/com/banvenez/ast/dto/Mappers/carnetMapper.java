package com.banvenez.ast.dto.Mappers;

import com.banvenez.ast.dto.carnetDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class carnetMapper implements RowMapper<carnetDto> {

    @Override
    public carnetDto mapRow(ResultSet rs, int i) throws SQLException {
        carnetDto usuario = new carnetDto();
            usuario.setNumCarnet(rs.getString("Num_Tarjeta"));
            usuario.setEmpleado(rs.getString("Empleado"));
            usuario.setCi(rs.getString("CI"));
            usuario.setCargo(rs.getString("Cargo"));
            usuario.setDepartamento(rs.getString("Departamento"));
            usuario.setFecha(rs.getString("Fecha_Evento"));
            usuario.setPanel(rs.getString("Panel"));
            usuario.setLectora(rs.getString("Lectora"));
            usuario.setCodUsuario(rs.getString("Num_Empleado"));
        return usuario;
    }
}
