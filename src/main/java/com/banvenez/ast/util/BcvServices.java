package com.banvenez.ast.util;

import com.banvenez.ast.dao.IntranetcorpDao;
import com.banvenez.ast.dto.Bcv.MonedaDTO;
import com.banvenez.ast.dto.Intranet.CorreoDto;
import com.banvenez.ast.dto.Intranet.RespuestaDto;
import com.banvenez.ast.dto.respuestaIntranetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class BcvServices {

    @Autowired
    ServicosHttps httpsServicio;

    @Autowired
    IntranetcorpDao daoIntranet;

    @Autowired
    ConexionesServicios servicios;

    public respuestaIntranetDto guardarTasaBcv(){
        respuestaIntranetDto resp = new respuestaIntranetDto();
        CorreoDto correo = new CorreoDto();
        correo.setDestino("intranet_bdv@banvenez.com");
        correo.setRemitente("intranet_bdv@banvenez.com");
        try {
            MonedaDTO moneda =  httpsServicio.servicioBcv();
            resp = daoIntranet.guardarTasaBcv(moneda.getCodigo(), moneda.getCompra());
            correo.setAsunto("crontab bcv exitoso");
            correo.setMensaje("la sincronización de la tasa del bcv ha sido exitosa tasa del dia " + moneda.getCompra());
            if(resp.getEstatus().equalsIgnoreCase(Constantes.fail)){
                log.error("BcvServices:guardarTasaBcv "+ resp.getMensaje());
                correo.setAsunto("crontab bcv fallo");
                correo.setMensaje("proceso de crontab de bcv sincronizacion de la tasa moneda extranjera fallo para mayor informacion consultar el log," +
                        "<br> se anexa error  mensaje del log => "+ resp.getMensaje());
            }

        }catch (Exception e){
            log.error("BcvServices:guardarTasaBcv");
            log.error(e.getMessage());
            correo.setAsunto("crontab bcv fallo");
            correo.setMensaje("proceso de crontab de bcv sincronizacion de la tasa moneda extranjera fallo para mayor informacion consultar el log," +
                    "<br> se anexa error  mensaje del log => "+ e.getMessage());
        }
        this.enviarCorreos(correo);
        return resp;
    }


    public void enviarCorreos(CorreoDto correo){
        try {
            RespuestaDto respCorreo = servicios.servicioIntranet().post()
                    .uri("/envioCorreo")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(Mono.just(correo), CorreoDto.class)
                    .exchange()
                    .block()
                    .bodyToMono(RespuestaDto.class)
                    .doOnError(e -> log.error("Error enviarCorreos => ", e))
                    .block();
            log.info(respCorreo.getStatus());
        }catch (Exception e){
            log.error("SincronizarDataService:enviarCorreos mensaje=> ");
            log.error(e.getMessage());
        }
    }
}
