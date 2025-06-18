package com.banvenez.ast.action;

import com.banvenez.ast.dto.*;
import com.banvenez.ast.dto.Bcv.RecibeTasaBcvDto;
import com.banvenez.ast.dto.Cobertura.*;
import com.banvenez.ast.dto.Contratos.*;
import com.banvenez.ast.dto.Contratos.reportes.ConsultaRepContratoDto;
import com.banvenez.ast.dto.Contratos.reportes.EntradaRepContratoDto;
import com.banvenez.ast.dto.Sorteo.SalidaJsonDscDto;
import com.banvenez.ast.dto.Sorteo.data;
import com.banvenez.ast.dto.Suscripcion.*;
import com.banvenez.ast.dto.Suscripcion.reportes.CedulaBeneficiarioDto;
import com.banvenez.ast.dto.Suscripcion.reportes.ConsultaRepReciboDto;
import com.banvenez.ast.dto.Suscripcion.reportes.EntradaRepReciboDto;
import com.banvenez.ast.dto.Suscripcion.reportes.RetornaBenefiDto;
import com.banvenez.ast.dto.administracion.*;
import com.banvenez.ast.dto.reclamo.*;
import com.banvenez.ast.dto.reportes.*;
import com.banvenez.ast.util.ConnectionUtil;
//import com.example.springbootwithpostgresqldevelopment.entities.*;
//import com.example.springbootwithpostgresqldevelopment.repo.UserRepo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.netty.handler.codec.http.cors.CorsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

//@CrossOrigin(origins = "https://s1salud.net")
@CrossOrigin
@RestController
@RequestMapping("/S1Salud")
public class UserController {


    public Integer ced;
    public String contrato;
    //private MnuPadreRepo mnuRepo;

