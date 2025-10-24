package com.banvenez.ast.action;



import com.banvenez.ast.dao.AuthService;
import com.banvenez.ast.dto.IngresoUserDto;
import com.banvenez.ast.dto.ValidaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
@RequestMapping("/S1Salud")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping(path = "/valida-user", produces = "application/json")
    public ValidaUser valida_user(@RequestBody IngresoUserDto entrada){
        return authService.validateUser(entrada);
    }

}
