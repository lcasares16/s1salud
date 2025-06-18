package com.banvenez.ast.util;


import com.banvenez.ast.dto.Bcv.MonedaDTO;
import com.banvenez.ast.dto.RecRespStringDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Service
public class BcvServices {
    public String pmoneda;
    @Autowired
    ServicosHttps httpsServicio;

 ;

    public RecRespStringDto guardarTasaBcv(){
        RecRespStringDto resp = new RecRespStringDto();

        try {
            MonedaDTO moneda =  httpsServicio.servicioBcv();

            LocalDateTime datetime = LocalDateTime.now();
            LocalDateTime n = datetime.plusMinutes(30);
            ZonedDateTime caracas = n.atZone(ZoneId.of("America/Guyana"));


            ConnectionUtil db = new ConnectionUtil();

            if(moneda.getCodigo().equals("USD")){
                pmoneda = "02";

            }

            String fecha = "2024-25-10";

            resp = db.ActualizaBcv(pmoneda,moneda.getCompra(), fecha);



            if(moneda.getCompra() == null){
                log.error("BcvServices:guardarTasaBcv "+ resp.getRetorno());

            }

        }catch (Exception e){
            log.error("BcvServices:guardarTasaBcv");
            log.error(e.getMessage());

        }
//        this.enviarCorreos(correo);
        return resp;
    }



}


