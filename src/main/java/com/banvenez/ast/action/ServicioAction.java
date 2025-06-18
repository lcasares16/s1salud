package com.banvenez.ast.action;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping(value = "/S1Salud", headers = "Accept=application/json", method = {RequestMethod.POST, RequestMethod.GET})
public class ServicioAction {

    @GetMapping(value = "/obtenerDsc")
    public String obtenerDsc() {
        return ("Hola estamos ready");
    }




}
