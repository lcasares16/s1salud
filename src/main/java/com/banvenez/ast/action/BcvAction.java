package com.banvenez.ast.action;

import com.banvenez.ast.dao.IntranetcorpDao;
import com.banvenez.ast.dto.Bcv.MonedaDTO;
import com.banvenez.ast.dto.respuestaIntranetDto;
import com.banvenez.ast.util.Constantes;
import com.banvenez.ast.util.ServicosHttps;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/", headers = "Accept=application/json", method = {RequestMethod.POST})
public class BcvAction {
    @Autowired
    ServicosHttps httpsServicio;

    @Autowired
    IntranetcorpDao daoIntranet;

    @RequestMapping(path = "bcv", produces = "application/json")
    @Post
    public void consultarTasasBcv(){
        try {
            MonedaDTO moneda =  httpsServicio.servicioBcv();
            respuestaIntranetDto resp = daoIntranet.guardarTasaBcv(moneda.getCodigo(), moneda.getCompra());

            if(resp.getEstatus().equalsIgnoreCase(Constantes.fail)){
                log.error("BcvAction:consultarTasasBcv "+ resp.getMensaje());
                // enviar correo a la gerencia de intranet
            }

        }catch (Exception e){
            log.error("BcvAction:consultarTasasBcv");
            log.error(e.getMessage());
        }

    }
}
