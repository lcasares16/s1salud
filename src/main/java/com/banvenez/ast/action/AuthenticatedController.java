package com.banvenez.ast.action;

import com.banvenez.ast.dto.InsSuscripcionDto;
import com.banvenez.ast.dto.Seguridad.IngresoUserDataDto;
import com.banvenez.ast.dto.Seguridad.ValidaDatosUser;
import com.banvenez.ast.dto.Suscripcion.ConsultaIndvDto;
import com.banvenez.ast.dto.Suscripcion.LineaSuscripcionDto;
import com.banvenez.ast.dto.Suscripcion.reportes.CedulaBeneficiarioDto;
import com.banvenez.ast.dto.Suscripcion.reportes.RetornaBenefiDto;
import com.banvenez.ast.dto.farmacia.ConsultaRecLinea;
import com.banvenez.ast.dto.farmacia.RespuestaReclLinesDto;
import com.banvenez.ast.util.ConnectionUtil;
import com.banvenez.ast.util.EmailSender;
import com.banvenez.ast.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/S1Salud")
public class AuthenticatedController {
    @Autowired
    ConnectionUtil db;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/consulta-beneficiario-linea")
    public ResponseEntity<List<RetornaBenefiDto>> consultabeneficiario(@RequestHeader(value = "Authorization", required = false) String token,
                                                                       @RequestBody CedulaBeneficiarioDto registro) throws Exception {

        try {
            jwtUtil.validateJwtToken(token);
            List<RetornaBenefiDto> solicitudes = new ArrayList<RetornaBenefiDto>();

            solicitudes = db.ConsultaBeneficiarioNew(
                    registro.getCedula()


            );

            return ResponseEntity.ok(solicitudes);
        } catch (JwtException e) { // Captura solo la excepción específica
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Devuelve 500 Internal Server Error
        }
    }


    @PostMapping("/consulta-suscripcion-linea")
    public ResponseEntity<List<LineaSuscripcionDto>> ConsultaSuscripcionIndv(@RequestHeader(value = "Authorization", required = false) String token,
                                                                             @RequestBody ConsultaIndvDto registro) throws Exception {

        try {
            jwtUtil.validateJwtToken(token);
            List<LineaSuscripcionDto> solicitudes = new ArrayList<LineaSuscripcionDto>();

            solicitudes = db.Consulta_Suscripcion_linea(
                    registro.getNumero_contrato(),
                    registro.getCertificado()


            );

            return ResponseEntity.ok(solicitudes);
        } catch (JwtException e) { // Captura solo la excepción específica
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Devuelve 500 Internal Server Error
        }
    }

    @PostMapping("/consulta-datos-userppp")
    public ResponseEntity<List<ValidaDatosUser>> ConsultaDatosUser(@RequestHeader(value = "Authorization", required = false) String token,
                                                                   @RequestBody IngresoUserDataDto registro) throws Exception {

        try {
            jwtUtil.validateJwtToken(token);
            List<ValidaDatosUser> solicitudes = new ArrayList<ValidaDatosUser>();

            solicitudes = db.valida_datos_en_linea(
                    registro.getTipo(),
                    registro.getCedula(),
                    registro.getCorreo()
                    );
            if (solicitudes != null && !solicitudes.isEmpty() && "01".equals(solicitudes.get(0).getCod_respuesta())) {
                // ...

                String destinatario = solicitudes.get(0).getCorreo();
                String asunto = "Recuerde su clave";
                String cuerpo = "<p>Su clave en <strong>S1salud en línea</strong> es la siguiente:</p>" +
                        "<p><strong>" + solicitudes.get(0).getClave() + "</strong></p>";


                EmailSender.enviarCorreo(destinatario, asunto, cuerpo);
            }


            return ResponseEntity.ok(solicitudes);
        } catch (JwtException e) { // Captura solo la excepción específica
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Devuelve 500 Internal Server Error
        }
    }

    @PostMapping("/consultar-reclamo-en-linea")
    public ResponseEntity<List<RespuestaReclLinesDto>> ConsultaReclamoLinea(@RequestHeader(value = "Authorization", required = false) String token,
                                                                             @RequestBody ConsultaRecLinea registro) throws Exception {

        try {
            jwtUtil.validateJwtToken(token);
            List<RespuestaReclLinesDto> solicitudes = new ArrayList<RespuestaReclLinesDto>();

            solicitudes = db.consulta_reclamo_linea(
                    registro


            );

            return ResponseEntity.ok(solicitudes);
        } catch (JwtException e) { // Captura solo la excepción específica
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Devuelve 500 Internal Server Error
        }
    }


}
