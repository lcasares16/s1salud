package com.banvenez.ast.util;

import com.banvenez.ast.dao.AsistenciaDao;
import com.banvenez.ast.dao.CarnetDao;
import com.banvenez.ast.dto.carnetDto;
import com.banvenez.ast.dto.respuestaIntranetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SincronizadorDataService {

    @Autowired
    CarnetDao daoCarnet;

    @Autowired
    AsistenciaDao daoAsistencia;

    public respuestaIntranetDto sincronizacionDataMasivo() {
        respuestaIntranetDto resp = new respuestaIntranetDto();
        try {
            Calendar c = Calendar.getInstance();
            Date fechaTmp = c.getTime();
            c.add(Calendar.DATE, -1);
            Date fechaValidar = c.getTime();

            String fechaFormateada = new SimpleDateFormat("yyyy-MM-dd").format(fechaValidar);

            String fechaInicio = fechaFormateada + " 00:00:00";
            String fechaFin = fechaFormateada + " 23:59:59";

            fechaInicio = "2022-06-01 00:00:00";
            fechaFin = "2022-06-01 23:59:59";
            log.info("Fechas a buscar  fecha Inicio " + fechaInicio);
            log.info("Fechas a buscar  fecha Fin " + fechaFin);
            List<carnetDto> carnet = daoCarnet.obternerRegistros(fechaInicio, fechaFin);
            if(carnet.size() > 0){
                resp = daoAsistencia.guardarEmpleados(carnet);
            }else{
                resp.setEstatus("FAIL");
                resp.setMensaje("no se obtuvo data de la base de datos de carnetizacion");
            }

        }catch ( Exception e){
            resp.setEstatus("FAIL");
            resp.setMensaje("Incoveniente en procesar la sincronizacion");
            resp.setData(e.getMessage());
            log.error("SincronizadorDataService:sincronizacionDataMasivo mensaje ==>");
            log.error(e.getMessage());
        }
        return resp;
    }
}
