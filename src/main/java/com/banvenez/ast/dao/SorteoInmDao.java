package com.banvenez.ast.dao;

import com.banvenez.ast.dto.Mappers.DataPagoMappers;
import com.banvenez.ast.dto.Sorteo.DataPagosDto;
import com.banvenez.ast.dto.Sorteo.DatosPagoPDto;
import com.banvenez.ast.dto.Sorteo.SalidaJsonDscDto;
import com.banvenez.ast.dto.respuestaIntranetDto;
import com.banvenez.ast.util.Constantes;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleTypes;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class SorteoInmDao {
    private ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    private JdbcTemplate jdbcTemplate;

    public SorteoInmDao(){
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
            log.error("Excepcion en la clase y metodo " + SorteoInmDao.class.getName() + " obtenerParametros ");
            log.error("Mensaje => " + e.getMessage());
        }
        return resp;
    }

    public respuestaIntranetDto guardarTasaBcv(String tipoMoneda, String moneda){
        respuestaIntranetDto resp = new respuestaIntranetDto();
    //    moneda = moneda.replace(".", ",");
        try {
            SimpleJdbcCall simpleJdbcIntranet = new SimpleJdbcCall(jdbcTemplate);
            simpleJdbcIntranet.withFunctionName("PKG_BCV_UTIL.PRC_GUARDAR_TASA_BDV");
            simpleJdbcIntranet.withoutProcedureColumnMetaDataAccess();
            simpleJdbcIntranet.setFunction(false);

            simpleJdbcIntranet.declareParameters(
                    new SqlParameter("P_Monto", OracleTypes.VARCHAR),
                    new SqlParameter("P_TipoMoneda", OracleTypes.VARCHAR),
                    new SqlOutParameter("P_CodResp", OracleTypes.VARCHAR),
                    new SqlOutParameter("P_Mensajes", OracleTypes.VARCHAR)
            );

            SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                    .addValue("P_Monto", moneda)
                    .addValue("P_TipoMoneda", tipoMoneda);

            Map<String, Object> result = simpleJdbcIntranet.execute(sqlParameterSource);
            resp.setEstatus((String) result.get("P_CodResp"));
            resp.setMensaje((String) result.get("P_Mensajes"));

            if(resp.getEstatus().equalsIgnoreCase(Constantes.success)){
                log.info(SorteoInmDao.class.getName() + " guardarTasaBcv Exitoso");
            }else{
                log.error("Excepcion en la clase y metodo " + SorteoInmDao.class.getName() + " guardarTasaBcv ");
                log.error(resp.getMensaje());
            }

        }catch (Exception e){
            log.error("Excepcion en la clase y metodo " + SorteoInmDao.class.getName() + " guardarTasaBcv ");
            log.error("Mensaje => " + e.getMessage());
        }
        return resp;
    }

    public DatosPagoPDto consultarParametro(Double amount, String channelCode,String bankOrigin,String bankDestination, String tipoId,
                                               Integer customerOrigin,String accountOrigin,String referen) {
        DatosPagoPDto resp = new DatosPagoPDto();
        //    moneda = moneda.replace(".", ",");
        try {
            SimpleJdbcCall simpleJdbcIntranet = new SimpleJdbcCall(jdbcTemplate);
            simpleJdbcIntranet.withFunctionName("PKG_PAGOS_M_SORTEO_UTIL.PRC_EVALUA_PAGOS_GANADOR");
            simpleJdbcIntranet.withoutProcedureColumnMetaDataAccess();
            simpleJdbcIntranet.setFunction(false);

            simpleJdbcIntranet.declareParameters(
                    new SqlParameter("p_monto_eva", OracleTypes.DOUBLE),
                    new SqlParameter("p_canal_eva", OracleTypes.VARCHAR),
                    new SqlParameter("p_bco_ori_eva", OracleTypes.VARCHAR),
                    new SqlParameter("p_bco_des_eva", OracleTypes.VARCHAR),
                    new SqlParameter("p_tipo_ide_eva", OracleTypes.VARCHAR),
                    new SqlParameter("p_cedula_eva", OracleTypes.INTEGER),
                    new SqlParameter("p_cuentaDestino_pgo", OracleTypes.VARCHAR),
                    new SqlParameter("p_reference_pago", OracleTypes.VARCHAR),
                    new SqlOutParameter("p_pagos_parametro", OracleTypes.CURSOR)
            );

            simpleJdbcIntranet.returningResultSet("p_pagos_parametro", new DataPagoMappers());

            SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                    .addValue("p_monto_eva", amount)
                    .addValue("p_canal_eva", channelCode)
                     .addValue("p_bco_ori_eva", bankOrigin)
                    .addValue("p_bco_des_eva", bankDestination)
                    .addValue("p_tipo_ide_eva", tipoId)
                    .addValue("p_cedula_eva", customerOrigin)
                    .addValue("p_cuentaDestino_pgo", accountOrigin)
                    .addValue("p_reference_pago", referen);



            Map<String, Object> result = simpleJdbcIntranet.execute(sqlParameterSource);
            // resp.setRespuesta((List<DatosPagoPDto>) result.get("P_CodResp"));
           // resp.setRespuesta(((List<DatosPagoPDto>) result.get("P_CodResp_Resp")));
            //resp = ((List<DataPagosDto>) result.get("p_pagos_parametro"));
            resp.setPagosLst(((List<DataPagosDto>) result.get("p_pagos_parametro")));


            //if(resp.getEstatus().equalsIgnoreCase(Constantes.success)){
            //  log.info(SorteoInmDao.class.getName() + " guardarTasaBcv Exitoso");
            //}else{
            //  log.error("Excepcion en la clase y metodo " + SorteoInmDao.class.getName() + " guardarTasaBcv ");
            //  log.error(resp.getMensaje());
            //}

        }catch (Exception e){
            log.error("Excepcion en la clase y metodo " + SorteoInmDao.class.getName() + " guardarTasaBcv ");
            log.error("Mensaje => " + e.getMessage());
        }
        return resp;

    }



    public DatosPagoPDto insertaGanador(Double amount, String channelCode,String bankOrigin,String bankDestination, String tipoId,
                                            Integer customerOrigin,String accountOrigin,String referen) {
        DatosPagoPDto resp = new DatosPagoPDto();
        //    moneda = moneda.replace(".", ",");
        try {
            SimpleJdbcCall simpleJdbcIntranet = new SimpleJdbcCall(jdbcTemplate);
            simpleJdbcIntranet.withFunctionName("PKG_PAGOS_M_SORTEO_UTIL.PRC_INSERTA_PAGOS_GANADOR");
            simpleJdbcIntranet.withoutProcedureColumnMetaDataAccess();
            simpleJdbcIntranet.setFunction(false);

            simpleJdbcIntranet.declareParameters(
                    new SqlParameter("p_monto_eva", OracleTypes.DOUBLE),
                    new SqlParameter("p_canal_eva", OracleTypes.VARCHAR),
                    new SqlParameter("p_bco_ori_eva", OracleTypes.VARCHAR),
                    new SqlParameter("p_bco_des_eva", OracleTypes.VARCHAR),
                    new SqlParameter("p_tipo_ide_eva", OracleTypes.VARCHAR),
                    new SqlParameter("p_cedula_eva", OracleTypes.INTEGER),
                    new SqlParameter("p_cuentaDestino_pgo", OracleTypes.VARCHAR),
                    new SqlParameter("p_reference_pago", OracleTypes.VARCHAR)

            );

        //    simpleJdbcIntranet.returningResultSet("p_pagos_parametro", new DataPagoMappers());

            SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                    .addValue("p_monto_eva", amount)
                    .addValue("p_canal_eva", channelCode)
                    .addValue("p_bco_ori_eva", bankOrigin)
                    .addValue("p_bco_des_eva", bankDestination)
                    .addValue("p_tipo_ide_eva", tipoId)
                    .addValue("p_cedula_eva", customerOrigin)
                    .addValue("p_cuentaDestino_pgo", accountOrigin)
                    .addValue("p_reference_pago", referen);



            Map<String, Object> result = simpleJdbcIntranet.execute(sqlParameterSource);
            // resp.setRespuesta((List<DatosPagoPDto>) result.get("P_CodResp"));
            // resp.setRespuesta(((List<DatosPagoPDto>) result.get("P_CodResp_Resp")));
            //resp = ((List<DataPagosDto>) result.get("p_pagos_parametro"));
           // resp.setPagosLst(((List<DataPagosDto>) result.get("p_pagos_parametro")));


            //if(resp.getEstatus().equalsIgnoreCase(Constantes.success)){
            //  log.info(SorteoInmDao.class.getName() + " guardarTasaBcv Exitoso");
            //}else{
            //  log.error("Excepcion en la clase y metodo " + SorteoInmDao.class.getName() + " guardarTasaBcv ");
            //  log.error(resp.getMensaje());
            //}

        }catch (Exception e){
            log.error("Excepcion en la clase y metodo " + SorteoInmDao.class.getName() + " guardarTasaBcv ");
            log.error("Mensaje => " + e.getMessage());
        }
        return resp;

    }



}
