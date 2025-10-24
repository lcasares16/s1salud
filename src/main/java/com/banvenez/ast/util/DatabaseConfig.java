package com.banvenez.ast.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;

@Configuration

public class DatabaseConfig {
    @Bean
    public JdbcTemplate jdbcTemplate() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Configura los parámetros de la base de datos según tus necesidades
        final String driverClassName = "org.postgresql.Driver";
        final String jdbcUrl = "jdbc:postgresql://localhost:5432/salud";
        final String username = "postgres";
        final String password = "s1salud2024*";

        // Crea un objeto DataSource manualmente
        final Class<?> driverClass = Class.forName(driverClassName);
        final Driver driver = (Driver) driverClass.getDeclaredConstructor().newInstance();
        final SimpleDriverDataSource dataSource = new SimpleDriverDataSource(driver, jdbcUrl, username, password);

        // Devuelve un nuevo JdbcTemplate con el DataSource configurado
        return new JdbcTemplate(dataSource);
    }


}
