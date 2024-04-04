package com.banvenez.ast;

import com.banvenez.ast.dto.respuestaIntranetDto;

import com.banvenez.ast.util.VacacionesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@Slf4j
public class AstWSApplication {

//    @Autowired
//    SincronizadorDataService sincronizadorService;

    @Autowired
    VacacionesService vacacionesService;

    public static void main(String[] args) {
        SpringApplication.run(AstWSApplication.class, args);
    }


    @Scheduled(cron="${cron.sincronizadorD}")
    public void sincronizacionData(){
        long tiempoInicial = System.currentTimeMillis();
                long tiempoFinal = 0;
        try {
      //      respuestaIntranetDto resp = sincronizadorService.sincronizacionDataMasivo();
          //  log.info("Respuesta de sincronizacion " + resp);
             tiempoFinal = System.currentTimeMillis();
        }catch (Exception e){
            log.error("AstWSApplication:sincronizacionData  ==>");
            log.error(e.getMessage());
        }
        log.info("Tiempo Estimado del Proceso  Cron sincronizadorD  ==> "+ ((new Double((tiempoFinal-tiempoInicial))/1000)/60)+" minutos");
    }



    public void notificacionesVacaciones(){
        long tiempoInicial = System.currentTimeMillis();
        long tiempoFinal = 0;
        try {
            respuestaIntranetDto resp = vacacionesService.gestionVacacionesVencidas();
            tiempoFinal = System.currentTimeMillis();
            if(resp.getEstatus().equalsIgnoreCase("SUCCESS")){
                log.info(resp.getMensaje());
            }else{
                log.error(resp.getMensaje());
            }
        }catch (Exception e){
            log.error("AstWSApplication:notificacionesVacaciones  ==>");
            log.error(e.getMessage());
        }
        log.info("Tiempo Estimado del Proceso  Cron sincronizadorD  ==> "+ ((new Double((tiempoFinal-tiempoInicial))/1000)/60)+" minutos");
    }

}
