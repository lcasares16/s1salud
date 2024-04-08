package com.banvenez.ast.dao;

import com.banvenez.ast.dto.Mappers.carnetMapper;
import com.banvenez.ast.dto.carnetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class CarnetDao {

    private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private JdbcTemplate jdbcTemplate;

    public CarnetDao(){
        super();
        this.jdbcTemplate = (JdbcTemplate) context.getBean("jdbctemplateCarnet");
    }

    public List<carnetDto> obternerRegistros(String fechaInicio, String fechaFin){
        List<carnetDto> resp = new ArrayList<carnetDto>();
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall.withProcedureName("PCSC_DB.dbo.PRC_OBTENER_EMPLEADOS");
        simpleJdbcCall.withoutProcedureColumnMetaDataAccess();
        simpleJdbcCall.setFunction(false);

        simpleJdbcCall.declareParameters(
                new SqlParameter("Pinicio", Types.VARCHAR),
                new SqlParameter("Pfin", Types.VARCHAR)
        );

        simpleJdbcCall.returningResultSet("result1", new carnetMapper());

        MapSqlParameterSource in = new MapSqlParameterSource()
                .addValue("Pinicio", fechaInicio)
                .addValue("Pfin", fechaFin);

        try {
            Map<String, Object> resultMap = simpleJdbcCall.execute(in);
            resp = (List<carnetDto>) resultMap.get("result1");

        } catch (Exception e) {
            log.error("CarnetDao:obternerRegistros error ==>");
            log.error("error: " + e.getMessage());
        }
        return resp;


    }

}
