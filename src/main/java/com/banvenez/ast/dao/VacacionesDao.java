package com.banvenez.ast.dao;


import com.banvenez.ast.dto.Mappers.VacacionesMappers;
import com.banvenez.ast.dto.Vacaciones.VacacionesDTO;
import com.banvenez.ast.dto.respuestaIntranetDto;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleTypes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
@Slf4j
public class VacacionesDao {

    private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private JdbcTemplate jdbcTemplate;

    public VacacionesDao(){
        super();
        this.jdbcTemplate = (JdbcTemplate) context.getBean("jdbctemplateVAC");
    }
    public respuestaIntranetDto consultarSolicitudesPorVencer(){
        respuestaIntranetDto resp =  new respuestaIntranetDto();
        try {
            SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
            simpleJdbcCall.withFunctionName("PKG_VAC_CONSULTAS.PRC_CONSULTAR_SOLICITUDES_VEN");
            simpleJdbcCall.withoutProcedureColumnMetaDataAccess();
            simpleJdbcCall.setFunction(false);
            simpleJdbcCall.declareParameters(
                    new SqlOutParameter("P_CodResp",OracleTypes.VARCHAR),
                    new SqlOutParameter("P_Mensajes", OracleTypes.VARCHAR),
                    new SqlOutParameter("P_Resultado", OracleTypes.CURSOR)
            );
            simpleJdbcCall.returningResultSet("P_Resultado", new VacacionesMappers());

            Map<String, Object> resultMap = simpleJdbcCall.execute();
            resp.setEstatus((String) resultMap.get("P_CodResp"));
            resp.setMensaje((String) resultMap.get("P_Mensajes"));

            if(resp.getEstatus().equalsIgnoreCase("SUCCESS")){
                resp.setData(resultMap.get("P_Resultado"));
            }else{
                resp.setData(null);
                log.error("VacacionesDao:consultarSolicitudesPorVencer mensaje ==>");
                log.error(resp.getMensaje());
            }
        }catch (Exception e){
            log.error("VacacionesDao:consultarSolicitudesPorVencer mensaje ==>");
            log.error(e.getMessage());
        }
        return resp;
    }

}
