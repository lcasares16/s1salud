package com.banvenez.ast.action;

import com.banvenez.ast.dto.citas.programacionDto;
import com.banvenez.ast.dto.contabilidad.*;
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


    @PostMapping("/consulta-oper-contable")
    public List<DetopercontDto> operacioncontable(){

        List<DetopercontDto> solicitudes = new ArrayList<DetopercontDto>();
        solicitudes = db.opercontab();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/registrar-oper-detalle")
    public Resultado operdetalles(@RequestBody DetopercontDto registro){


        Resultado solicitudes = new Resultado();
        solicitudes = db.registraroperconta(registro);

        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-tip-oper")
    public List<Estados> tipooper(){

        List<Estados> solicitudes = new ArrayList<Estados>();
        solicitudes = db.consultaTipooper();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-oper-cont-gen")
    public List<OperacionContableDto> opercontable(){

        List<OperacionContableDto> solicitudes = new ArrayList<OperacionContableDto>();
        solicitudes = db.opercontables();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-maestro-detalle")
    public List<MaestroCtasDto> maestrodetalle(@RequestBody MaestroCtasDto registro){

        List<MaestroCtasDto> solicitudes = new ArrayList<MaestroCtasDto>();
        solicitudes = db.maestrodetalle(registro);
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-concepto-acreencia")
    public List<ConceptoAcreenciaDto> conceptoacre(){

        List<ConceptoAcreenciaDto> solicitudes = new ArrayList<ConceptoAcreenciaDto>();
        solicitudes = db.concetoacreencia();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/consulta-movimientos")
    public List<MovCuentaDto> movimientos(){

        List<MovCuentaDto> solicitudes = new ArrayList<MovCuentaDto>();
        solicitudes = db.consultamovimientos();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-acreencia")
    public List<AcreenciasDto> acreencia(){

        List<AcreenciasDto> solicitudes = new ArrayList<AcreenciasDto>();
        solicitudes = db.consultaacreencia();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-detalle-acreencia")
    public List<DetalleAcreenciaDto> detalleacreencia(@RequestBody DetalleAcreenciaDto registro){

        List<DetalleAcreenciaDto> solicitudes = new ArrayList<DetalleAcreenciaDto>();
        solicitudes = db.detalleacreencia(registro);
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

}
