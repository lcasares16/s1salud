package com.banvenez.ast.dto.Mappers;

import com.banvenez.ast.dto.Sorteo.EntradaTrab;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;




public class DataEntPeopleMapper implements RowMapper<EntradaTrab>{
    @Override
    public EntradaTrab mapRow(ResultSet rs, int rowNum) throws SQLException {
        EntradaTrab resp = new EntradaTrab();

        resp.setCedula(rs.getString("cedula"));

        return resp;
    }
    }