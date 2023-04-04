package com.banvenez.ast.dao;

import com.banvenez.ast.dto.Mappers.carnetObjMapper;
import com.banvenez.ast.dto.carnetDto;
import com.banvenez.ast.dto.respuestaIntranetDto;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleTypes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jdbc.support.oracle.SqlArrayValue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
@Slf4j
public class AsistenciaDao {

    private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private JdbcTemplate jdbcTemplate;

    public AsistenciaDao(){
        super();
        this.jdbcTemplate = (JdbcTemplate) context.getBean("jdbctemplateAst");
    }

    public respuestaIntranetDto guardarEmpleados(List<carnetDto> data){
        respuestaIntranetDto resp = new respuestaIntranetDto();

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall.withFunctionName("PKG_ASISTENCIA_UTIL.PRC_INSERTAR_DATA_CARNET");
        simpleJdbcCall.withoutProcedureColumnMetaDataAccess();
        simpleJdbcCall.setFunction(false);
        simpleJdbcCall.declareParameters(
                new SqlParameter("P_Carnet", OracleTypes.ARRAY, "ASISTENCIA.ARREGLO_CARNETIZACION" ),
                new SqlOutParameter("P_CodResp",OracleTypes.VARCHAR),
                new SqlOutParameter("P_Mensajes", OracleTypes.VARCHAR),
                new SqlOutParameter("P_Fallas", OracleTypes.VARCHAR)
        );

        carnetObjMapper transformar = new carnetObjMapper();
        Object[] arrayObjList = transformar.transformaListaObj(data);

        Map in = new HashMap();
        in.put("P_Carnet", new SqlArrayValue(arrayObjList));

        try {
            Map<String, Object> resultMap = simpleJdbcCall.execute(in);
            String  validar = (String) resultMap.get("P_CodResp");
            if (validar.equals("SUCCESS")){
                resp.setEstatus("SUCCESS");
                resp.setMensaje("Data guarda exitosamente");
                resp.setData(null);
            }else {
                resp.setEstatus("WARNING");
                resp.setMensaje("Se proceso la carga de la data con excepcion de  " + (String) resultMap.get("P_Fallas"));
                resp.setData(null);
            }

            log.info("AsistenciaDao:guardarEmpleados guardando empleados en el sistema de asistencia");
            log.info(resp.toString());

        }catch (Exception e){
            resp.setEstatus("FAIL");
            resp.setMensaje("Incovenientes en guardar masivamente en la base de datos");
            resp.setData(e.getMessage());
            log.error("AsistenciaDao: guardarEmpleados Mensaje ==> ");
            log.error(e.getMessage());
            log.error("Data resul " + resp.toString());
        }
        return  resp;
    }

    public respuestaIntranetDto consolidarDataFinal(){
        respuestaIntranetDto resp = new respuestaIntranetDto();
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall.withFunctionName("PKG_ASISTENCIA_UTIL.PRC_SINCRONIZAR_DATA_CARNET");
        simpleJdbcCall.withoutProcedureColumnMetaDataAccess();
        simpleJdbcCall.setFunction(false);

        try {
            simpleJdbcCall.execute();
            resp.setEstatus("SUCCESS");
            resp.setMensaje("Data sincronizada exitomamente");
        }catch (Exception e){
            resp.setEstatus("FAIL");
            resp.setMensaje("Incovenientes en guardar masivamente en la base de datos");
            resp.setData(e.getMessage());
            log.error("AsistenciaDao: consolidarDataFinal Mensaje ==> ");
            log.error(e.getMessage());
            log.error("Data resul " + resp.toString());
        }
        return resp;
    }


}
