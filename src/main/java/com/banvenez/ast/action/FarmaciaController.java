package com.banvenez.ast.action;


import com.banvenez.ast.dto.administracion.ConvierteTasaDto;
import com.banvenez.ast.dto.administracion.ProcesoTasaDto;
import com.banvenez.ast.dto.administracion.SalidaRefeFechaDto;
import com.banvenez.ast.dto.farmacia.*;
import com.banvenez.ast.util.ConnectionUtil;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/S1Salud/farmacia")
public class FarmaciaController {

    @PostMapping("/consultar-inventario")
    public List<RegistrarInventarioDto> inventario(@RequestBody RegistrarInventarioDto registro){

        List<RegistrarInventarioDto> solicitudes = new ArrayList<RegistrarInventarioDto>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.consultar_inventario(
                registro.getCodarticulo(),
                registro.getCodaplicativo()
        );

        return solicitudes;
    }


    @PostMapping("/estatus-inventario")
    public List<Estados> ListaReportes(){


        ConnectionUtil db = new ConnectionUtil();

        List<Estados> solicitudes = new ArrayList<Estados>();
        solicitudes = db.Estatus_inventario();


        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/tipos-inventario")
    public List<Estados> tiposinventario(@RequestBody RegistrarInventarioDto registro){


        ConnectionUtil db = new ConnectionUtil();
        List<Estados> solicitudes = new ArrayList<Estados>();
        solicitudes = db.Tipo_inventario(
                registro.getCodaplicativo()
        );

        return solicitudes;
    }


    @PostMapping("/registrar-inventario")
    public Resultado registrarinventario(@RequestBody RegistrarInventarioDto registro){


        ConnectionUtil db = new ConnectionUtil();
        Resultado solicitudes = new Resultado();
        solicitudes = db.registrar_inventario(
                registro.getDescripcion(),
                registro.getCodtipo(),
                registro.getCantidad(),
                registro.getPreciocosto(),
                registro.getPrecioventa(),
                registro.getDescuento(),
                registro.getVolumen(),
                registro.getStatus(),
                registro.getFechamod(),
                registro.getUsuario(),
                registro.getCodaplicativo(),
                registro.getFecharegistro()

        );

        return solicitudes;
    }


    @PostMapping("/actualizar-inventario")
    public Resultado actualizarinventario(@RequestBody RegistrarInventarioDto registro){


        ConnectionUtil db = new ConnectionUtil();
        Resultado solicitudes = new Resultado();
        solicitudes = db.actualiza_inventario(
                registro.getCodarticulo(),
                registro.getDescripcion(),
                registro.getCodtipo(),
                registro.getCantidad(),
                registro.getPreciocosto(),
                registro.getPrecioventa(),
                registro.getDescuento(),
                registro.getVolumen(),
                registro.getStatus(),
                registro.getFechamod(),
                registro.getUsuario(),
                registro.getCodaplicativo(),
                registro.getFecharegistro()

        );

        return solicitudes;
    }


    @PostMapping("/consulta-volumen")
    public  List<TipoVolumenDto> consulta_volumen(@RequestBody RegistrarInventarioDto registro){


        ConnectionUtil db = new ConnectionUtil();
        List<TipoVolumenDto> solicitudes = new ArrayList<TipoVolumenDto>();
        solicitudes = db.Consulta_volumen(registro
        );

        return solicitudes;
    }


    @PostMapping("/calcula-impuesto")
    public  List<ProcesoImpuestosDto> proceso_impuesto(@RequestBody ProcesoTasaDto registro){


        ConnectionUtil db = new ConnectionUtil();
        List<ProcesoImpuestosDto> solicitudes = new ArrayList<ProcesoImpuestosDto>();
        solicitudes = db.Proceso_impuesto(registro
        );

        return solicitudes;
    }

    @PostMapping("/proceso-tasa")
    public  List<ConvierteTasaDto> proceso_tasa(@RequestBody ProcesoTasaDto registro){


        ConnectionUtil db = new ConnectionUtil();
        List<ConvierteTasaDto> solicitudes = new ArrayList<ConvierteTasaDto>();
        solicitudes = db.Proceso_tasa( registro.getMonto(),
                                      registro.getCodigo(),
                                      registro.getTasa()

        );

        return solicitudes;
    }


    @PostMapping("/registrar-factura")
    public Resultado registrar_factura(@RequestBody RegsitrarFacturaDto registro){


        ConnectionUtil db = new ConnectionUtil();
        Resultado solicitudes = new Resultado();
        solicitudes = db.registrar_factura(

                registro.getFechaemision(),
                registro.getIdcliente(),
                registro.getIdarticulo(),
                registro.getCantidad(),
                registro.getPrecioventa(),
                registro.getDescuento(),
                registro.getDescripcion(),
                registro.getPreciounitario(),
                registro.getPreciounitariodolar(),
                registro.getPreciounitarioeuro(),
                registro.getSubtotal(),
                registro.getSubtotaldolar(),
                registro.getSubtotaleuro(),
                registro.getMontosubtotal(),
                registro.getMontosubtotaldolar(),
                registro.getMontosubtotaleuro(),
                registro.getMontoiva(),
                registro.getMontoivadolar(),
                registro.getMontoivaeuro(),
                registro.getMontoigtf(),
                registro.getMontoigtfdolar(),
                registro.getMontoigtfeuro(),
                registro.getMontototal(),
                registro.getMontototaldolar(),
                registro.getMontototaleuro(),
                registro.getStatus(),
                registro.getFechamod(),
                registro.getUsuario(),
                registro.getCodaplicativo(),
                registro.getDespacho());

        String finalfactura = solicitudes.getRetorno();

        List<DetFacturaDto> detfactura = registro.getDetallefactura();
        for (DetFacturaDto factura : detfactura) {

            System.out.println("Detalle Factura " + solicitudes);
            Resultado detsolicitudes = new Resultado();
            detsolicitudes = db.detalle_factura(


                    finalfactura,
                    factura.getIdcliente(),
                    factura.getIdarticulo(),
                    factura.getDescripcion(),
                    factura.getCantidad(),
                    factura.getPreciounitario(),
                    factura.getPreciounitariodolar(),
                    factura.getPreciounitarioeuro(),
                    factura.getDescuento(),
                    factura.getDescuentodolar(),
                    factura.getDescuentoeuro(),
                    factura.getSubtotal(),
                    factura.getSubtotaldolar(),
                    factura.getSubtotaleuro(),
                    factura.getStatus(),
                    factura.getFechamod(),
                    factura.getUsuario(),
                    factura.getCodaplicativo(),
                    factura.getVolumen());

        }


        List<FormaPagoDto> detpago = registro.getDetallepago();
        for (FormaPagoDto pago : detpago) {

            System.out.println("Detalle Factura " + solicitudes);
            Resultado detpagos = new Resultado();
            detpagos = db.registrar_forma_pago(

                    pago.getFormapago(),
                    pago.getFechapago(),
                    pago.getReferenciapago(),
                    pago.getMonto(),
                    pago.getCodmoneda(),
                    pago.getCorreo(),
                    finalfactura,
                    pago.getIdcliente(),
                    pago.getFechamod(),
                    pago.getUsuariomod(),
                    pago.getIdbanco(),
                    pago.getCodaplicativo(),
                    pago.getIdpuntoventa(),
                    pago.getStatus());

        }

        return solicitudes;
    }

    @PostMapping("/consulta-moneda")
    public List<Estados> consulta_moneda(){
        ConnectionUtil db = new ConnectionUtil();
        List<Estados> solicitudes = new ArrayList<Estados>();
        solicitudes = db.Consulta_Moneda();

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/consulta-referencia-pago")
    public  List<Estados> consulta_referencia_pago(){


        ConnectionUtil db = new ConnectionUtil();
        List<Estados> solicitudes = new ArrayList<Estados>();
        solicitudes = db.Consulta_referencia_pago(
        );

        return solicitudes;
    }

    @PostMapping("/consultar-detalles-factura")
    public  List<DetFacturaDto> consultar_detalles_factura(@RequestBody RegsitrarFacturaDto registro){


        ConnectionUtil db = new ConnectionUtil();
        List<DetFacturaDto> solicitudes = new ArrayList<DetFacturaDto>();
        solicitudes = db.consultar_detalle_factura(registro
        );

        return solicitudes;
    }

    @PostMapping("/consultar-forma-pago")
    public  List<FormaPagoFDto> consultar_pago(@RequestBody FormaPagoFDto registro){


        ConnectionUtil db = new ConnectionUtil();
        List<FormaPagoFDto> solicitudes = new ArrayList<FormaPagoFDto>();
        solicitudes = db.consultar_pago(registro
        );

        return solicitudes;
    }


    @PostMapping("/consultar-factura")
    public  List<RegsitrarFacturaDto> consultar_factura(@RequestBody RegsitrarFacturaDto registro){


        ConnectionUtil db = new ConnectionUtil();
        List<RegsitrarFacturaDto> solicitudes = new ArrayList<RegsitrarFacturaDto>();
        solicitudes = db.consultar_factura(registro
        );

        return solicitudes;
    }

    @PostMapping("/actualiza-despacho")
    public  Resultado actualiza_despacho(@RequestBody RegsitrarFacturaDto registro){


        ConnectionUtil db = new ConnectionUtil();
        Resultado solicitudes = new Resultado();
        solicitudes = db.atualiza_despacho(registro
        );

        return solicitudes;
    }


    @PostMapping("/anula-factura")
    public  Resultado anula_factura(@RequestBody RegsitrarFacturaDto registro){


        ConnectionUtil db = new ConnectionUtil();
        Resultado solicitudes = new Resultado();
        solicitudes = db.anula_factura(registro
        );

        return solicitudes;
    }
    @PostMapping("/consulta-puntos")
    public  List<Estados> consulta_puntos(){


        ConnectionUtil db = new ConnectionUtil();
        List<Estados> solicitudes = new ArrayList<Estados>();
        solicitudes = db.Consulta_puntos(
        );

        return solicitudes;
    }

    @PostMapping("/consulta-banco")
    public  List<Estados> consulta_banco(){


        ConnectionUtil db = new ConnectionUtil();
        List<Estados> solicitudes = new ArrayList<Estados>();
        solicitudes = db.Consulta_banco(
        );

        return solicitudes;
    }

    @PostMapping("/consultar-cliente")
    public  List<RegistrarClientes> consultar_cliente(@RequestBody RegistrarClientes registro){


        ConnectionUtil db = new ConnectionUtil();
        List<RegistrarClientes> solicitudes = new ArrayList<RegistrarClientes>();
        solicitudes = db.consultar_cliente(registro
        );

        return solicitudes;
    }


    @PostMapping("/reporte-inventario")
    public  List<RepInventarioDto> reporteinventario(@RequestBody SalidaRefeFechaDto registro){


        ConnectionUtil db = new ConnectionUtil();
        List<RepInventarioDto> solicitudes = new ArrayList<RepInventarioDto>();
        solicitudes = db.Reporte_inventario(registro);

        return solicitudes;
    }


    @PostMapping("/reporte-ganancia")
    public  List<GanaciasDiariasDto> actualizaganancia(@RequestBody SalidaRefeFechaDto registro){


        ConnectionUtil db = new ConnectionUtil();
        List<GanaciasDiariasDto> solicitudes = new ArrayList<GanaciasDiariasDto>();
        solicitudes = db.Reporte_ganancias(registro);

        return solicitudes;
    }

    @PostMapping("/consultar-reclamo-en-linea")
    public  List<RespuestaReclLinesDto> consultar_reclamo_linea(@RequestBody ConsultaRecLinea registro){


        ConnectionUtil db = new ConnectionUtil();
        List<RespuestaReclLinesDto> solicitudes = new ArrayList<RespuestaReclLinesDto>();
        solicitudes = db.consulta_reclamo_linea(registro
        );

        return solicitudes;
    }


}
