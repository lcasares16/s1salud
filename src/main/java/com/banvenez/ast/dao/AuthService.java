package com.banvenez.ast.dao;

import com.banvenez.ast.dto.IngresoUserDto;
import com.banvenez.ast.dto.ValidaUser;

public interface AuthService {
    public ValidaUser validateUser(IngresoUserDto user);
}
