package com.banvenez.ast.action;

import com.banvenez.ast.dto.respuestaIntranetDto;

import com.banvenez.ast.util.SincronizadorDataService;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/", headers = "Accept=application/json", method = {RequestMethod.POST})
public class CarnetizacionAction {

    @Autowired
    SincronizadorDataService sincronizadorService;
    @RequestMapping(path = "sincronizarEmpleados", produces = "application/json")
    @Post
    public respuestaIntranetDto sincronizarData(@RequestBody Object jsonEntrada){
        respuestaIntranetDto resp = new respuestaIntranetDto();
        try {
            String fechaInicio = (String)((LinkedHashMap) jsonEntrada).get("fechaInicio") + " 00:00:00";
            String fechaFin = (String)((LinkedHashMap) jsonEntrada).get("fechaFin") + " 23:59:59";
            resp = sincronizadorService.sincronizacionDataMasivoRangoFecha(fechaInicio, fechaFin);
            log.info("Respuesta de sincronizacion " + resp);
        }catch (Exception e){
            log.error("Excepcion en la clase y metodo " + CarnetizacionAction.class.getName() + " sincronizarData");
            log.error("Mensaje " + e.getMessage());
            resp.setEstatus("FAIL");
            resp.setMensaje("Disculpe, tuvimos un incoveniente en procesar la acción");
            resp.setData(null);
        }
        return resp;
    }
}
