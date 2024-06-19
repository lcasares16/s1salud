package com.banvenez.ast.util;

import com.banvenez.ast.dao.IntranetcorpDao;
import com.banvenez.ast.dao.VacacionesDao;
import com.banvenez.ast.dto.Intranet.CorreoDto;
import com.banvenez.ast.dto.Ldap.EntradaDTO;
import com.banvenez.ast.dto.Ldap.LoginDto;
import com.banvenez.ast.dto.Ldap.RespuestaDto;
import com.banvenez.ast.dto.Ldap.UsuarioDto;
import com.banvenez.ast.dto.Vacaciones.VacacionesDTO;
import com.banvenez.ast.dto.respuestaIntranetDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class VacacionesService {

    @Autowired
    VacacionesDao daoVacaciones = new VacacionesDao();

    @Autowired
    IntranetcorpDao daoIntranet = new IntranetcorpDao();

    @Autowired
    ConexionesServicios servicios;

    public respuestaIntranetDto gestionVacacionesVencidas(){
        respuestaIntranetDto resp = new respuestaIntranetDto();
        try {
            // llamamos a las solicitudes pendientes por vencer
            respuestaIntranetDto solicitudes = daoVacaciones.consultarSolicitudesPorVencer();

            if(solicitudes.getEstatus().equalsIgnoreCase("SUCCESS")){
                // aqui casteamos las solicitudes
                List<VacacionesDTO> solicitudesLts = (List<VacacionesDTO>) solicitudes.getData();

                for (VacacionesDTO  solicitud:  solicitudesLts) {
                    // aqui buscamos la informacion del supervisor para enviar el correo
                    log.info(solicitud.getCodigoUsuarioSupervisor());
                    UsuarioDto supervisor = new UsuarioDto();
                    supervisor =  this.obtenerDatosSupervisor(solicitud.getCodigoUsuarioSupervisor());
                    // aqui enviamos el corrreo
                    this.envioCorreo(supervisor, solicitud);
                    resp.setEstatus("SUCCESS");
                }
            }else{
                resp.setEstatus("WARNING");
                resp.setMensaje("No hay solicitudes pendientes por vencer");
            }
        }catch (Exception e){
            log.error("VacacionesService:gestionVacacionesVencidas mensaje ==>");
            log.error(e.getMessage());
            resp.setEstatus("FAIL");
            resp.setMensaje("Disculpe tuvimos inconveniente en procesar la accion");
        }
        return  resp;
    }

    public UsuarioDto obtenerDatosSupervisor(String codigoUsuario){
        UsuarioDto resp = new UsuarioDto();
        try {

            LoginDto jsonEntrada = new LoginDto();
            jsonEntrada.setCodUsuario(codigoUsuario);
            RespuestaDto respLogin = servicios.servicioLdap().post()
                    .uri("consultarUsuario")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(Mono.just(jsonEntrada), LoginDto.class)
                    .exchange()
                    .block()
                    .bodyToMono(RespuestaDto.class)
                    .doOnError(e -> log.error("Error obtenerDatosSupervisor => ", e))
                    .block();
            log.info(respLogin.toString());
            resp = respLogin.getUsuario();
        }catch (Exception e){
            log.error("VacacionesService:obtenerDatosSupervisor mensaje ==>");
            log.error(e.getMessage());
            resp = null;
        }
        return resp;
    }


    public void envioCorreo(UsuarioDto user, VacacionesDTO  solicitud){
        // Buscar Datos del Correo
        String buzon = daoIntranet.obtenerParametros(Constantes.contextoParametro, Constantes.acronimoAplicacion, Constantes.nombreCorreo);
        String asuntoAprobacionCorreo = daoIntranet.obtenerParametros(Constantes.contextoParametro, Constantes.acronimoAplicacion,Constantes.asuntoAprobacionCorreo);
        String mensajeEntranda        = daoIntranet.obtenerParametros(Constantes.contextoParametro, Constantes.acronimoAplicacion,Constantes.mensajeEntranda      );
        String mensajeSupervisor      = daoIntranet.obtenerParametros(Constantes.contextoParametro, Constantes.acronimoAplicacion,Constantes.mensajeSupervisor    );
        String continuacionMensajeS   = daoIntranet.obtenerParametros(Constantes.contextoParametro, Constantes.acronimoAplicacion,Constantes.continuacionMensajeS );

        String mensaje = mensajeEntranda +  user.getNombres() + " "+ user.getApellidos() + mensajeSupervisor +
                      "<strong>"+ solicitud.getNombres() + "</strong> cédula <strong>"+
                      solicitud.getCedula() +" " +  solicitud.getCodigoUsuario()  +"</strong> " +
                       continuacionMensajeS;

        CorreoDto correo = new CorreoDto();
        correo.setRemitente(buzon);
        correo.setAsunto(asuntoAprobacionCorreo);
        correo.setDestino(user.getCorreo());
        correo.setMensaje(mensaje);

        log.info(correo.toString());

        RespuestaDto resp = servicios.servicioIntranet().post()
                .uri("envioCorreo")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(correo), CorreoDto.class)
                .exchange()
                .block()
                .bodyToMono(RespuestaDto.class)
                .doOnError(e -> log.error("Error pushBdvApp => ", e.getMessage()))
                .block();

        log.info("Respuesta de correo =>"+ resp.toString());
    }

}
