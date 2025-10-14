package com.banvenez.ast.dto;


import lombok.Data;

@Data
public class ConexionDto {

    //produccion
//    private String url = "jdbc:postgresql://s1salud.net:5432/";
//    private String dbname = "salud";
//    private String user = "salud";
//    private String pass = "j6DQVqLeK8yLW6dLubCL";
//    private String drivers = "org.postgresql.Driver";

//    //Desarrollo  209.38.214.192

    private String url = "jdbc:postgresql://devs1salud.sirumatek.com:5432/";
    private String dbname = "BD_PROD";
    private String user = "s1salud";
    private String pass = "s1salud2024*";
    private String drivers = "org.postgresql.Driver";

//    private String url = "jdbc:postgresql://localhost:5432/";
//    private String dbname = "BD_PROD";
//    private String user = "postgres";
//    private String pass = "postgres";
//    private String drivers = "org.postgresql.Driver";

}
