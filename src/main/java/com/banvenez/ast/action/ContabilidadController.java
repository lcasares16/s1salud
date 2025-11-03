package com.banvenez.ast.action;

import com.banvenez.ast.dto.citas.programacionDto;
import com.banvenez.ast.dto.contabilidad.MaestroCtasDto;
import com.banvenez.ast.dto.farmacia.Estados;
import com.banvenez.ast.dto.farmacia.Resultado;
import com.banvenez.ast.util.ConnectionContabilidad;
import com.banvenez.ast.util.ConnectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/S1Salud/contabilidad")
@CrossOrigin
public class ContabilidadController {
    @Autowired
    ConnectionContabilidad db;




    @PostMapping("/consulta-tip-cta")
    public List<Estados> tipocta(){

        List<Estados> solicitudes = new ArrayList<Estados>();
        solicitudes = db.consultaTipocta();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-tip-mov")
    public List<Estados> tipomov(){

        List<Estados> solicitudes = new ArrayList<Estados>();
        solicitudes = db.consultaTipomov();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-compania")
    public List<Estados> compania(){

        List<Estados> solicitudes = new ArrayList<Estados>();
        solicitudes = db.consultaCompania();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-naturaleza")
    public List<Estados> naturalez(){

        List<Estados> solicitudes = new ArrayList<Estados>();
        solicitudes = db.naturaleza();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-maestro-cuentas")
    public List<MaestroCtasDto> maestrocuentas(){

        List<MaestroCtasDto> solicitudes = new ArrayList<MaestroCtasDto>();
        solicitudes = db.maestrocuentas();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/registrar-cuentas")
    public Resultado registrocuentas(@RequestBody MaestroCtasDto registro){

        Resultado solicitudes = new Resultado();
        solicitudes = db.registrarcuentas(registro
        );

        return solicitudes;
    }


}
