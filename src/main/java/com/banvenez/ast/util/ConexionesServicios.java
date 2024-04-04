package com.banvenez.ast.util;

import com.banvenez.ast.dao.IntranetcorpDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class ConexionesServicios {

    private IntranetcorpDao daoIntranet = new IntranetcorpDao();

    @Bean
    public WebClient servicioLdap(){
        try {
            String endPoint = daoIntranet.obtenerParametros(Constantes.contextoParametro, Constantes.contextoLdapWs, Constantes.parametroLdaWs);
            WebClient clienteLdap = WebClient.builder().baseUrl(endPoint).build();
            return clienteLdap;
        }catch (Exception e){
            log.error("ConexionesServicios:servicioLdap mensaje => ");
            log.error(e.getMessage());
            return  null;
        }
    }


    @Bean
    public WebClient servicioIntranet(){
        try {
            String endPoint = daoIntranet.obtenerParametros(Constantes.contextoParametro, Constantes.contextoPeopleWs, Constantes.parametroIntWs);
            WebClient clienteLdap = WebClient.builder().baseUrl(endPoint).build();
            return clienteLdap;
        }catch (Exception e){
            log.error("ConexionServiciosWeb:servicioIntranet =>");
            log.error(e.getMessage());
            return null;
        }
    }
}
