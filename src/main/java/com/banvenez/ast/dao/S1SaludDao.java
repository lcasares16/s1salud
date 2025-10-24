package com.banvenez.ast.dao;


import com.banvenez.ast.dto.AplicDto;
import com.banvenez.ast.util.DatabaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class S1SaludDao {


    private DatabaseConfig databaseConfig;
   // private JdbcTemplate jdbcTemplate;

    public List<AplicDto> consultarSorteo() {
        //SorteoInmPDTO resp = new SorteoInmPDTO();
        //    moneda = moneda.replace(".", ",");
        List<AplicDto> resp =   new ArrayList<>();
        try {

            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(databaseConfig.jdbcTemplate());
            jdbcCall.withFunctionName("msint.aplicacion_listado_completo");
            Map<String, Object> result = jdbcCall.execute();

            List<Map<String, Object>> datosEmpleados = (List<Map<String, Object>>) result.get("emp_cursor");









        }catch (Exception e){

            log.error("Mensaje => " + e.getMessage());
        }
        return resp;

    }


}