   //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/ListadopadreI")
    public List<MsiNewDTO> ListadopadreI(@RequestBody Entrada entrada){

        List<MsiNewDTO> solicitudes = new ArrayList<MsiNewDTO>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.obtenerLista_padre(entrada.getVar());
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }
    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/ListadoAplicaciones")
    public List<AplicDto> Listado_aplicaciones(){

        List<AplicDto> solicitudes = new ArrayList<AplicDto>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.obtenerLista();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    //////Nueva Aplicacion
   // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/NuevaAplicacion")
    public RecibirRespDto NuevaAplicacion(@RequestBody InsertAplicDto entrada){

        //  List<AplicDto> solicitudes = new ArrayList<AplicDto>();
        RecibirRespDto solicitudes = new RecibirRespDto();

        ConnectionUtil db = new ConnectionUtil();
//        aplicacion
//        acronimo
//        codigo_l
//        imagen
//        direccion
//        funcionalidad
//        activo
//        imaarchivo)
        solicitudes = db.insertar_nueva_aplicacion(entrada.getDescripcion_aplicativo(),
                entrada.getAcronimo_aplicativo(),
                entrada.getNombre_imagen_aplicativo(),
                entrada.getDir_url_aplicativo(),
                entrada.getResumen_aplicativo(),
                entrada.getActivo_aplicativo(),
                entrada.getIma_archivo_aplicativo(),
                entrada.getCod_libra());
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }
    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/MenupadreI")
    public List<MsiNewDTO> Menu_padreI(@RequestBody Entrada entrada){

        List<MsiNewDTO> solicitudes = new ArrayList<MsiNewDTO>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Menu_padreI(entrada.getVar());
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }
   // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/NuevoMenu")
    public RecibirRespDto Nuevo_menu_js(@RequestBody CreMnuDto entrada){

        //  List<AplicDto> solicitudes = new ArrayList<AplicDto>();
        RecibirRespDto solicitudes = new RecibirRespDto();

        ConnectionUtil db = new ConnectionUtil();
//        aplicacion
//        acronimo
//        codigo_l
//        imagen
//        direccion
//        funcionalidad
//        activo
//        imaarchivo)
        solicitudes = db.insertar_nuevo_menu(entrada.getDescripcion_menu(),
                entrada.getDireccion_menu(),
                entrada.getPadre_menu(),
                entrada.getCodigo_aplicativo(),
                entrada.getActivo_menu(),
                entrada.getPadremenuii());
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/ListadoMenuRol")
    public List<RolMnuDto> Listado_menu_rol(@RequestBody Entrada entrada){

        List<RolMnuDto> solicitudes = new ArrayList<RolMnuDto>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.roles_menu(entrada.getVar());
        //db.createTable(conn, "recovery");

        return solicitudes;



    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/ConsultarDetallesRol")
    public List<ObtDetRolDto> Consultardetalles_rol(@RequestBody Entrada entrada){

        List<ObtDetRolDto> solicitudes = new ArrayList<ObtDetRolDto>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.obtenerdetalle_rol(entrada.getVar());
        //db.createTable(conn, "recovery");

        return solicitudes;



    }
    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/ModificarRol")
    public RecibirRespDto Modificar_rol(@RequestBody EntModRolDto entrada){

        RecibirRespDto solicitudes = new RecibirRespDto();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.Modificar_rol(entrada.getCustid(),entrada.getDescripcion2());
        //db.createTable(conn, "recovery");

        return solicitudes;



    }
   // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/NuevoRol")
    public RecibirRespDto Nuevo_rol(@RequestBody CreaRolDto entrada){

        RecibirRespDto solicitudes = new RecibirRespDto();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.insertar_nuevo_rol(entrada.getDescripcion_rol(),
                entrada.getCod_aplicativo(),
                entrada.getActivo_rol());
        //db.createTable(conn, "recovery");

        return solicitudes;



    }
   // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/CargarUsuarios")
    public List<UsuListDto> Cargar_usuarios(){

        List<UsuListDto> solicitudes = new ArrayList<UsuListDto>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Cargar_usuarios();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/NuevoUsuario")
    public RecibirRespDto Nuevo_usuario(@RequestBody UsuListDto entrada){

        RecibirRespDto solicitudes = new RecibirRespDto();
        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.nuevo_usuario(entrada.getIde_usuario(),
                entrada.getDoc_usuario(),
                entrada.getNom_usuario(),
                entrada.getApe_usuario(),
                entrada.getCar_usuario(),
                entrada.getEma_usuario(),
                entrada.getLda_usuario(),
                entrada.getEst_usuario(),
                entrada.getObs_usuario(),
                entrada.getPassword(),
                entrada.getCod_aplicacion()
        );


        return solicitudes;



    }

//    @CrossOrigin(origins = "http://localhost:4200", methods = RequestMethod.POST) // Change this to your frontend domain
    //   @PostMapping("/ValidaUser")

    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/ValidaUser", produces = "application/json")

    public List<ValidaUser> valida_user(@RequestBody IngresoUserDto entrada){

        List<ValidaUser> solicitudes = new ArrayList<ValidaUser>();
        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.valida_user(entrada.getUser(),
                entrada.getPassword()

        );


        return solicitudes;



    }
    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/GestionUser")
    public List<ValidaUser> act_ina_user(@RequestBody EntActInaUserDto entrada){

        List<ValidaUser> solicitudes = new ArrayList<ValidaUser>();
        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.act_ina_user(entrada.getUser(),
                                     entrada.getValor()

        );


        return solicitudes;



    }

   // @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/prueba")
    public String miEndpoint() {
        // Lógica de tu endpoint aquí
        return "Respuesta desde Spring Boot";
    }
   // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/ConsultaContratos")
    public List<ContratoAuxDto> Consulta_Contratos(){

        List<ContratoAuxDto> solicitudes = new ArrayList<ContratoAuxDto>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Consulta_Contratos();
        //db.createTable(conn, "recovery");

        Gson gson = new Gson();
       // String json = gson.toJson(solicitudes);
       // Object prueba = json.replace("[","").replace("]","");

      //  String  json = solicitudes.toString();
       // Object prueba = json.replace("[","").replace("]","");
//        String json2 = gson.toJson(prueba);
//        List<ContratosDto> userList = gson.fromJson(json2, new TypeToken<List<ContratosDto>>() {}.getType());




        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }
   // @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/NuevoContrato")
    public RecRespStringDto Nuevo_Contrato(@RequestBody InsContratoDto entrada){

        RecRespStringDto solicitudes = new RecRespStringDto();
        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.NuevoContrato(
               entrada.getNumero_contrato(),
                entrada.getNombre(),
               entrada.getRif(),
               entrada.getFecha_creacion(),
               entrada.getResponsable(),
               entrada.getContacto(),
               entrada.getNit(),
               entrada.getAgente(),
               entrada.getObservacion_agente(),
                entrada.getDireccion(),
                entrada.getObservacion(),
                entrada.getFecha_desde(),
                entrada.getFecha_hasta(),
                entrada.getFecha_desde_exceso(),
                entrada.getFecha_hasta_exceso(),
                entrada.getStatus(),
                entrada.getFecha_anulacion(),
                entrada.getPorcentaje_comision(),
                entrada.getComision_qualitas(),
                entrada.getComision_agente(),
                entrada.getObservaciones_qualitas(),
                entrada.getMonto_restitucion(),
                entrada.getExento_iva(),
                entrada.getCont_especial(),
                entrada.getSucursal(),
                entrada.getForma_pago(),
                entrada.getTipo(),
                entrada.getVigencia_desde(),
                entrada.getVigencia_hasta(),
                entrada.getCodigo_sucursal(),
                entrada.getCod_ramo(),
                entrada.getCod_sub_ramo(),
                entrada.getModificado_por(),
                entrada.getFecha_modificacion(),
                entrada.getFecha_modif(),
                entrada.getMaquina(),
                entrada.getHecho_por(),
                entrada.getOrigen(),
                entrada.getBeneficiario(),
                entrada.getMatrix(),
                entrada.getAuxiliar(),
                entrada.getCod_loc(),
                entrada.getEstado_cuenta(),
                entrada.getAsistencia(),
                entrada.getAmbulancia(),
                entrada.getFecha_asistencia(),
                entrada.getFecha_ambulancia(),
                entrada.getFecha_registro(),
                entrada.getAtencion(),
                entrada.getQualimed(),
                entrada.getFecha_atencion(),
                entrada.getFecha_qualimed(),
                entrada.getTipo_qualimed(),
                entrada.getCantidad_qualimed(),
                entrada.getMasivo(),
                entrada.getDigitalizacion(),
                entrada.getPoliza_fronti(),
                entrada.getNombre_web(),
                entrada.getTipo_producto_web(),
                entrada.getCronico(),
                entrada.getMil_razones(),
                entrada.getComision_qualimed(),
                entrada.getTipo_cobranza(),
                entrada.getCodigo_sucursal_operativa(),
                entrada.getHuma_ticket(),
                entrada.getCod_fact(),
                entrada.getProveedor_amd(),
                entrada.getFacturacion_global(),
                entrada.getOrden_compra(),
                entrada.getFecha_tope_sntro(),
                entrada.getFactura_nombre_de(),
                entrada.getRif_factura(),
                entrada.getDireccion_fiscal_fact(),
                entrada.getTelf_factura(),
                entrada.getActiva_sntros(),
                entrada.getFechahastaloc()
                );




        return solicitudes;

    }

    @PostMapping("/ConsultaContratosIndv")
    public ContratoAuxDto Consulta_Contratos_Indv(@RequestBody ContratosIndvDto entrada){

        ContratoAuxDto solicitudes = new ContratoAuxDto();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Consulta_Contratos_Indv(entrada.getContratoindv());

        return solicitudes;

    }

    //@CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/OrdenaMenu")
    public RespOrdMenDto Ornada_Menu(@RequestBody OrdenaMenuDto entrada){

        RespOrdMenDto solicitudes = new RespOrdMenDto();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Orden_Menu(entrada.getP_codmenu(),
                                    entrada.getP_ordermenu());
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

//    @PostMapping("/NuevoRol")
//    public RespOrdMenDto Nuevo_Rol(@RequestBody NuevoRolDto entrada){
//
//        RespOrdMenDto solicitudes = new RespOrdMenDto();
//
//
//        ConnectionUtil db = new ConnectionUtil();
//
//        solicitudes = db.Neuvo_Rol(entrada.getP_des_rol(),
//                entrada.getP_cod_aplicativo(),
//                entrada.getP_activo());
//        //db.createTable(conn, "recovery");
//
//
//
//        return solicitudes;
//
//        //return new ResponseEntity<>(list, HttpStatus.OK);
//
//    }



//Actualiza Contratos


    @PostMapping("/ActualizoContrato")
    public RecRespStringDto Actualizo_Contrato(@RequestBody InsContratoDto entrada){

        RecRespStringDto solicitudes = new RecRespStringDto();
        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Actualizo_Contrato(
                entrada.getNombre(),
                entrada.getRif(),
                entrada.getFecha_creacion(),
                entrada.getResponsable(),
                entrada.getContacto(),
                entrada.getNit(),
                entrada.getAgente(),
                entrada.getObservacion_agente(),
                entrada.getDireccion(),
                entrada.getObservacion(),
                entrada.getFecha_desde(),
                entrada.getFecha_hasta(),
                entrada.getFecha_desde_exceso(),
                entrada.getFecha_hasta_exceso(),
                entrada.getStatus(),
                entrada.getFecha_anulacion(),
                entrada.getPorcentaje_comision(),
                entrada.getComision_qualitas(),
                entrada.getComision_agente(),
                entrada.getObservaciones_qualitas(),
                entrada.getMonto_restitucion(),
                entrada.getExento_iva(),
                entrada.getCont_especial(),
                entrada.getSucursal(),
                entrada.getForma_pago(),
                entrada.getTipo(),
                entrada.getVigencia_desde(),
                entrada.getVigencia_hasta(),
                entrada.getCodigo_sucursal(),
                entrada.getCod_ramo(),
                entrada.getCod_sub_ramo(),
                entrada.getModificado_por(),
                entrada.getFecha_modificacion(),
                entrada.getFecha_modif(),
                entrada.getMaquina(),
                entrada.getHecho_por(),
                entrada.getOrigen(),
                entrada.getBeneficiario(),
                entrada.getMatrix(),
                entrada.getAuxiliar(),
                entrada.getCod_loc(),
                entrada.getEstado_cuenta(),
                entrada.getAsistencia(),
                entrada.getAmbulancia(),
                entrada.getFecha_asistencia(),
                entrada.getFecha_ambulancia(),
                entrada.getFecha_registro(),
                entrada.getAtencion(),
                entrada.getQualimed(),
                entrada.getFecha_atencion(),
                entrada.getFecha_qualimed(),
                entrada.getTipo_qualimed(),
                entrada.getCantidad_qualimed(),
                entrada.getMasivo(),
                entrada.getDigitalizacion(),
                entrada.getPoliza_fronti(),
                entrada.getNombre_web(),
                entrada.getTipo_producto_web(),
                entrada.getCronico(),
                entrada.getMil_razones(),
                entrada.getComision_qualimed(),
                entrada.getTipo_cobranza(),
                entrada.getCodigo_sucursal_operativa(),
                entrada.getHuma_ticket(),
                entrada.getCod_fact(),
                entrada.getProveedor_amd(),
                entrada.getFacturacion_global(),
                entrada.getOrden_compra(),
                entrada.getFecha_tope_sntro(),
                entrada.getFactura_nombre_de(),
                entrada.getRif_factura(),
                entrada.getDireccion_fiscal_fact(),
                entrada.getTelf_factura(),
                entrada.getActiva_sntros(),
                entrada.getNumero_contrato()
        );


        return solicitudes;

    }

    @PostMapping("/ModificarMenu")
    public RecibirRespDto Modificar_Menu(@RequestBody ModificarMenuDto entrada){

        //  List<AplicDto> solicitudes = new ArrayList<AplicDto>();
        RecibirRespDto solicitudes = new RecibirRespDto();

        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Modificar_Menu(entrada.getP_custid(),
                entrada.getP_descripcion2(),
                entrada.getP_direccion2(),
                entrada.getP_menu_padre(),
                entrada.getP_activo2());




        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }



    @PostMapping("/NuevaSuscripcion")
    public RecRespStringDto Nueva_Suscripcion(@RequestBody InsSuscripcionDto entrada){

        RecRespStringDto solicitudes = new RecRespStringDto();
        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.NuevaSuscripcion(
                entrada.getNumero_contrato(),
                entrada.getCodigo_localidad(),
                entrada.getPlan(),
                entrada.getFecha_inicio(),
                entrada.getCodigo_prima(),
                entrada.getCodigo(),
                entrada.getCertificado(),
                entrada.getNombre(),
                entrada.getFecha_ingreso(),
                entrada.getFecha_nacimiento(),
                entrada.getSexo(),
                entrada.getEstado_civil(),
                entrada.getParentesco(),
                entrada.getStatus(),
                entrada.getFecha_registro(),
                entrada.getPlazos_espera(),
                entrada.getPoliza_exceso(),
                entrada.getCodigo_filial(),
                entrada.getCedula(),
                entrada.getFecha_egreso(),
                entrada.getCodigo_empleado(),
                entrada.getAseguradora_exceso(),
                entrada.getNumero_poliza_exceso(),
                entrada.getTipo_de_empleado(),
                entrada.getHecho_por(),
                entrada.getObservaciones(),
                entrada.getPlan_exceso(),
                entrada.getFecha_inicio_exceso(),
                entrada.getCodigo_prima_exceso(),
                entrada.getSueldo(),
                entrada.getPlazos_de_espera_ezceso(),
                entrada.getObservaciones_exceso(),
                entrada.getRevisar_basico(),
                entrada.getRevisar_exceso(),
                entrada.getFecha_ingreso_exceso(),
                entrada.getFecha_egreso_exceso(),
                entrada.getProrrata(),
                entrada.getCmf(),
                entrada.getRl(),
                entrada.getDireccion(),
                entrada.getMaternidad(),
                entrada.getExclusion_enfermedades(),
                entrada.getSts_procesado(),
                entrada.getCuenta_bancaria(),
                entrada.getCup(),
                entrada.getSts_procesado(),
                entrada.getCentro_costo(),
                entrada.getSts_procesado_egreso(),
                entrada.getSts_procesado_egreso_exc(),
                entrada.getNumero_poliza(),
                entrada.getOdontologico(),
                entrada.getModificado_por(),
                entrada.getFecha_modificacion(),
                entrada.getMaquina(),
                entrada.getFecha_modif(),
                entrada.getFicha(),
                entrada.getCongenita(),
                entrada.getPre_existente(),
                entrada.getCodigo_odontologico(),
                entrada.getAmbulancia(),
                entrada.getCodigo_ambulancia(),
                entrada.getAsistencia(),
                entrada.getCodigo_asistencia(),
                entrada.getBono_plan_incentivo(),
                entrada.getAut_ren(),
                entrada.getDescuento(),
                entrada.getAnexo_vida(),
                entrada.getCodigo_anexo_vida(),
                entrada.getRenovar(),
                entrada.getUsuario_renovacion(),
                entrada.getObservaciones_renovacion(),
                entrada.getEmail(),
                entrada.getId_masivo(),
                entrada.getMat_exceso(),
                entrada.getEnfermedad_cronica(),
                entrada.getModulo_cronico(),
                entrada.getMat_exceso2(),
                entrada.getCodigo_medico_mil_razones(),
                entrada.getSuma_maternidad(),
                entrada.getNacionalidad(),
                entrada.getCod_area(),
                entrada.getCelular(),
                entrada.getF_ing_mat_exc(),
                entrada.getSuma_maternidad_exc(),
                entrada.getCodigo_exclusion(),
                entrada.getFecha_solicitud_ingreso(),
                entrada.getFecha_solicitud_egreso()
        );


        return solicitudes;

    }

    @PostMapping("/ConsultaSuscripcion")
    public List<InsSuscripcionDto> Consulta_Suscripcion(){

        List<InsSuscripcionDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Consulta_Suscripcion();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/ConsultaSuscripcionIndv")
    public List<InsSuscripcionDto> Consulta_Suscripcion_indv(@RequestBody ConsultaIndvDto entrada){

        List<InsSuscripcionDto> solicitudes = new ArrayList<>();



        ConnectionUtil db = new ConnectionUtil();
//
//        if(entrada.getCedula() == 0){
//          Integer ced = 0;
//        }
//        if(entrada.getNumero_contrato() != "0"){
//            String contrato = entrada.getNumero_contrato();
//        }

        solicitudes = db.Consulta_Suscripcion_indv(entrada.getCedula(), entrada.getNumero_contrato());
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

  ///////Actualizo Contrato
  @PostMapping("/ActualizoSuscripcion")
  public RecRespStringDto Actualizo_Suscripcion(@RequestBody InsSuscripcionDto entrada){

      RecRespStringDto solicitudes = new RecRespStringDto();
      ConnectionUtil db = new ConnectionUtil();

      solicitudes = db.ActualizoSuscripcion(
              entrada.getNumero_contrato(),
              entrada.getCodigo_localidad(),
              entrada.getPlan(),
              entrada.getFecha_inicio(),
              entrada.getCodigo_prima(),
              entrada.getCodigo(),
              entrada.getCertificado(),
              entrada.getNombre(),
              entrada.getFecha_ingreso(),
              entrada.getFecha_nacimiento(),
              entrada.getSexo(),
              entrada.getEstado_civil(),
              entrada.getParentesco(),
              entrada.getStatus(),
              entrada.getFecha_registro(),
              entrada.getPlazos_espera(),
              entrada.getPoliza_exceso(),
              entrada.getCodigo_filial(),
              entrada.getCedula(),
              entrada.getFecha_egreso(),
              entrada.getCodigo_empleado(),
              entrada.getAseguradora_exceso(),
              entrada.getNumero_poliza_exceso(),
              entrada.getTipo_de_empleado(),
              entrada.getHecho_por(),
              entrada.getObservaciones(),
              entrada.getPlan_exceso(),
              entrada.getFecha_inicio_exceso(),
              entrada.getCodigo_prima_exceso(),
              entrada.getSueldo(),
              entrada.getPlazos_de_espera_ezceso(),
              entrada.getObservaciones_exceso(),
              entrada.getRevisar_basico(),
              entrada.getRevisar_exceso(),
              entrada.getFecha_ingreso_exceso(),
              entrada.getFecha_egreso_exceso(),
              entrada.getProrrata(),
              entrada.getCmf(),
              entrada.getRl(),
              entrada.getDireccion(),
              entrada.getMaternidad(),
              entrada.getExclusion_enfermedades(),
              entrada.getSts_procesado(),
              entrada.getCuenta_bancaria(),
              entrada.getCup(),
              entrada.getSts_procesado(),
              entrada.getCentro_costo(),
              entrada.getSts_procesado_egreso(),
              entrada.getSts_procesado_egreso_exc(),
              entrada.getNumero_poliza(),
              entrada.getOdontologico(),
              entrada.getModificado_por(),
              entrada.getFecha_modificacion(),
              entrada.getMaquina(),
              entrada.getFecha_modif(),
              entrada.getFicha(),
              entrada.getCongenita(),
              entrada.getPre_existente(),
              entrada.getCodigo_odontologico(),
              entrada.getAmbulancia(),
              entrada.getCodigo_ambulancia(),
              entrada.getAsistencia(),
              entrada.getCodigo_asistencia(),
              entrada.getBono_plan_incentivo(),
              entrada.getAut_ren(),
              entrada.getDescuento(),
              entrada.getAnexo_vida(),
              entrada.getCodigo_anexo_vida(),
              entrada.getRenovar(),
              entrada.getUsuario_renovacion(),
              entrada.getObservaciones_renovacion(),
              entrada.getEmail(),
              entrada.getId_masivo(),
              entrada.getMat_exceso(),
              entrada.getEnfermedad_cronica(),
              entrada.getModulo_cronico(),
              entrada.getMat_exceso2(),
              entrada.getCodigo_medico_mil_razones(),
              entrada.getSuma_maternidad(),
              entrada.getNacionalidad(),
              entrada.getCod_area(),
              entrada.getCelular(),
              entrada.getF_ing_mat_exc(),
              entrada.getSuma_maternidad_exc(),
              entrada.getCodigo_exclusion(),
              entrada.getFecha_solicitud_ingreso(),
              entrada.getFecha_solicitud_egreso()
      );


      return solicitudes;

  }

    @PostMapping("/EstatusSuscripcion")
    public List<EstatusSuscDto> Estatus_Suscripcion(){

        List<EstatusSuscDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Estatus_Suscripcion();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/Parentesco")
    public List<ParentescoDto> Parentesco(){

        List<ParentescoDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Parentesco();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/RamoSubramo")
    public List<RamoSubRamoDto> ramo_subramo(@RequestBody EntradaRamoDTO entrada){

        List<RamoSubRamoDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.RamoSubRamo(entrada.getCod_ramo_pol());
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/Ramo")
    public List<RamoDto> ramo(){

        List<RamoDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Ramo();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/FormaPago")
    public List<FormaPagoDto> forma_pago(){

        List<FormaPagoDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.FormaPago();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/TipoContrato")
    public List<FormaPagoDto> tipo_contrato(){

        List<FormaPagoDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.TipoContrato();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/EstatusContrato")
    public List<FormaPagoDto> estatus_contrato(){

        List<FormaPagoDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.EstatusContrato();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/Sucursal")
    public List<SucursalDto> sucursal(){

        List<SucursalDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Sucursal();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/Localidad")
    public List<LocalidadDto> localidad(){

        List<LocalidadDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Localidad();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/Sexo")
    public List<FormaPagoDto> sexo(){

        List<FormaPagoDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Sexo();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/EstadoCivil")
    public List<FormaPagoDto> Estado_Civil(){

        List<FormaPagoDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.EstadoCivil();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/Producto")
    public List<ProductoDto> producto(@RequestBody EntradaRamoDTO entrada){

        List<ProductoDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Producto(entrada.getCod_ramo_pol());
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }
    @PostMapping("/Planes")
    public List<PlanesSalidaDto> planes(@RequestBody PlanesEntradaDto entrada){

        List<PlanesSalidaDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Planes(entrada.getCod_ramo_pol(),
                                  entrada.getCod_prod());
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

/// cobertura

    @PostMapping("/Cobertura")
    public List<CobertSalidaDto> cobertura(@RequestBody CobertEntradaDto entrada){

        List<CobertSalidaDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Cobertura(entrada.getCod_ramo_pol(),
                                entrada.getCod_prod(),
                                 entrada.getCod_plan());
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/SumaAsegurada")
    public List<SumaAsegSalidaDto> sumaasegurada(@RequestBody SumaAseguradaDto entrada){

        List<SumaAsegSalidaDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.SumaAsegurada(entrada.getCod_ramo_pol(),
                entrada.getCod_prod(),
                entrada.getCod_plan(),
                entrada.getCod_cobertura());
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


 //Baremos
 @PostMapping("/Baremos")
 public List<BaremosSalidaDto> beremos(@RequestBody ConsEntradaGenDto entrada){

     List<BaremosSalidaDto> solicitudes = new ArrayList<>();


     ConnectionUtil db = new ConnectionUtil();

     solicitudes = db.Baremos(entrada.getCod_ramo_pol(),
             entrada.getCod_prod(),
             entrada.getCod_plan(),
             entrada.getCod_cobertura());
     //db.createTable(conn, "recovery");



     return solicitudes;

     //return new ResponseEntity<>(list, HttpStatus.OK);

 }


    //
    @PostMapping("/ConsultaDatosReclamo")
    public List<ConsultaDatosReclamoDto> ConsultaDatosReclamo(@RequestBody EntradaRecDto entrada){

        List<ConsultaDatosReclamoDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ConsultaDataReclamo(entrada.getCertificado());

        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    //Servicios
    @PostMapping("/ConsultaServiciosReclamo")
    public List<ServiciosDto> ConsultaServiciosReclamo(){

        List<ServiciosDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ConsultaServiciosReclamo();

        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    //Clinicas
    @PostMapping("/ConsultaClinicasReclamo")
    public List<ClinicasDto> ConsultaClinicasReclamo(){

        List<ClinicasDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ConsultaClinicasReclamo();

        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }
    //Clinicas
    @PostMapping("/ConsultaMedicosReclamo")
    public List<MedicoDto> ConsultaMedicosReclamo(){

        List<MedicoDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ConsultaMedicosReclamo();

        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    //
    @PostMapping("/ConsultantervReclamo")
    public List<IntervencionDto> ConsultantervReclamo(@RequestBody EntradaIntervDto entrada){

        List<IntervencionDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ConsultaIntervReclamo(entrada.getCod_servicio());

        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/EstatusReclamo")
    public List<EstatusDto> EstatusReclamo(){

        List<EstatusDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ConsultaEstatusReclamo();

        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/NuevoReclamo")
    public RespReclamoDto nuevoreclamo(@RequestBody CreaReclamoDto entrada){

        RespReclamoDto solicitudes = new RespReclamoDto();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.NuevoReclamo(entrada.getP_codigo_suscripcion(),
                entrada.getP_codigo_intervencion(),
                entrada.getP_codigo_tipo_servicio(),
                entrada.getP_codigo_medico(),
                entrada.getP_codigo_clinica(),
                entrada.getP_fecha_ingreso(),
                entrada.getP_numero_servicio(),
                entrada.getP_tipo_poliza(),
                entrada.getP_hecho_por(),
                entrada.getP_aprobado_por(),
                entrada.getP_fecha_recepcion(),
                entrada.getP_fecha_ocurrencia(),
                entrada.getP_status(),
                entrada.getP_departamento(),
                entrada.getP_tipo_cheque(),
                entrada.getP_fecha_egreso(),
                entrada.getP_fecha_pago(),
                entrada.getP_clase_pago(),
                entrada.getP_codigo_medico_opinion(),
                entrada.getP_numero_aval_clave(),
                entrada.getP_deducible(),
                entrada.getP_gastos_clinica(),
                entrada.getP_gastos_no_amparados_neto(),
                entrada.getP_honorarios_medicos(),
                entrada.getP_gastos_ambulatorios(),
                entrada.getP_descuento_clinicas(),
                entrada.getP_descuento_medico(),
                entrada.getP_descuento_neto(),
                entrada.getP_porcentaje_descuento(),
                entrada.getP_porcentaje_reembolso(),
                entrada.getP_monto_facturado(),
                entrada.getP_monto_pagado(),
                entrada.getP_neto_a_pagar(),
                entrada.getP_total_a_pagar(),
                entrada.getP_total_facturado(),
                entrada.getP_total_elegible(),
                entrada.getP_sub_total(),
                entrada.getP_islr_neto(),
                entrada.getP_autorizado(),
                entrada.getP_envio_carta(),
                entrada.getP_fecha_envio_administracion(),
                entrada.getP_fecha_envio_factura(),
                entrada.getP_fecha_recepcion_factura(),
                entrada.getP_descuento_clinica_compromiso(),
                entrada.getP_descuento_medico_compromiso(),
                entrada.getP_numero_factura(),
                entrada.getP_monto_facturado_letra(),
                entrada.getP_monto_pagado_letra(),
                entrada.getP_diagnostico(),
                entrada.getP_comentarios(),
                entrada.getP_observaciones_auditado(),
                entrada.getP_segunda_opinion_medica(),
                entrada.getP_ahorro_opinion(),
                entrada.getP_observaciones_opinion(),
                entrada.getP_fecha_registro(),
                entrada.getP_ajuste_baremo(),
                entrada.getP_medico_auditor(),
                entrada.getP_factura(),
                entrada.getP_numero(),
                entrada.getP_iva(),
                entrada.getP_control(),
                entrada.getP_ca_rel(),
                entrada.getP_tipo_reclamo(),
                entrada.getP_iva_retenido(),
                entrada.getP_porcentaje_retencion(),
               entrada.getP_comprobante(),
                entrada.getP_numero_contrato(),
                entrada.getP_hecho_por_fact(),
                entrada.getP_modificado_por(),
                entrada.getP_fecha_modificacion(),
                entrada.getP_fecha_modif(),
                entrada.getP_maquina(),
                entrada.getP_celular(),
                entrada.getP_cod_area(),
                entrada.getP_fecha_valor(),
                entrada.getP_fecha_recepcion_atc(),
                entrada.getP_referencia_atc(),
                entrada.getP_codigo_filial(),
                entrada.getP_centro_costo(),
                entrada.getP_nu_servicio_unico(),
                entrada.getP_fecha_emision_factura(),
                entrada.getP_remesa(),
                entrada.getP_operador(),
                entrada.getP_fecha_operador(),
                entrada.getP_cod_cobertura(),
                entrada.getP_factura_global(),
                entrada.getP_numero_finiquito()
                );

        List<ListaTipoServicioDto> detreclamos = entrada.getListTserv();


        // List<IntervencionesDto> inter = new ArrayList<>();

        for (ListaTipoServicioDto reclamo : detreclamos) {

            System.out.println("ID Reclamo: " + solicitudes);
            System.out.println("Número de Contrato: " + reclamo.getCod_servicio());
            System.out.println("Fecha de Movimiento: " + reclamo.getDescripcion());
            System.out.println("Tipo Servicio: " + reclamo.getListIntev());


            List<IntervencionesDto> inter = reclamo.getListIntev();
            RespReclamoDto solicitudess = new RespReclamoDto();
            // Integer variable = solicitudes.getRespuestarecl();

            for (IntervencionesDto intervencion : inter) {
                System.out.println("ID Intervención: " + intervencion.getCod_intervencion());
                System.out.println("Descripción Intervención: " + intervencion.getDescripcion());
                System.out.println("Monto Intervención: " + intervencion.getMonto());
                System.out.println("Monto Intervención: " + intervencion.getMonto());
                System.out.println("Tipo Reclamo: " + intervencion.getTipo_reclamo());


                solicitudess = db.DetalleReclamo(solicitudes.getRespuestarecl(),
                        reclamo.getCod_servicio() ,
                        intervencion.getCod_intervencion(),
                        intervencion.getMonto());



            }



        }



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }



    @PostMapping("/ConsultaMontos")
    public List<ConsultaMontosDto> consultamontos(@RequestBody EntradaMontoDto entrada){

         List<ConsultaMontosDto> solicitudes = new ArrayList<ConsultaMontosDto>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ConsultaMontos(entrada.getP_monto_total()
              );




        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/ConsultaFactura")
    public List<ConsultaFacturaDto> consultafacturas(@RequestBody EntradaFactDto entrada){

        List<ConsultaFacturaDto> solicitudes = new ArrayList<ConsultaFacturaDto>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ConsultaFactura(entrada.getP_numero_factura(),
                                         entrada.getContrato(),
                                         entrada.getCedula()
        );




        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/CambioStatusFactura")
    public RetornaDto cambioestatusfacturas(@RequestBody UpdateFactDto entrada){

         RetornaDto solicitudes = new RetornaDto();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.CambiaEstatusFactura(entrada.getP_numero_factura(),
                                         entrada.getP_status()
        );




        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/PlanesSuscripcion")
    public List<PlanesSuscripcionDto> planessuscripcion(){

        List<PlanesSuscripcionDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.PlanesSus();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ConsultaEstatusFact")
    public List<EstatusFacturaDto> consultaestatusfact(){

        List<EstatusFacturaDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.EstatusFactura();
        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ConsultaDatosSuscripcion")
    public List<ConsultaDatosSuscDto> consultadatossuscripcion(@RequestBody EntradaDatosSusDTO entrada){

        List<ConsultaDatosSuscDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaDatosSuscripcion(entrada.getNumerocontrato()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/OtrosDatosSuscripcion")
    public List<OtrosDatosSusDto> otrosdatossuscripcion(@RequestBody EntradaDatosSusDTO entrada){

        List<OtrosDatosSusDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.OtrosDatosSuscripcion(entrada.getNumerocontrato()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ConsultaEdad")
    public ObetnerEdadDto cambioestatusfacturas(@RequestBody EntradaEdadDto entrada){

        ObetnerEdadDto solicitudes = new ObetnerEdadDto();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ObtenerEdad(entrada.getPd_fecha_ini(),
                entrada.getPd_fecha_fin()
        );




        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/ConsultaCronograma")
    public List<ConsultaCronoDto> consultacronograma(@RequestBody EntradaCronoDto entrada){

        List<ConsultaCronoDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaCronograma(entrada.getP_numero_contrato(),
                                                entrada.getP_codigo_suscricion()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }



    @PostMapping("/CobraCuotas")
    public RetornoCobraCuotasDto cobracuotas(@RequestBody EntradaCobroCuotasDto entrada){

        RetornoCobraCuotasDto solicitudes = new RetornoCobraCuotasDto();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.CobraCuota(entrada.getP_numero_contrato(),
                entrada.getP_codigo_suscricion(),
                entrada.getP_cantidad(),
                entrada.getCuota()
        );




        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ConsultaDatosPagos")
    public List<ConsultaDatosPagosDto> consultadatospagos(@RequestBody EntradaDatosPagosDto entrada){

        List<ConsultaDatosPagosDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaDatosPagos(entrada.getCedula()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/ConsultaContratosGen")
    public List<ConsultaDatosPagosDto> consultacontratosgen(@RequestBody EntradaDatosPagosDto entrada){

        List<ConsultaDatosPagosDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaContratosGen(entrada.getCedula()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ReporteContrato")
    public List<ConsultaRepContratoDto> reportecontrato(@RequestBody EntradaRepContratoDto entrada){

        List<ConsultaRepContratoDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ReporteContrato(entrada.getNumerocontrato()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/ReporteRecibo")
    public List<ConsultaRepReciboDto> reporterecibo(@RequestBody EntradaRepReciboDto entrada){

        List<ConsultaRepReciboDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ReporteRecibo(entrada.getCertificado()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }
    @PostMapping("/ProcesoDisminucion")
    public List<SalidaDisminucionDto> procesodisminucion(@RequestBody EntradaDismunucionDto entrada){

        List<SalidaDisminucionDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ProcesoDismiucion(entrada.getCertificado()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/ReferenciaPagos")
    public SalidaReferenciaDto referenciapagos(@RequestBody EntredaReferenciaDto entrada){
        SalidaReferenciaDto solicitudes = new SalidaReferenciaDto();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ReferenciaCuota(entrada.getFechapago(),
                entrada.getFormapago(),
                entrada.getReferenciapago(),
                entrada.getMonto(),
                entrada.getCodigomoneda(),
                entrada.getCorreo(),
                entrada.getContrato(),
                entrada.getCodigosuscripcion(),
                entrada.getBanco()
        );

        return solicitudes;
       //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/GestionCobranza")
    public List<SalidaCobranzaDto> gestioncobranza(){

        List<SalidaCobranzaDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ConsultaGestionCobra();

        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ConsultaReclamo")
    public List<ConsultaReclamoDto> consultareclamo(@RequestBody EntradaConsReclaDTO entrada){

        List<ConsultaReclamoDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaReclamo(entrada.getNumeroreclamo()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }
    @PostMapping("/ConsultaInicialReclamo")
    public List<CertifcadoReclamoDto> consultainicreclamo(@RequestBody EntradaDismunucionDto entrada){

        List<CertifcadoReclamoDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaInicialReclamo(entrada.getCertificado()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ActualizaServicio")
    public RecpuestaSuccesDto actualizaservicio(@RequestBody ActualizaServDto entrada){
        RecpuestaSuccesDto solicitudes = new RecpuestaSuccesDto();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ActualizaServicio(entrada.getIdreclamo(),
                entrada.getTiposervicio(),
                entrada.getTipointervencion(),
                entrada.getMonto()
        );

        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping("/DeleteServicio")
    public RecpuestaSuccesDto deleteservicio(@RequestBody ActualizaServDto entrada){
        RecpuestaSuccesDto solicitudes = new RecpuestaSuccesDto();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.DeleteServicio(entrada.getIdreclamo(),
                entrada.getTiposervicio(),
                entrada.getTipointervencion(),
                entrada.getMonto()
        );

        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }




    @PostMapping("/ActualizoReclamo")
    public RespReclamoDto actualizoreclamo(@RequestBody ActualizoReclDto entrada){

        RespReclamoDto solicitudes = new RespReclamoDto();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ActualizoReclamo(

                entrada.getP_codigo_suscripcion(),
                entrada.getP_codigo_intervencion(),
                entrada.getP_codigo_tipo_servicio(),
                entrada.getP_codigo_medico(),
                entrada.getP_codigo_clinica(),
                entrada.getP_fecha_ingreso(),
                entrada.getP_numero_servicio(),
                entrada.getP_tipo_poliza(),
                entrada.getP_hecho_por(),
                entrada.getP_aprobado_por(),
                entrada.getP_fecha_recepcion(),
                entrada.getP_fecha_ocurrencia(),
                entrada.getP_status(),
                entrada.getP_departamento(),
                entrada.getP_tipo_cheque(),
                entrada.getP_fecha_egreso(),
                entrada.getP_fecha_pago(),
                entrada.getP_clase_pago(),
                entrada.getP_codigo_medico_opinion(),
                entrada.getP_numero_aval_clave(),
                entrada.getP_deducible(),
                entrada.getP_gastos_clinica(),
                entrada.getP_gastos_no_amparados_neto(),
                entrada.getP_honorarios_medicos(),
                entrada.getP_gastos_ambulatorios(),
                entrada.getP_descuento_clinicas(),
                entrada.getP_descuento_medico(),
                entrada.getP_descuento_neto(),
                entrada.getP_porcentaje_descuento(),
                entrada.getP_porcentaje_reembolso(),
                entrada.getP_monto_facturado(),
                entrada.getP_monto_pagado(),
                entrada.getP_neto_a_pagar(),
                entrada.getP_total_a_pagar(),
                entrada.getP_total_facturado(),
                entrada.getP_total_elegible(),
                entrada.getP_sub_total(),
                entrada.getP_islr_neto(),
                entrada.getP_autorizado(),
                entrada.getP_envio_carta(),
                entrada.getP_fecha_envio_administracion(),
                entrada.getP_fecha_envio_factura(),
                entrada.getP_fecha_recepcion_factura(),
                entrada.getP_descuento_clinica_compromiso(),
                entrada.getP_descuento_medico_compromiso(),
                entrada.getP_numero_factura(),
                entrada.getP_monto_facturado_letra(),
                entrada.getP_monto_pagado_letra(),
                entrada.getP_diagnostico(),
                entrada.getP_comentarios(),
                entrada.getP_observaciones_auditado(),
                entrada.getP_segunda_opinion_medica(),
                entrada.getP_ahorro_opinion(),
                entrada.getP_observaciones_opinion(),
                entrada.getP_fecha_registro(),
                entrada.getP_ajuste_baremo(),
                entrada.getP_medico_auditor(),
                entrada.getP_factura(),
                entrada.getP_numero(),
                entrada.getP_iva(),
                entrada.getP_control(),
                entrada.getP_ca_rel(),
                entrada.getP_tipo_reclamo(),
                entrada.getP_iva_retenido(),
                entrada.getP_porcentaje_retencion(),
                entrada.getP_comprobante(),
                entrada.getP_numero_contrato(),
                entrada.getP_hecho_por_fact(),
                entrada.getP_modificado_por(),
                entrada.getP_fecha_modificacion(),
                entrada.getP_fecha_modif(),
                entrada.getP_maquina(),
                entrada.getP_celular(),
                entrada.getP_cod_area(),
                entrada.getP_fecha_valor(),
                entrada.getP_fecha_recepcion_atc(),
                entrada.getP_referencia_atc(),
                entrada.getP_codigo_filial(),
                entrada.getP_centro_costo(),
                entrada.getP_nu_servicio_unico(),
                entrada.getP_fecha_emision_factura(),
                entrada.getP_remesa(),
                entrada.getP_operador(),
                entrada.getP_fecha_operador(),
                entrada.getP_cod_cobertura(),
                entrada.getP_factura_global(),
                entrada.getP_numero_finiquito(),
                entrada.getP_id_reclamo()
        );


        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ConsultaServicioInt")
    public List<ConsultaServicioIntDto> consultaservicoint(@RequestBody EntradaConsReclaDTO entrada){

        List<ConsultaServicioIntDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaServicioInterv(entrada.getNumeroreclamo()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/SecuenciaContrato")
    public NumeroContratoDto secuenciacontrato(){

        NumeroContratoDto solicitudes = new NumeroContratoDto();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.SecuenciaContrato(
        );




        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ConsultaInicContrato")
    public List<SalidDatosContratosDto> consultainiccontrato(@RequestBody EntradaContratoDto entrada){

        List<SalidDatosContratosDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsulatInicContrato(entrada.getCertificado()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ValidaBeneficiario")
    public List<SalidaBeneficiarioDto> validabeneficiario(@RequestBody EntradaCedula entrada){

        List<SalidaBeneficiarioDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ValidaBeneficiario(entrada.getCedula()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/Nacionalidad")
    public List<NacionalidadDto> nacionalidad(){

        List<NacionalidadDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.Nacionalidad(
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ReferenciaFormaPago")
    public List<ReferenciaFormaPDto> ReferenciaFormaPago(){

        List<ReferenciaFormaPDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ReferenciaFormaPago();

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/TipoMoneda")
    public List<TipoMonedaDto> TipoMoneda(){

        List<TipoMonedaDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.TipoMoneda();

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/Banco")
    public List<ReferenciaFormaPDto> Banco(){

        List<ReferenciaFormaPDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.Banco();

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ReporteReferenciaPagos")
    public List<RetornaReferenciaDto> ReporteReferenciaPagos(@RequestBody SalidaRefeFechaDto entrada){

        List<RetornaReferenciaDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ReferenciaPagosCrono(entrada.getPfecha1(),
                                            entrada.getPfecha2()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/GuardarProcesoPago")
    public RetornaGestionDto GuardarProcesoPago(@RequestBody PromesaPagoDto entrada){
        RetornaGestionDto solicitudes = new RetornaGestionDto();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.GestionPromesaPago(entrada.getFechapromesa(),
                entrada.getObservaciones(),
                entrada.getSuscripcion(),
                entrada.getCuota()
        );

        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping("/ConsultaIntermediario")
    public List<ReferenciaFormaPDto> consultaIntermediario(){

        List<ReferenciaFormaPDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaIntemediario();

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ConsultaPlanBeneficiario")
    public List<RetornaDto> ConsultaPlanBeneficiario(@RequestBody EntradaDatosSusDTO entrada){

        List<RetornaDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ConsultaPlanBeneficiario(entrada.getNumerocontrato()
        );




        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ConsultaTasaBcv")
    public List<RecibeTasaBcvDto> Consultatasabcv(){

        List<RecibeTasaBcvDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaTasaBcv();

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ConsultaBancos")
    public List<RecibeNombreBcoDto> ConsultaBancos(){

        List<RecibeNombreBcoDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaBancos();

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }
    @PostMapping("/CronogramaIndividual")
    public RecpuestaSuccesDto cronogramaindividual(@RequestBody ContratosCronogramaDto entrada){
        RecpuestaSuccesDto solicitudes = new RecpuestaSuccesDto();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.CronogramaIndividual(entrada.getNumerocontrato(),
                entrada.getFechapago()
        );

        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/ValidacionCuota")
    public RecpuestaSuccesDto validacuota(@RequestBody ContratosCronogramaDto entrada){
        RecpuestaSuccesDto solicitudes = new RecpuestaSuccesDto();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ValidacionCuota(entrada.getNumerocontrato(),
                entrada.getFechapago()
        );

        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @PostMapping("/ConsultaBeneficiario")
    public List<RetornaBenefiDto> consultabeneficiario(@RequestBody CedulaBeneficiarioDto entrada){

        List<RetornaBenefiDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ConsultaBeneficiario(entrada.getCedula()
        );




        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/ConsultaDetPoliza")
    public List<DisminucionRespDto> consultadetpoliza(@RequestBody CedulaBeneficiarioDto entrada){

        List<DisminucionRespDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaDetallePoliza(entrada.getCedula()
        );
        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/CronogramaIndividualHist")
    public RecpuestaSuccesDto cronogramaindividualhist(@RequestBody CronoHistDto entrada){
        RecpuestaSuccesDto solicitudes = new RecpuestaSuccesDto();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.CronogramaIndividualHist(entrada.getNumerocontrato(),
                                                  entrada.getFechapago(),
                                                  entrada.getMonto(),
                                                  entrada.getMontobs()
        );

        return solicitudes;
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/ConsultaContratosCed")
    public List<SalidDatosContratosDto> ConsultaContratosCed(@RequestBody EntradaContratoDto entrada){

        List<SalidDatosContratosDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsulatContratoCedula(entrada.getCedula()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }
    @PostMapping("/ConsultaTiposPlanes")
    public List<TipoContratoDto> ConsultaTiposPlanes(@RequestBody ConsultaIndvDto entrada){

        List<TipoContratoDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaTipoPlanCon(entrada.getNumero_contrato());

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ActualizaCuotas")
    public RetornoCobraCuotasDto ActiualizaCuotas(@RequestBody EntradaCobroCuotasDto entrada){

        RetornoCobraCuotasDto solicitudes = new RetornoCobraCuotasDto();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ActualizaCuotas(entrada.getP_numero_contrato(),
                entrada.getP_codigo_suscricion(),
                entrada.getP_cantidad(),
                entrada.getCuota(),
                entrada.getMontobs(),
                entrada.getMontodiv(),
                entrada.getFechacobro(),
                entrada.getUsuariomod()
        );




        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }
    @PostMapping("/Estadisticas")
    public List<EstadisticasDto> Estadisticas(){

        List<EstadisticasDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.Estadisticas();

        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/ConsultaRepContatos")
    public List<RepContratoDto> ConsultaRepContatos(@RequestBody RepEntradaDto entrada){

        List<RepContratoDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaRepContratos(   entrada.getTipo(),
                                                 entrada.getFecha_desde(),
                                                 entrada.getFecha_hasta()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }


    @PostMapping("/ConsultaRepSuscripcion")
    public List<RepSuscripcionDto> ConsultaRepSuscripcion(@RequestBody RepEntradaDto entrada){

        List<RepSuscripcionDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaRepSuscripcion(
                entrada.getTipo(),
                entrada.getFecha_desde(),
                entrada.getFecha_hasta()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ConsultaRepReclamo")
    public List<RepReclamoDto> ConsultaRepReclamo(@RequestBody RepEntradaDto entrada){

        List<RepReclamoDto> solicitudes = new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ConsultaRepReclamo(entrada.getFecha_desde(),
                entrada.getFecha_hasta()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }
    @PostMapping("/ListaReportes")
    public List<SalidaRepDto> ListaReportes(){

        List<SalidaRepDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ListaReporte();

        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/CargaMasiva")
    public RetornoCobraCuotasDto cargamasiva(@RequestBody CargaPrincipalDto entrada){

        //RetornoCobraCuotasDto solicitudes = new RetornoCobraCuotasDto();


        ConnectionUtil db = new ConnectionUtil();

        RetornoCobraCuotasDto detsolicitudes = new RetornoCobraCuotasDto();
        List<CargaMasivaDto> detcarga = entrada.getListCarga();
        for (CargaMasivaDto carga : detcarga) {

            System.out.println("Detalle Carga Masiva " + carga);


            detsolicitudes = db.CargaMasiva(
                    carga.getCertificado(),
                    carga.getNombre(),
                    carga.getFecha_ingreso(),
                    carga.getFecha_nacimiento(),
                    carga.getSexo(),
                    carga.getEstado_civil(),
                    carga.getParentesco(),
                    carga.getStatus(),
                    carga.getFecha_registro(),
                    carga.getPlazos_espera(),
                    carga.getPoliza_exceso(),
                    carga.getNacionalidad(),
                    carga.getCedula(),
                    carga.getEmail(),
                    carga.getCod_area(),
                    carga.getCelular(),
                    carga.getObservaciones_renovacion());

            if (!"SUCCESS".equals(detsolicitudes.getResultado())) {
                System.out.println(detsolicitudes);
//               ErrorResponse errorResponse = new ErrorResponse();
//                errorResponse.setTimestamp(java.time.Instant.now().toString());
//                errorResponse.setPath("/S1Salud/CargaMasiva"); // O el path correspondiente
//                errorResponse.setStatus(500); // Código de estado HTT//                errorResponse.setError("Internal Server Error");
//                errorResponse.setMessage("Error en la carga. " + detsolicitudes.getResultado());
//                String repuestaerror = errorResponse.getMessage();

                throw new RuntimeException("Error en la carga."+ detsolicitudes.getResultado());

            }



        }




        return detsolicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ProcesoCargaMasiva")
    public RetornoCobraCuotasDto ProcesoCargaMasiva(@RequestBody EntradaCobroCuotasDto entrada){

        RetornoCobraCuotasDto solicitudes = new RetornoCobraCuotasDto();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.ProcesoCargaMasiva(entrada.getP_numero_contrato()

        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/ConsultaCargaMasiva")
    public List<CargaMasivaDto> ConsultaCargaMasva(){

        List<CargaMasivaDto> solicitudes = new ArrayList<>();


        ConnectionUtil db = new ConnectionUtil();

        solicitudes = db.ConsultaMasiva();

        //db.createTable(conn, "recovery");



        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/proceso-tasa")
    public List<ConvierteTasaDto>  ProcesoTasa(@RequestBody ProcesoTasaDto entrada){

        List<ConvierteTasaDto>  solicitudes =  new ArrayList<>();
        ConnectionUtil db = new ConnectionUtil();
        solicitudes = db.Proceso_tasa(
                entrada.getMonto(),
                entrada.getCodigo(),
                entrada.getTasa()
        );

        return solicitudes;

        //return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @PostMapping("/CobraCuotasPagos")
    public RetornoCobraCuotasDto cobracuotaspagos(@RequestBody FormaPagoCuotasDto entrada){

        RetornoCobraCuotasDto solicitudes = new RetornoCobraCuotasDto();


        ConnectionUtil db = new ConnectionUtil();


        List<EntradaCobroCuotasDto> detcobro = entrada.getDetallecuota();
        for (EntradaCobroCuotasDto pago : detcobro) {


            RetornoCobraCuotasDto detpagos = new RetornoCobraCuotasDto();
            solicitudes = detpagos;
            System.out.println("Detalle cuotas " + detpagos);
            solicitudes = db.CobraCuotaIndv(

                    pago.getP_numero_contrato(),
                    pago.getP_codigo_suscricion(),
                    pago.getP_cantidad(),
                    pago.getCuota());
        }
        String finalfactura = solicitudes.getResultado();

        List<EntredaReferenciaDto> detforma = entrada.getDetallepago();
        for (EntredaReferenciaDto refer : detforma) {


            SalidaReferenciaDto detformacuotas = new SalidaReferenciaDto();
            System.out.println("Detalle forma de pago " + detformacuotas);
            detformacuotas = db.ReferenciaCuota(

                    refer.getFechapago(),
                    refer.getFormapago(),
                    refer.getReferenciapago(),
                    refer.getMonto(),
                    refer.getCodigomoneda(),
                    refer.getCorreo(),
                    refer.getContrato(),
                    refer.getCodigosuscripcion(),
                    refer.getBanco());
        }



        solicitudes = db.Proceso_factura_ind(
                entrada.getP_suscripcion(),
                entrada.getContrato(),
                entrada.getP_monto_bs_total(),
                entrada.getP_monto_usd_total(),
                entrada.getP_monto_eur_total(),
                entrada.getP_cantidad_total()

        );


        return solicitudes;



    }



}
