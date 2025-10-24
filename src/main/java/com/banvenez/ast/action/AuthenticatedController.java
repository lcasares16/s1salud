package com.banvenez.ast.action;

import com.banvenez.ast.dto.InsSuscripcionDto;
import com.banvenez.ast.dto.Suscripcion.ConsultaIndvDto;
import com.banvenez.ast.dto.Suscripcion.LineaSuscripcionDto;
import com.banvenez.ast.dto.Suscripcion.reportes.CedulaBeneficiarioDto;
import com.banvenez.ast.dto.Suscripcion.reportes.RetornaBenefiDto;
import com.banvenez.ast.util.ConnectionUtil;
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
                    registro.getNumero_contrato()


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
