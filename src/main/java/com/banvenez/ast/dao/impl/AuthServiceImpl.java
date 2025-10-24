package com.banvenez.ast.dao.impl;

import com.banvenez.ast.dao.AuthService;
import com.banvenez.ast.dto.IngresoUserDto;
import com.banvenez.ast.dto.ValidaUser;
import com.banvenez.ast.util.ConnectionUtil;
import com.banvenez.ast.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    ConnectionUtil db;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public ValidaUser validateUser(IngresoUserDto entrada) {
        ValidaUser user = db.valida_user_en_linea(entrada.getUser(),
                entrada.getPassword()).get(0);

        if (user.getCod_respuesta().equals("01")) {
            user.setToken(jwtUtil.generateToken(entrada.getUser(), user));
        } else {
            user.setToken(null);
        }
        return user;
    }
}
