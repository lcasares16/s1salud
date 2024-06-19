package com.banvenez.ast;

import com.banvenez.ast.dao.SorteoInmDao;
import com.banvenez.ast.dto.Sorteo.*;
import com.banvenez.ast.dto.respuestaIntranetDto;

import com.banvenez.ast.util.BcvServices;
import com.banvenez.ast.util.CronogramaHttp;
import com.banvenez.ast.util.SincronizadorDataService;
import com.banvenez.ast.util.VacacionesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@Slf4j
public class AstWSApplication {

    @Autowired
    SincronizadorDataService sincronizadorService;

    @Autowired
    VacacionesService vacacionesService;

    @Autowired
    BcvServices bcvservicios;

    @Autowired
    CronogramaHttp cronograma;


    public static void main(String[] args) {
        SpringApplication.run(AstWSApplication.class, args);
    }


    @Scheduled(cron="${cron.sincronizadorD}")
    public void sincronizacionData(){
        long tiempoInicial = System.currentTimeMillis();
                long tiempoFinal = 0;
        try {
            respuestaIntranetDto resp = sincronizadorService.sincronizacionDataMasivo();
            log.info("Respuesta de sincronizacion " + resp);
             tiempoFinal = System.currentTimeMillis();
        }catch (Exception e){
            log.error("AstWSApplication:sincronizacionData  ==>");
            log.error(e.getMessage());
        }
        log.info("Tiempo Estimado del Proceso  Cron sincronizadorD  ==> "+ ((new Double((tiempoFinal-tiempoInicial))/1000)/60)+" minutos");
    }


    @Scheduled(cron="${cron.vacaciones}")
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
        log.info("Tiempo Estimado del Proceso  Cron cron.vacaciones  ==> "+ ((new Double((tiempoFinal-tiempoInicial))/1000)/60)+" minutos");
    }

    @Scheduled(cron="${cron.bcv}")
    public void sincronizacionBcv(){
        long tiempoInicial = System.currentTimeMillis();
        long tiempoFinal = 0;
        try {
            respuestaIntranetDto resp = bcvservicios.guardarTasaBcv();
            log.info("Respuesta de sincronizacionBcv " + resp);
            if(resp.getEstatus().equalsIgnoreCase("SUCCESS")){
                log.info(resp.getMensaje());
            }else{
                log.error(resp.getMensaje());
            }
            tiempoFinal = System.currentTimeMillis();
        }catch (Exception e){
            log.error("AstWSApplication:sincronizacionBcv  ==>");
            log.error(e.getMessage());
        }
        log.info("Tiempo Estimado del Proceso  Cron sincronizadorD  ==> "+ ((new Double((tiempoFinal-tiempoInicial))/1000)/60)+" minutos");
    }
    private ParametroPDTO consultaParametro = new ParametroPDTO();
    @Scheduled(cron="${cron.cronograma}")
    public void CronogramaParametros(){
        long tiempoInicial = System.currentTimeMillis();
        long tiempoFinal = 0;

        SorteoInmDao daoMultiInm = new SorteoInmDao();
        try {

            log.info("Inicio llamada del servivio");

            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date();
            log.info("Hora actual: " + dateFormat.format(date));
            //String prueba = "11:02:15" ;
            consultaParametro =  daoMultiInm.obtenerDatos();


                Integer parametro1 = Integer.parseInt(consultaParametro.getLstPara().get(0).getHoraIncio().replace(":",""));
                Integer parametro2 = Integer.parseInt(dateFormat.format(date).replace(":",""));
            //   Integer parametro2 = Integer.parseInt(prueba.replace(":",""));

              if (parametro1 <= parametro2){

                  SalidaJsonDscDto resp = cronograma.CronogramaEntrada(dateFormat.format(date));
              //   SalidaJsonDscDto resp = cronograma.CronogramaEntrada(prueba);

                  log.info("Respuesta de llamado al servicio Dsc" + resp);

                  log.info("Finalizo llamada del servivio");
                  if(resp.getCode() == 1000){
                      log.info(resp.getMessage());
                      log.info(resp.getStatus());
                  }else{log.info("Fallando en el inicio del llamado al DSC");
                      log.info(resp.getMessage());
                      log.error(resp.getStatus());
                  }


              }else{ log.info("No existe sorteo activo para esta hora: " + dateFormat.format(date));

              }



            tiempoFinal = System.currentTimeMillis();
        }catch (Exception e){
            log.error("AstWSApplication:sincronizacionBcv  ==>");
            log.error(e.getMessage());
        }

        log.info("Tiempo Estimado del Proceso  Cron sincronizadorD  ==> "+ ((new Double((tiempoFinal-tiempoInicial))/1000)/60)+" minutos");

    }




}
