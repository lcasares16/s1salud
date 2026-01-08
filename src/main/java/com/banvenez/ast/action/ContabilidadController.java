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

    @PostMapping("/consulta-parametros-concepto")
    public List<ParamCptoCtaDto> parametrosconcepto(){

        List<ParamCptoCtaDto> solicitudes = new ArrayList<ParamCptoCtaDto>();
        solicitudes = db.parametrosconcepto();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-asientos-contable")
    public List<AsientoContableDto> asientos(){

        List<AsientoContableDto> solicitudes = new ArrayList<AsientoContableDto>();
        solicitudes = db.asientoscontable();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-asientos-detalle")
    public List<DetalleAsientoDto> asientosdetalle(@RequestBody DetalleAsientoDto registro){

        List<DetalleAsientoDto> solicitudes = new ArrayList<DetalleAsientoDto>();
        solicitudes = db.asientosdetalle(registro);
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-obligacion")
    public List<ObligacionDto> obligacion(){

        List<ObligacionDto> solicitudes = new ArrayList<ObligacionDto>();
        solicitudes = db.obligacion();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-obligacion-detalle")
    public List<DetalleObligacionDto> obligaciondetalle(@RequestBody DetalleObligacionDto registro){

        List<DetalleObligacionDto> solicitudes = new ArrayList<DetalleObligacionDto>();
        solicitudes = db.obligaciondetalle(registro);
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/registrar-asientos")
    public Resultado registrasientos(@RequestBody AsientoContableDto registro){

        Resultado solicitudes = new Resultado();
        solicitudes = db.registrarasientos(registro
        );

        return solicitudes;
    }


    @PostMapping("/registrar-acreencia")
    public Resultado registraracreencia(@RequestBody AcreenciasDto registro){

        Resultado solicitudes = new Resultado();
        solicitudes = db.registracreencias(registro
        );

        return solicitudes;
    }

    @PostMapping("/registrar-obligacion")
    public Resultado registrarobligacion(@RequestBody ObligacionDto registro){

        Resultado solicitudes = new Resultado();
        solicitudes = db.registrobligacion(registro
        );

        return solicitudes;
    }

    @PostMapping("/registrar-conceptos")
    public Resultado registrarconceptos(@RequestBody ParamCptoCtaDto registro){

        Resultado solicitudes = new Resultado();
        solicitudes = db.registroconcepto(registro
        );

        return solicitudes;
    }


    @PostMapping("/consulta-conceptos-acreencias")
    public List<ConceptoAcreenciaDto> conceptos(){

        List<ConceptoAcreenciaDto> solicitudes = new ArrayList<ConceptoAcreenciaDto>();
        solicitudes = db.conceptosacre();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/balance-comprobacion")
    public  List<RepContable> balancecomprobacion(@RequestBody RepEntrada registro){

        List<RepContable> solicitudes = new ArrayList<RepContable>();
        solicitudes = db.fn_balance_comprobacion(registro
        );

        return solicitudes;
    }


    @PostMapping("/balance-detallado")
    public  List<RepContable> balancedetallado(@RequestBody RepEntrada registro){

        List<RepContable> solicitudes = new ArrayList<RepContable>();
        solicitudes = db.fn_balance_detallado(registro
        );

        return solicitudes;
    }

    @PostMapping("/balance-general")
    public  List<RepContable> balancegeneral(@RequestBody RepEntrada registro){

        List<RepContable> solicitudes = new ArrayList<RepContable>();
        solicitudes = db.fn_balance_general(registro
        );

        return solicitudes;
    }

    @PostMapping("/estado-resultado")
    public  List<RepContable> estadoresultado(@RequestBody RepEntrada registro){

        List<RepContable> solicitudes = new ArrayList<RepContable>();
        solicitudes = db.fn_estado_resultado(registro
        );

        return solicitudes;
    }


    @PostMapping("/estado-resultado-det")
    public  List<RepContable> estadoresultadodetallado(@RequestBody RepEntrada registro){

        List<RepContable> solicitudes = new ArrayList<RepContable>();
        solicitudes = db.fn_estado_resultado_detallado(registro
        );

        return solicitudes;
    }

    @PostMapping("/libro-diario")
    public  List<RepContable> librodiario(@RequestBody RepEntrada registro){

        List<RepContable> solicitudes = new ArrayList<RepContable>();
        solicitudes = db.fn_libro_diario(registro
        );

        return solicitudes;
    }

    @PostMapping("/libro-mayor")
    public  List<RepContable> libromayor(@RequestBody RepEntrada registro){

        List<RepContable> solicitudes = new ArrayList<RepContable>();
        solicitudes = db.fn_libro_mayor(registro
        );

        return solicitudes;
    }


    @PostMapping("/consulta-estatus-conceptos")
    public List<RepEstatusConc> estatusconceptos(){

        List<RepEstatusConc> solicitudes = new ArrayList<RepEstatusConc>();
        solicitudes = db.estatusconceptosacre();
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

}
