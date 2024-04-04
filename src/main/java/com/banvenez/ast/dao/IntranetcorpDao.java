package com.banvenez.ast.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.Map;

@Repository
@Slf4j
public class IntranetcorpDao {
    private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private JdbcTemplate jdbcTemplate;

    public IntranetcorpDao(){
        super();
        jdbcTemplate = (JdbcTemplate) context.getBean("jdbctemplateIntranetCorp");
    }


    public String obtenerParametros(String contextoParametro, String valorContexto, String nombreParametro){
        SimpleJdbcCall simpleJdbcIntranet = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcIntranet.withFunctionName("PKG_INTRANET_UTIL.OBTENER_PARAMETRO");
        simpleJdbcIntranet.withoutProcedureColumnMetaDataAccess();
        simpleJdbcIntranet.setFunction(true);

        simpleJdbcIntranet.declareParameters(
                new SqlOutParameter("v_valorParametro",	Types.VARCHAR),
                new SqlParameter("v_contextoParametro", Types.VARCHAR),
                new SqlParameter("v_valorContexto", Types.VARCHAR),
                new SqlParameter("v_nombreParametro", Types.VARCHAR)
        );
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("v_contextoParametro", contextoParametro)
                .addValue("v_valorContexto", valorContexto)
                .addValue("v_nombreParametro", nombreParametro);
        simpleJdbcIntranet.compile();
        String resp = "";
        try {
            Map<String, Object> result = simpleJdbcIntranet.execute(sqlParameterSource);
            resp = (String)result.get("v_valorParametro");
        }catch (Exception e){
            log.error("Excepcion en la clase y metodo " + IntranetcorpDao.class.getName() + " obtenerParametros ");
            log.error("Mensaje => " + e.getMessage());
        }
        return resp;
    }
}
