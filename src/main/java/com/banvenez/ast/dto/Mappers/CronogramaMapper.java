package com.banvenez.ast.dto.Mappers;


import com.banvenez.ast.dto.Sorteo.JsonCronogramaDTO;

import com.banvenez.ast.dto.Sorteo.sorteoInmDTO;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CronogramaMapper implements RowMapper<JsonCronogramaDTO> {

        @Override
        public JsonCronogramaDTO mapRow(ResultSet rs, int i) throws SQLException {
            JsonCronogramaDTO resp = new JsonCronogramaDTO();
            resp.setIdSorteoCronogramaPk(rs.getInt("id_sorteo_cronograma_pk"));
            resp.setDatePay(rs.getString("datePay"));
            resp.setModalityCode(rs.getString("modalityCode"));
            resp.setProductCode(rs.getString("productCode"));
            resp.setTimePayInit(rs.getString("timePayInit"));
            resp.setTimePayEnd(rs.getString("timePayEnd"));

             return resp;
        }

}
