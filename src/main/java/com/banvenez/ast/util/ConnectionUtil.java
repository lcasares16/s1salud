package com.banvenez.ast.util;

import com.banvenez.ast.dto.*;
//import com.example.springbootwithpostgresqldevelopment.entities.*;
import com.banvenez.ast.dto.Bcv.RecibeTasaBcvDto;
import com.banvenez.ast.dto.Cobertura.BaremosSalidaDto;
import com.banvenez.ast.dto.Cobertura.CobertSalidaDto;
import com.banvenez.ast.dto.Cobertura.PlanesSalidaDto;
import com.banvenez.ast.dto.Cobertura.SumaAsegSalidaDto;
import com.banvenez.ast.dto.Contratos.*;
import com.banvenez.ast.dto.Contratos.FormaPagoDto;
import com.banvenez.ast.dto.Contratos.reportes.ConsultaRepContratoDto;
import com.banvenez.ast.dto.Suscripcion.*;
import com.banvenez.ast.dto.Suscripcion.reportes.ConsultaRepReciboDto;
import com.banvenez.ast.dto.Suscripcion.reportes.RetornaBenefiDto;
import com.banvenez.ast.dto.administracion.*;
import com.banvenez.ast.dto.citas.*;
import com.banvenez.ast.dto.farmacia.*;
import com.banvenez.ast.dto.farmacia.FormaPagoFDto;
import com.banvenez.ast.dto.reclamo.*;
import com.banvenez.ast.dto.reportes.RepContratoDto;
import com.banvenez.ast.dto.reportes.RepReclamoDto;
import com.banvenez.ast.dto.reportes.RepSuscripcionDto;
import com.banvenez.ast.dto.reportes.SalidaRepDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ConnectionUtil {

    public Connection connect_to_db(Integer num) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();


        try {
            Class.forName("org.postgresql.Driver");
           // Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {
                try {
                  //  Integer num = 1;
                    List<MsiDto> list = new ArrayList<MsiDto>();
                    CallableStatement stmt = conn.prepareCall("{? = call msint.obtener_datos("+ num +")}");
                    stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                    //stmt.setLong(1, 0L);
                    stmt.execute();

                    ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet
                    while (rs.next()) {
                        // Procesar los datos del ResultSet
                        System.out.println(rs.getString("cod_menu") + " - " + rs.getString("DES_MENU"));
                    }

                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (Exception ex) {
                    // Manejar excepciones
                    ex.printStackTrace();
                }

                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public void createTable(Connection conn, String recovery) {

        Statement statement;
        try {
            String query = "create table " + recovery + "(id SERIAL PRIMARY KEY NOT NULL, balance numeric(10, 2) default 0, social_security int4 NOT NULL UNIQUE);";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("please enter your social security number");

        } catch (Exception e) {
            System.out.println("entering db information");
            System.out.println("please enter your social security number **1 to exit program**");
        }
    }
    private MsiNewPDTO consultaListado = new MsiNewPDTO();
    public List<MsiNewDTO> obtenerLista_padre(Integer num) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<MsiNewDTO> resp =   new ArrayList<>();
        MsiNewPDTO lisP = new MsiNewPDTO();

        MsiNewDTO sol1 = new MsiNewDTO();
        try {
            Class.forName("org.postgresql.Driver");
            // Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                    CallableStatement stmt = conn.prepareCall("{? = call msint.obtener_datos("+ num +")}");
                    stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                    stmt.execute();

                    ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                    while (rs.next()) {
                        // Procesar los datos del ResultSet

                        System.out.println(rs.getString("cod_menu") + " - " +
                                           rs.getString("DES_MENU") + " - " +
                                           rs.getString("dir_menu") + " - " +
                                           rs.getString("men_padre") + " - " +
                                           rs.getString("COD_APLICATIVO") + " - " +
                                           rs.getString("ORD_MENU") + " - " +
                                           rs.getString("ACTIVO")
                        );

                        MsiNewDTO sol = new MsiNewDTO();

                        sol.setCod_menu(rs.getInt("cod_menu"));
                        sol.setDES_MENU(rs.getString("DES_MENU"));
                        sol.setDIR_MENU(rs.getString("dir_menu"));
                        sol.setMEN_PADRE(rs.getInt("men_padre"));
                        sol.setCOD_APLICATIVO(rs.getInt("COD_APLICATIVO"));
                        sol.setORD_MENU(rs.getInt("ORD_MENU"));
                        sol.setACTIVO(rs.getInt("ACTIVO"));

                        resp.add(sol);





                }
                ObjectMapper mapper = new ObjectMapper();
               // List<MsiNewDTO> listCar = mapper.readValue(json, new TypeReference<List<MsiNewDTO>>(){});
              //  MsiNewDTO obj = mapper.readValue(json, MsiNewDTO.class);



                    rs.close();
                    stmt.close();
                    conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }


       // ArrayList <MsiNewDTO> miArr= new ArrayList <MsiNewDTO> (sol.getCod_menu());
        return resp;
    }


     //obtener lista de aplicaciones


    public List<AplicDto> obtenerLista() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<AplicDto> resp =   new ArrayList<>();



        try {
            Class.forName("org.postgresql.Driver");
            // Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call msint.aplicacion_listado_completo()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    // Procesar los datos del ResultSet

//                    System.out.println(rs.getString("cod_menu") + " - " +
//                            rs.getString("DES_MENU") + " - " +
//                            rs.getString("dir_menu") + " - " +
//                            rs.getString("men_padre") + " - " +
//                            rs.getString("COD_APLICATIVO") + " - " +
//                            rs.getString("ORD_MENU") + " - " +
//                            rs.getString("ACTIVO")
//                    );

                    AplicDto sol = new AplicDto();

                    sol.setCODIGO_APLICATIVO(rs.getInt("CODIGO_APLICATIVO"));
                    sol.setDESCRIPCION_APLICATIVO(rs.getString("DESCRIPCION_APLICATIVO"));
                    sol.setACRONIMO_APLICATIVO(rs.getString("ACRONIMO_APLICATIVO"));
                    sol.setNOMBRE_IMAGEN_APLICATIVO(rs.getString("NOMBRE_IMAGEN_APLICATIVO"));
                    sol.setDIRECCION_URL_APLICATIVO(rs.getString("DIRECCION_URL_APLICATIVO"));
                    sol.setRESUMEN_APLICATIVO(rs.getString("RESUMEN_APLICATIVO"));
                    sol.setCODIGO_LIBRA(rs.getString("CODIGO_LIBRA"));
                    sol.setACTIVO(rs.getString("ACTIVO"));

                    resp.add(sol);





                }
                ObjectMapper mapper = new ObjectMapper();
                // List<MsiNewDTO> listCar = mapper.readValue(json, new TypeReference<List<MsiNewDTO>>(){});
                //  MsiNewDTO obj = mapper.readValue(json, MsiNewDTO.class);



                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }


        // ArrayList <MsiNewDTO> miArr= new ArrayList <MsiNewDTO> (sol.getCod_menu());
        return resp;
    }




    ///////////////nueva apliacacion


    public RecibirRespDto insertar_nueva_aplicacion(String aplicacion, String acronimo,  String imagen,
                                                     String direccion, String funcionalidad, String activo,
                                                     String imaarchivo,Integer codigo_l) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        //List<MsiNewDTO> resp =   new ArrayList<>();
        RecibirRespDto resp = new RecibirRespDto();

        MsiNewDTO sol1 = new MsiNewDTO();
        try {
            Class.forName("org.postgresql.Driver");
            // Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



               // CallableStatement stmt = conn.prepareCall("{?, = call msint.aplicacion_crear1("+ aplicacion + "," + acronimo + ","  + imagen + "," + direccion + "," + funcionalidad + "," + activo + "," + imaarchivo + "," + codigo_l +")}");
                //CallableStatement stmt = conn.prepareCall("{call msint.aplicacion_crear1(:aplicacion,:acronimo,:imagen,:direccion,:funcionalidad,:activo,:imaarchivo,:codigo_l )}");
                CallableStatement stmt = conn.prepareCall("{call msint.aplicacion_crear1(?,?,?,?,?,?,?,? )}");
                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.setString(1, aplicacion);
                stmt.setString(2, acronimo);
                stmt.setString(3, imagen);
                stmt.setString(4, direccion);
                stmt.setString(5, funcionalidad);
                stmt.setString(6, activo);
                stmt.setString(7, imaarchivo);
                stmt.setInt(8, codigo_l);
                // Set the output parameter type

                 stmt.execute();
                int resultado = stmt.getInt(1);

                   // ResultSet rs = stmt.getResultSet(); // Get the result set
                    RecibirRespDto sol = new RecibirRespDto();
                    sol.setRetorno(resultado);
                   // rs.close();     // Process the result set as needed
                     resp.setRetorno((resultado));

                //ResultSet rs = (ResultSet) stmt.getInt(1); // Obtener el resultado como ResultSet
                //ResultSet rs = stmt.getResultSet();

                    // Procesar los datos del ResultSet


                conn.commit();
                stmt.close();
                conn.close();
                System.out.println("Finaliza proceso de Insertar Apliacacion");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }


        // ArrayList <MsiNewDTO> miArr= new ArrayList <MsiNewDTO> (sol.getCod_menu());
        return resp;
    }


    public List<MsiNewDTO> Menu_padreI(Integer num) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<MsiNewDTO> resp =   new ArrayList<>();
        MsiNewPDTO lisP = new MsiNewPDTO();

        MsiNewDTO sol1 = new MsiNewDTO();
        try {
            Class.forName("org.postgresql.Driver");
            // Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call msint.menu_listado_padres("+ num +")}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    // Procesar los datos del ResultSet

                    System.out.println(rs.getString("cod_menu") + " - " +
                            rs.getString("DES_MENU") + " - " +
                            rs.getString("dir_menu") + " - " +
                            rs.getString("men_padre") + " - " +
                            rs.getString("COD_APLICATIVO") + " - " +
                            rs.getString("ORD_MENU") + " - " +
                            rs.getString("ACTIVO")
                    );

                    MsiNewDTO sol = new MsiNewDTO();

                    sol.setCod_menu(rs.getInt("cod_menu"));
                    sol.setDES_MENU(rs.getString("DES_MENU"));
                    sol.setDIR_MENU(rs.getString("dir_menu"));
                    sol.setMEN_PADRE(rs.getInt("men_padre"));
                    sol.setCOD_APLICATIVO(rs.getInt("COD_APLICATIVO"));
                    sol.setORD_MENU(rs.getInt("ORD_MENU"));
                    sol.setACTIVO(rs.getInt("ACTIVO"));

                    resp.add(sol);





                }
                ObjectMapper mapper = new ObjectMapper();
                // List<MsiNewDTO> listCar = mapper.readValue(json, new TypeReference<List<MsiNewDTO>>(){});
                //  MsiNewDTO obj = mapper.readValue(json, MsiNewDTO.class);



                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }


        // ArrayList <MsiNewDTO> miArr= new ArrayList <MsiNewDTO> (sol.getCod_menu());
        return resp;
    }

    /////crear Menu


    public RecibirRespDto insertar_nuevo_menu(String descripcion_menu, String direccion_menu,  Integer padre_menu,
                                              Integer codigo_aplicativo, Integer activo_menu, Integer padre_menu_ii
    ) {



        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        //List<MsiNewDTO> resp =   new ArrayList<>();
        RecibirRespDto resp = new RecibirRespDto();

        MsiNewDTO sol1 = new MsiNewDTO();
        try {
            Class.forName("org.postgresql.Driver");
            // Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{call msint.menu_crear(?,?,?,?,?,? )}");
                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.setString(1, descripcion_menu);
                stmt.setString(2, direccion_menu);
                stmt.setInt(3, padre_menu);
                stmt.setInt(4, codigo_aplicativo);
                stmt.setInt(5, activo_menu);
                stmt.setInt(6, padre_menu_ii);

                // Set the output parameter type

                stmt.execute();
                int resultado = stmt.getInt(1);

                // ResultSet rs = stmt.getResultSet(); // Get the result set
                RecibirRespDto sol = new RecibirRespDto();
                sol.setRetorno(resultado);
                // rs.close();     // Process the result set as needed
                resp.setRetorno((resultado));

                //ResultSet rs = (ResultSet) stmt.getInt(1); // Obtener el resultado como ResultSet
                //ResultSet rs = stmt.getResultSet();

                // Procesar los datos del ResultSet


                conn.commit();
                stmt.close();
                conn.close();
                System.out.println("Finaliza proceso de Insertar Apliacacion");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }


        // ArrayList <MsiNewDTO> miArr= new ArrayList <MsiNewDTO> (sol.getCod_menu());
        return resp;
    }


    ///////Listado_menu_rol

    public List<RolMnuDto> roles_menu(Integer num) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<RolMnuDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call msint.menu_listado_filtrado2("+ num +")}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    // Procesar los datos del ResultSet


                    RolMnuDto sol = new RolMnuDto();

                    sol.setCODIGO_MENU(rs.getInt("CODIGO_MENU"));
                    sol.setDESCRIPCION_MENU(rs.getString("DESCRIPCION_MENU"));
                    sol.setDIRECCION_MENU(rs.getString("DIRECCION_MENU"));
                    sol.setMENU_PADRE(rs.getInt("MENU_PADRE"));
                    sol.setCODIGO_APLICATIVO(rs.getInt("CODIGO_APLICATIVO"));
                    sol.setORDEN_MENU(rs.getInt("ORDEN_MENU"));
                    sol.setACTIVO(rs.getInt("ACTIVO"));
                    sol.setPADRE(rs.getInt("PADRE"));

                    resp.add(sol);





                }

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    ////obetner Rol

    public List<ObtDetRolDto> obtenerdetalle_rol(Integer num) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ObtDetRolDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {
                CallableStatement stmt = conn.prepareCall("{? = call msint.detalle_rol("+ num +")}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.execute();
                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    // Procesar los datos del ResultSet
                    ObtDetRolDto sol = new ObtDetRolDto();
                    sol.setCODIGO_ROL(rs.getInt("CODIGO_ROL"));
                    sol.setDESCRIPCION_ROL(rs.getString("DESCRIPCION_ROL"));
                    resp.add(sol);

                }

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }



    /////////Modifica Rol
    public RecibirRespDto Modificar_rol(Integer num, String Descrip) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RecibirRespDto resp = new RecibirRespDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{call msint.rol_modificar(?,?)}");
                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.setInt(1, num);
                stmt.setString(2, Descrip);

                stmt.execute();
                int resultado = stmt.getInt(1);

                // ResultSet rs = stmt.getResultSet(); // Get the result set
                RecibirRespDto sol = new RecibirRespDto();
                sol.setRetorno(resultado);
                // rs.close();     // Process the result set as needed
                resp.setRetorno((resultado));

                //ResultSet rs = (ResultSet) stmt.getInt(1); // Obtener el resultado como ResultSet
                //ResultSet rs = stmt.getResultSet();

                // Procesar los datos del ResultSet


                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    /////////Inserta Nuevo Rol
    public RecibirRespDto insertar_nuevo_rol(String descripcion_rol, Integer cod_aplicativo, Integer activo_rol) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RecibirRespDto resp = new RecibirRespDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{call msint.rol_crear(?,?,?)}");
                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.setString(1, descripcion_rol);
                stmt.setInt(2, cod_aplicativo);
                stmt.setInt(3, activo_rol);

                stmt.execute();
                int resultado = stmt.getInt(1);

                RecibirRespDto sol = new RecibirRespDto();
                sol.setRetorno(resultado);

                resp.setRetorno((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    ///obetner usuarios

    public List<UsuListDto> Cargar_usuarios() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<UsuListDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call msint.obtener_info_usuario()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    UsuListDto sol = new UsuListDto();

                    sol.setCod_usuario(rs.getInt("cod_usuario"));
                    sol.setIde_usuario(rs.getString("ide_usuario"));
                    sol.setDoc_usuario(rs.getInt("doc_usuario"));
                    sol.setNom_usuario(rs.getString("nom_usuario"));
                    sol.setApe_usuario(rs.getString("ape_usuario"));
                    sol.setCar_usuario(rs.getString("car_usuario"));
                    sol.setEma_usuario(rs.getString("ema_usuario"));
                    sol.setLda_usuario(rs.getString("lda_usuario"));
                    sol.setEst_usuario(rs.getInt("est_usuario"));
                    sol.setObs_usuario(rs.getString("obs_usuario"));

                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    //Insertar Usuario

    public RecibirRespDto nuevo_usuario(String ide_usuario, Integer doc_usuario,
                                        String nom_usuario, String ape_usuario, String car_usuario,
                                        String ema_usuario, String lda_usuario, Integer est_usuario,
                                       String obs_usuario, String password ,Integer aplicacion
    ) {




        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        //List<MsiNewDTO> resp =   new ArrayList<>();
        RecibirRespDto resp = new RecibirRespDto();

        MsiNewDTO sol1 = new MsiNewDTO();
        try {
            Class.forName("org.postgresql.Driver");
            // Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{call msint.nuevo_usuario(?,?,?,?,?,?,?,?,?,?,?)}");
                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.setString(1, ide_usuario);
                stmt.setInt(2, doc_usuario);
                stmt.setString(3, nom_usuario);
                stmt.setString(4, ape_usuario);
                stmt.setString(5, car_usuario);
                stmt.setString(6, ema_usuario);
                stmt.setString(7, lda_usuario);
                stmt.setInt(8, est_usuario);
                stmt.setString(9, obs_usuario);
                stmt.setString(10, password);
                stmt.setInt(11, aplicacion);

                stmt.execute();
                int resultado = stmt.getInt(1);

                RecibirRespDto sol = new RecibirRespDto();
                sol.setRetorno(resultado);
                resp.setRetorno((resultado));

                conn.commit();
                stmt.close();
                conn.close();
                System.out.println("Finaliza proceso de Insertar Usuario");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

////Validar usuario
    public List<ValidaUser> valida_user(String user, String password) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ValidaUser> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call msint.validar_user(?,? )}");
                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setString(1, user);
                stmt.setString(2, password);

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    // Procesar los datos del ResultSet


                    ValidaUser sol = new ValidaUser();

                    sol.setCod_respuesta(rs.getString("cod_respuesta"));
                    sol.setDescripcion(rs.getString("descripcion"));
                    sol.setCod_aplicacion(rs.getInt("aplicativo"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setApellido(rs.getString("apellido"));
                    sol.setPerfil(rs.getString("perfil"));
                    resp.add(sol);

                }

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


///Activa e Inactiva Usuario
//
public List<ValidaUser> act_ina_user(String user, Integer valor) {

    Connection conn = null;
    ConexionDto conexion = new ConexionDto();
    List<ValidaUser> resp =   new ArrayList<>();

    try {
        Class.forName("org.postgresql.Driver");

        conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
        conn.setAutoCommit(false);
        if (conn != null) {

            CallableStatement stmt = conn.prepareCall("{call msint.act_ina_user(?,? )}");
            stmt.registerOutParameter(1, Types.OTHER);
            stmt.setString(1, user);
            stmt.setInt(2, valor);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

            while (rs.next()) {
                // Procesar los datos del ResultSet
                ValidaUser sol = new ValidaUser();
                sol.setCod_respuesta(rs.getString("cod_respuesta"));
                sol.setDescripcion(rs.getString("descripcion"));
                resp.add(sol);
            }
            conn.commit();
            rs.close();
            stmt.close();
            conn.close();


            System.out.println("Connection Exitosa");
        } else {
            System.out.println("Connection Fallida");
        }

    } catch (Exception e) {
        System.out.println(e);
    }

    return resp;
}

////Modulo de contratos  Consulta

public List<ContratoAuxDto> Consulta_Contratos() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ContratoAuxDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_contratos()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ContratoAuxDto sol = new ContratoAuxDto();

                    sol.setNumero_contrato(rs.getString("numero_contrato"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setRif(rs.getString("rif"));
                    sol.setFecha_creacion(rs.getString("fecha_creacion"));
                    sol.setResponsable(rs.getString("responsable"));
                    sol.setContacto(rs.getString("contacto"));
                    sol.setNit(rs.getString("nit"));
                    sol.setAgente(rs.getString("agente"));
                    sol.setObservacion_agente(rs.getString("observacion_agente"));
                    sol.setDireccion(rs.getString("direccion"));
                    sol.setObservacion(rs.getString("observacion"));
                    sol.setFecha_desde(rs.getString("fecha_desde"));
                    sol.setFecha_hasta(rs.getString("fecha_hasta"));
                    sol.setFecha_desde_exceso(rs.getString("fecha_desde_exceso"));
                    sol.setFecha_hasta_exceso(rs.getString("fecha_hasta_exceso"));
                    sol.setStatus(rs.getString("status"));
                    sol.setFecha_anulacion(rs.getString("fecha_anulacion"));
                    sol.setPorcentaje_comision(rs.getDouble("porcentaje_comision"));
                    sol.setComision_qualitas(rs.getDouble("comision_qualitas"));
                    sol.setComision_agente(rs.getDouble("comision_agente"));
                    sol.setObservaciones_qualitas(rs.getString("observaciones_qualitas"));
                    sol.setMonto_restitucion(rs.getDouble("monto_restitucion"));
                    sol.setExento_iva(rs.getString("exento_iva"));
                    sol.setCont_especial(rs.getString("cont_especial"));
                    sol.setSucursal(rs.getString("sucursal"));
                    sol.setForma_pago(rs.getString("forma_pago"));
                    sol.setTipo(rs.getString("tipo"));
                    sol.setVigencia_desde(rs.getString("vigencia_desde"));
                    sol.setVigencia_hasta(rs.getString("vigencia_hasta"));
                    sol.setCodigo_sucursal(rs.getInt("codigo_sucursal"));
                    sol.setCod_ramo(rs.getString("cod_ramo"));
                    sol.setCod_sub_ramo(rs.getString("cod_sub_ramo"));
                    sol.setModificado_por(rs.getString("modificado_por"));
                    sol.setFecha_modificacion(rs.getString("fecha_modificacion"));
                    sol.setFecha_modif(rs.getString("fecha_modif"));
                    sol.setMaquina(rs.getString("maquina"));
                    sol.setHecho_por(rs.getString("hecho_por"));
                    sol.setOrigen(rs.getString("origen"));
                    sol.setBeneficiario(rs.getString("beneficiario"));
                    sol.setMatrix(rs.getString("matrix"));
                    sol.setAuxiliar(rs.getInt("auxiliar"));
                    sol.setCod_loc(rs.getString("cod_loc"));
                    sol.setEstado_cuenta(rs.getString("estado_cuenta"));
                    sol.setAsistencia(rs.getString("asistencia"));
                    sol.setAmbulancia(rs.getString("ambulancia"));
                    sol.setFecha_asistencia(rs.getString("fecha_asistencia"));
                    sol.setFecha_ambulancia(rs.getString("fecha_ambulancia"));
                    sol.setFecha_registro(rs.getString("fecha_registro"));
                    sol.setAtencion(rs.getString("atencion"));
                    sol.setQualimed(rs.getString("qualimed"));
                    sol.setFecha_atencion(rs.getString("fecha_atencion"));
                    sol.setFecha_qualimed(rs.getString("fecha_qualimed"));
                    sol.setTipo_qualimed(rs.getString("tipo_qualimed"));
                    sol.setCantidad_qualimed(rs.getInt("cantidad_qualimed"));
                    sol.setMasivo(rs.getString("masivo"));
                    sol.setDigitalizacion(rs.getString("digitalizacion"));
                    sol.setPoliza_fronti(rs.getString("poliza_fronti"));
                    sol.setNombre_web(rs.getString("nombre_web"));
                    sol.setTipo_producto_web(rs.getString("tipo_producto_web"));
                    sol.setCronico(rs.getString("cronico"));
                    sol.setMil_razones(rs.getString("mil_razones"));
                    sol.setComision_qualimed(rs.getDouble("comision_qualimed"));
                    sol.setTipo_cobranza(rs.getString("tipo_cobranza"));
                    sol.setCodigo_sucursal_operativa(rs.getInt("codigo_sucursal_operativa"));
                    sol.setHuma_ticket(rs.getString("huma_ticket"));
                    sol.setCod_fact(rs.getString("cod_fact"));
                    sol.setProveedor_amd(rs.getString("proveedor_amd"));
                    sol.setFacturacion_global(rs.getString("facturacion_global"));
                    sol.setOrden_compra(rs.getString("orden_compra"));
                    sol.setFecha_tope_sntro(rs.getString("fecha_tope_sntro"));
                    sol.setFactura_nombre_de(rs.getString("factura_nombre_de"));
                    sol.setRif_factura(rs.getString("rif_factura"));
                    sol.setDireccion_fiscal_fact(rs.getString("direccion_fiscal_fact"));
                    sol.setTelf_factura(rs.getString("telf_factura"));
                    sol.setActiva_sntros(rs.getString("activa_sntros"));






                    resp.add(sol);

                }
               // ObjectMapper mapper = new ObjectMapper();

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    /////////Inserta Nuevo Contrato
    public RecRespStringDto NuevoContrato(String contrato,String nombre, String rif , String fecha_creacion,
                                           String responsable, String contacto ,String nit,  String agente,
                                           String observacion_agente, String direccion, String observacion,
                                           String fecha_desde, String fecha_hasta, String fecha_desde_exceso,
                                           String fecha_hasta_exceso, String status,String fecha_anulacion, Double porcentaje_comision,
                                           Double comision_qualitas, Double comision_agente, String observaciones_qualitas, Double monto_restitucion,
                                           String exento_iva, String cont_especial, String sucursal, String forma_pago, String tipo, String vigencia_desde,
                                           String vigencia_hasta, Integer codigo_sucursal,String cod_ramo, String cod_sub_ramo, String modificado_por,
                                           String fecha_modificacion, String fecha_modif, String maquina, String hecho_por, String origen,
                                           String beneficiario, String matrix, Integer auxiliar , String cod_loc, String estado_cuenta, String asistencia ,
                                           String ambulancia, String fecha_asistencia, String fecha_ambulancia, String fecha_registro,
                                           String atencion, String qualimed, String fecha_atencion, String fecha_qualimed,
                                           String tipo_qualimed, Integer cantidad_qualimed, String masivo, String digitalizacion,
                                           String poliza_fronti, String nombre_web, String tipo_producto_web, String cronico,
                                           String mil_razones, Double comision_qualimed, String tipo_cobranza, Integer codigo_sucursal_operativa,
                                           String huma_ticket, String cod_fact, String proveedor_amd, String facturacion_global, String orden_compra,
                                           String fecha_tope_sntro,String factura_nombre_de, String rif_factura, String direccion_fiscal_fact,
                                           String telf_factura,String activa_sntros, String fechahastaloc
    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RecRespStringDto resp = new RecRespStringDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


           CallableStatement stmt = conn.prepareCall("{call qualitas.nuevo_contrato(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
           //     CallableStatement stmt = conn.prepareCall("{call qualitas.nuevo_contrato(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                stmt.registerOutParameter(1, Types.CHAR);
                stmt.setString(1, nombre);
                stmt.setString(2, rif);
                stmt.setString(3, fecha_creacion);
                stmt.setString(4, responsable);
                stmt.setString(5, contacto);
                stmt.setString(6, nit);
                stmt.setString(7, agente);
                stmt.setString(8, observacion_agente);
                stmt.setString(9, direccion);
                stmt.setString(10, observacion);
                stmt.setString(11, fecha_desde);

                stmt.setString(12, fecha_hasta);
                stmt.setString(13, fecha_desde_exceso);
                stmt.setString(14, fecha_hasta_exceso);
                stmt.setString(15, status);
                stmt.setString(16, fecha_anulacion);
                stmt.setDouble(17, porcentaje_comision);
                stmt.setDouble(18, comision_qualitas);
                stmt.setDouble(19, comision_agente);
                stmt.setString(20, observaciones_qualitas);
                stmt.setDouble(21, monto_restitucion);
                stmt.setString(22, exento_iva);

                stmt.setString(23, cont_especial);
                stmt.setString(24, sucursal);
                stmt.setString(25, forma_pago);
                stmt.setString(26, tipo);
                stmt.setString(27, vigencia_desde);
                stmt.setString(28, vigencia_hasta);
                stmt.setInt(29, codigo_sucursal);
                stmt.setString(30, cod_ramo);
                stmt.setString(31, cod_sub_ramo);
                stmt.setString(32, modificado_por);
                stmt.setString(33, fecha_modificacion);
                stmt.setString(34, fecha_modif);

                stmt.setString(35, maquina);
                stmt.setString(36, hecho_por);
                stmt.setString(37, origen);
                stmt.setString(38, beneficiario);
                stmt.setString(39, matrix);
                stmt.setInt(40, auxiliar);
                stmt.setString(41, cod_loc);
                stmt.setString(42, estado_cuenta);
                stmt.setString(43, asistencia);
                stmt.setString(44, ambulancia);
                stmt.setString(45, fecha_asistencia);
                stmt.setString(46, fecha_ambulancia);
                stmt.setString(47, fecha_registro);
                stmt.setString(48, atencion);
                stmt.setString(49, qualimed);
                stmt.setString(50, fecha_atencion);

                stmt.setString(51, fecha_qualimed);
                stmt.setString(52, tipo_qualimed);
                stmt.setInt(53, cantidad_qualimed);
                stmt.setString(54, masivo);
                stmt.setString(55, digitalizacion);
                stmt.setString(56, poliza_fronti);
                stmt.setString(57, nombre_web);
                stmt.setString(58, tipo_producto_web);
                stmt.setString(59, cronico);
                stmt.setString(60, mil_razones);
                stmt.setDouble(61, comision_qualimed);
                stmt.setString(62, tipo_cobranza);
                stmt.setInt(63, codigo_sucursal_operativa);
                stmt.setString(64, huma_ticket);
                stmt.setString(65, cod_fact);
                stmt.setString(66, proveedor_amd);
                stmt.setString(67, facturacion_global);
                stmt.setString(68, orden_compra);
                stmt.setString(69, fecha_tope_sntro);
                stmt.setString(70, factura_nombre_de);
                stmt.setString(71, rif_factura);
                stmt.setString(72, direccion_fiscal_fact);
                stmt.setString(73, telf_factura);
                stmt.setString(74, activa_sntros);
                stmt.setString(75, contrato);
                stmt.setString(76, fechahastaloc);


            stmt.execute();
            String resultado = stmt.getString(1);

            RecRespStringDto sol = new RecRespStringDto();
            sol.setRetorno(resultado);

            resp.setRetorno((resultado));

            conn.commit();

            stmt.close();
            conn.close();


            System.out.println("Connection Exitosa");
        } else {
            System.out.println("Connection Fallida");
        }

    } catch (Exception e) {
        System.out.println(e);
    }

        return resp;
}



    public ContratoAuxDto Consulta_Contratos_Indv(String contratoIndv) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        ContratoAuxDto resp =   new ContratoAuxDto();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_contratos_indv( )}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setString(1, contratoIndv);
                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ContratoAuxDto sol = new ContratoAuxDto();

                    sol.setNumero_contrato(rs.getString("numero_contrato"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setRif(rs.getString("rif"));
                    sol.setFecha_creacion(rs.getString("fecha_creacion"));
                    sol.setResponsable(rs.getString("responsable"));
                    sol.setContacto(rs.getString("contacto"));
                    sol.setNit(rs.getString("nit"));
                    sol.setAgente(rs.getString("agente"));
                    sol.setObservacion_agente(rs.getString("observacion_agente"));
                    sol.setDireccion(rs.getString("direccion"));
                    sol.setObservacion(rs.getString("observacion"));
                    sol.setFecha_desde(rs.getString("fecha_desde"));
                    sol.setFecha_hasta(rs.getString("fecha_hasta"));
                    sol.setFecha_desde_exceso(rs.getString("fecha_desde_exceso"));
                    sol.setFecha_hasta_exceso(rs.getString("fecha_hasta_exceso"));
                    sol.setStatus(rs.getString("status"));
                    sol.setFecha_anulacion(rs.getString("fecha_anulacion"));
                    sol.setPorcentaje_comision(rs.getDouble("porcentaje_comision"));
                    sol.setComision_qualitas(rs.getDouble("comision_qualitas"));
                    sol.setComision_agente(rs.getDouble("comision_agente"));
                    sol.setObservaciones_qualitas(rs.getString("observaciones_qualitas"));
                    sol.setMonto_restitucion(rs.getDouble("monto_restitucion"));
                    sol.setExento_iva(rs.getString("exento_iva"));
                    sol.setCont_especial(rs.getString("cont_especial"));
                    sol.setSucursal(rs.getString("sucursal"));
                    sol.setForma_pago(rs.getString("forma_pago"));
                    sol.setTipo(rs.getString("tipo"));
                    sol.setVigencia_desde(rs.getString("vigencia_desde"));
                    sol.setVigencia_hasta(rs.getString("vigencia_hasta"));
                    sol.setCodigo_sucursal(rs.getInt("codigo_sucursal"));
                    sol.setCod_ramo(rs.getString("cod_ramo"));
                    sol.setCod_sub_ramo(rs.getString("cod_sub_ramo"));
                    sol.setModificado_por(rs.getString("modificado_por"));
                    sol.setFecha_modificacion(rs.getString("fecha_modificacion"));
                    sol.setFecha_modif(rs.getString("fecha_modif"));
                    sol.setMaquina(rs.getString("maquina"));
                    sol.setHecho_por(rs.getString("hecho_por"));
                    sol.setOrigen(rs.getString("origen"));
                    sol.setBeneficiario(rs.getString("beneficiario"));
                    sol.setMatrix(rs.getString("matrix"));
                    sol.setAuxiliar(rs.getInt("auxiliar"));
                    sol.setCod_loc(rs.getString("cod_loc"));
                    sol.setEstado_cuenta(rs.getString("estado_cuenta"));
                    sol.setAsistencia(rs.getString("asistencia"));
                    sol.setAmbulancia(rs.getString("ambulancia"));
                    sol.setFecha_asistencia(rs.getString("fecha_asistencia"));
                    sol.setFecha_ambulancia(rs.getString("fecha_ambulancia"));
                    sol.setFecha_registro(rs.getString("fecha_registro"));
                    sol.setAtencion(rs.getString("atencion"));
                    sol.setQualimed(rs.getString("qualimed"));
                    sol.setFecha_atencion(rs.getString("fecha_atencion"));
                    sol.setFecha_qualimed(rs.getString("fecha_qualimed"));
                    sol.setTipo_qualimed(rs.getString("tipo_qualimed"));
                    sol.setCantidad_qualimed(rs.getInt("cantidad_qualimed"));
                    sol.setMasivo(rs.getString("masivo"));
                    sol.setDigitalizacion(rs.getString("digitalizacion"));
                    sol.setPoliza_fronti(rs.getString("poliza_fronti"));
                    sol.setNombre_web(rs.getString("nombre_web"));
                    sol.setTipo_producto_web(rs.getString("tipo_producto_web"));
                    sol.setCronico(rs.getString("cronico"));
                    sol.setMil_razones(rs.getString("mil_razones"));
                    sol.setComision_qualimed(rs.getDouble("comision_qualimed"));
                    sol.setTipo_cobranza(rs.getString("tipo_cobranza"));
                    sol.setCodigo_sucursal_operativa(rs.getInt("codigo_sucursal_operativa"));
                    sol.setHuma_ticket(rs.getString("huma_ticket"));
                    sol.setCod_fact(rs.getString("cod_fact"));
                    sol.setProveedor_amd(rs.getString("proveedor_amd"));
                    sol.setFacturacion_global(rs.getString("facturacion_global"));
                    sol.setOrden_compra(rs.getString("orden_compra"));
                    sol.setFecha_tope_sntro(rs.getString("fecha_tope_sntro"));
                    sol.setFactura_nombre_de(rs.getString("factura_nombre_de"));
                    sol.setRif_factura(rs.getString("rif_factura"));
                    sol.setDireccion_fiscal_fact(rs.getString("direccion_fiscal_fact"));
                    sol.setTelf_factura(rs.getString("telf_factura"));
                    sol.setActiva_sntros(rs.getString("activa_sntros"));
                    resp = sol;
                }
                ObjectMapper mapper = new ObjectMapper();

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    /////////Nuevo Roll
    public RespOrdMenDto Neuvo_Rol(String p_des_rol, Integer p_cod_aplicativo, Integer p_activo
    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RespOrdMenDto resp = new RespOrdMenDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call msint.prc_modificar_order_menu_f(?,?)}");


                stmt.registerOutParameter(1, Types.CHAR);
                stmt.setString(1, p_des_rol);
                stmt.setInt(2, p_cod_aplicativo);
                stmt.setInt(3, p_activo);


                stmt.execute();
                String resultado = stmt.getString(1);

                RespOrdMenDto sol = new RespOrdMenDto();
                sol.setP_respuesta(resultado);

                resp.setP_respuesta((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    ///////orden Manu


    public RespOrdMenDto Orden_Menu(Integer p_codmenu, Integer p_ordermenu
    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RespOrdMenDto resp = new RespOrdMenDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call msint.prc_modificar_order_menu_f(?,?)}");


                stmt.registerOutParameter(1, Types.CHAR);
                stmt.setInt(1, p_codmenu);
                stmt.setInt(2, p_ordermenu);


                stmt.execute();
                String resultado = stmt.getString(1);

                RespOrdMenDto sol = new RespOrdMenDto();
                sol.setP_respuesta(resultado);

                resp.setP_respuesta((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    /////////Actualizo  Contrato
    public RecRespStringDto Actualizo_Contrato(String nombre, String rif , String fecha_creacion,
                                          String responsable, String contacto ,String nit,  String agente,
                                          String observacion_agente, String direccion, String observacion,
                                          String fecha_desde, String fecha_hasta, String fecha_desde_exceso,
                                          String fecha_hasta_exceso, String status,String fecha_anulacion, Double porcentaje_comision,
                                          Double comision_qualitas, Double comision_agente, String observaciones_qualitas, Double monto_restitucion,
                                          String exento_iva, String cont_especial, String sucursal, String forma_pago, String tipo, String vigencia_desde,
                                          String vigencia_hasta, Integer codigo_sucursal,String cod_ramo, String cod_sub_ramo, String modificado_por,
                                          String fecha_modificacion, String fecha_modif, String maquina, String hecho_por, String origen,
                                          String beneficiario, String matrix, Integer auxiliar , String cod_loc, String estado_cuenta, String asistencia ,
                                          String ambulancia, String fecha_asistencia, String fecha_ambulancia, String fecha_registro,
                                          String atencion, String qualimed, String fecha_atencion, String fecha_qualimed,
                                          String tipo_qualimed, Integer cantidad_qualimed, String masivo, String digitalizacion,
                                          String poliza_fronti, String nombre_web, String tipo_producto_web, String cronico,
                                          String mil_razones, Double comision_qualimed, String tipo_cobranza, Integer codigo_sucursal_operativa,
                                          String huma_ticket, String cod_fact, String proveedor_amd, String facturacion_global, String orden_compra,
                                          String fecha_tope_sntro,String factura_nombre_de, String rif_factura, String direccion_fiscal_fact,
                                          String telf_factura,String activa_sntros, String numero_contrato
    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RecRespStringDto resp = new RecRespStringDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.actualiza_contrato(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                //     CallableStatement stmt = conn.prepareCall("{call qualitas.nuevo_contrato(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                stmt.registerOutParameter(1, Types.CHAR);
                stmt.setString(1, nombre);
                stmt.setString(2, rif);
                stmt.setString(3, fecha_creacion);
                stmt.setString(4, responsable);
                stmt.setString(5, contacto);
                stmt.setString(6, nit);
                stmt.setString(7, agente);
                stmt.setString(8, observacion_agente);
                stmt.setString(9, direccion);
                stmt.setString(10, observacion);
                stmt.setString(11, fecha_desde);

                stmt.setString(12, fecha_hasta);
                stmt.setString(13, fecha_desde_exceso);
                stmt.setString(14, fecha_hasta_exceso);
                stmt.setString(15, status);
                stmt.setString(16, fecha_anulacion);
                stmt.setDouble(17, porcentaje_comision);
                stmt.setDouble(18, comision_qualitas);
                stmt.setDouble(19, comision_agente);
                stmt.setString(20, observaciones_qualitas);
                stmt.setDouble(21, monto_restitucion);
                stmt.setString(22, exento_iva);

                stmt.setString(23, cont_especial);
                stmt.setString(24, sucursal);
                stmt.setString(25, forma_pago);
                stmt.setString(26, tipo);
                stmt.setString(27, vigencia_desde);
                stmt.setString(28, vigencia_hasta);
                stmt.setInt(29, codigo_sucursal);
                stmt.setString(30, cod_ramo);
                stmt.setString(31, cod_sub_ramo);
                stmt.setString(32, modificado_por);
                stmt.setString(33, fecha_modificacion);
                stmt.setString(34, fecha_modif);

                stmt.setString(35, maquina);
                stmt.setString(36, hecho_por);
                stmt.setString(37, origen);
                stmt.setString(38, beneficiario);
                stmt.setString(39, matrix);
                stmt.setInt(40, auxiliar);
                stmt.setString(41, cod_loc);
                stmt.setString(42, estado_cuenta);
                stmt.setString(43, asistencia);
                stmt.setString(44, ambulancia);
                stmt.setString(45, fecha_asistencia);
                stmt.setString(46, fecha_ambulancia);
                stmt.setString(47, fecha_registro);
                stmt.setString(48, atencion);
                stmt.setString(49, qualimed);
                stmt.setString(50, fecha_atencion);

                stmt.setString(51, fecha_qualimed);
                stmt.setString(52, tipo_qualimed);
                stmt.setInt(53, cantidad_qualimed);
                stmt.setString(54, masivo);
                stmt.setString(55, digitalizacion);
                stmt.setString(56, poliza_fronti);
                stmt.setString(57, nombre_web);
                stmt.setString(58, tipo_producto_web);
                stmt.setString(59, cronico);
                stmt.setString(60, mil_razones);
                stmt.setDouble(61, comision_qualimed);
                stmt.setString(62, tipo_cobranza);
                stmt.setInt(63, codigo_sucursal_operativa);
                stmt.setString(64, huma_ticket);
                stmt.setString(65, cod_fact);
                stmt.setString(66, proveedor_amd);
                stmt.setString(67, facturacion_global);
                stmt.setString(68, orden_compra);
                stmt.setString(69, fecha_tope_sntro);
                stmt.setString(70, factura_nombre_de);
                stmt.setString(71, rif_factura);
                stmt.setString(72, direccion_fiscal_fact);
                stmt.setString(73, telf_factura);
                stmt.setString(74, activa_sntros);
                stmt.setString(75, numero_contrato);

                stmt.execute();
                String resultado = stmt.getString(1);

                RecRespStringDto sol = new RecRespStringDto();
                sol.setRetorno(resultado);

                resp.setRetorno((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

///Modificar Menu

public RecibirRespDto Modificar_Menu( Integer p_custid, String p_descripcion2, String p_direccion2,
                                      Integer p_menu_padre, Integer p_activo2
    ) {



        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        //List<MsiNewDTO> resp =   new ArrayList<>();
        RecibirRespDto resp = new RecibirRespDto();

        MsiNewDTO sol1 = new MsiNewDTO();
        try {
            Class.forName("org.postgresql.Driver");
            // Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{call msint.menu_modificar(?,?,?,?,? )}");
                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.setInt(1, p_custid);
                stmt.setString(2, p_descripcion2);
                stmt.setString(3, p_direccion2);
                stmt.setInt(4, p_menu_padre);
                stmt.setInt(5, p_activo2);


                // Set the output parameter type

                stmt.execute();
                int resultado = stmt.getInt(1);

                // ResultSet rs = stmt.getResultSet(); // Get the result set
                RecibirRespDto sol = new RecibirRespDto();
                sol.setRetorno(resultado);
                // rs.close();     // Process the result set as needed
                resp.setRetorno((resultado));

                //ResultSet rs = (ResultSet) stmt.getInt(1); // Obtener el resultado como ResultSet
                //ResultSet rs = stmt.getResultSet();

                // Procesar los datos del ResultSet


                conn.commit();
                stmt.close();
                conn.close();
                System.out.println("Finaliza proceso de Insertar Apliacacion");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }


        // ArrayList <MsiNewDTO> miArr= new ArrayList <MsiNewDTO> (sol.getCod_menu());
        return resp;
    }

////nueva suscripcion

    public RecRespStringDto NuevaSuscripcion(  String numero_contrato,
                                               String  codigo_localidad,
                                               String  plan,
                                               String  fecha_inicio,
                                               Integer  codigo_prima,
                                               Integer  codigo,
                                               Integer   certificado,
                                               String  nombre,
                                               String  fecha_ingreso,
                                               String fecha_nacimiento,
                                               String sexo,
                                               String estado_civil,
                                               String parentesco,
                                               String  status,
                                               String fecha_registro,
                                               String plazos_espera,
                                               String poliza_exceso,
                                               String  codigo_filial,
                                               Integer  cedula,
                                               String fecha_egreso,
                                               String codigo_empleado,
                                               String aseguradora_exceso,
                                               String numero_poliza_exceso,
                                               String tipo_de_empleado,
                                               String hecho_por,
                                               String observaciones,
                                               String plan_exceso,
                                               String fecha_inicio_exceso,
                                               Double codigo_prima_exceso,
                                               Double sueldo,
                                               String plazos_de_espera_ezceso,
                                               String observaciones_exceso,
                                               String revisar_basico,
                                               String revisar_exceso,
                                               String fecha_ingreso_exceso,
                                               String fecha_egreso_exceso,
                                               Double prorrata,
                                               String cmf,
                                               String rl,
                                               String direccion,
                                               String maternidad,
                                               String exclusion_enfermedades,
                                               String sts_procesado,
                                               String cuenta_bancaria,
                                               Double cup,
                                               String sts_procesado_exc,
                                               String centro_costo,
                                               String sts_procesado_egreso,
                                               String sts_procesado_egreso_exc,
                                               String numero_poliza,
                                               String odontologico,
                                               String modificado_por,
                                               String fecha_modificacion,
                                               String maquina,
                                               String fecha_modif,
                                               String ficha,
                                               String congenita,
                                               String pre_existente,
                                               Integer codigo_odontologico,
                                               String ambulancia,
                                               Integer codigo_ambulancia,
                                               String asistencia,
                                               Integer codigo_asistencia,
                                               String bono_plan_incentivo,
                                               String aut_ren,
                                               Integer descuento,
                                               String anexo_vida,
                                               Integer codigo_anexo_vida,
                                               String renovar,
                                               String usuario_renovacion,
                                               String observaciones_renovacion,
                                               String email,
                                               Integer id_masivo,
                                               String mat_exceso,
                                               String enfermedad_cronica,
                                               String modulo_cronico,
                                               String mat_exceso2,
                                               String codigo_medico_mil_razones,
                                               Double suma_maternidad,
                                               String nacionalidad,
                                               String cod_area,
                                               String celular,
                                               String f_ing_mat_exc,
                                               Double suma_maternidad_exc,
                                               String codigo_exclusion,
                                               String fecha_solicitud_ingreso,
                                               String fecha_solicitud_egreso

                                               ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RecRespStringDto resp = new RecRespStringDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.nueva_suscripcion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
               //   CallableStatement stmt = conn.prepareCall("{call qualitas.nueva_suscripcion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                stmt.registerOutParameter(1, Types.CHAR);
                stmt.setString(1, numero_contrato);
                stmt.setString(2, codigo_localidad );
                stmt.setString(3, plan );
                stmt.setString(4, fecha_inicio );
                stmt.setInt(5, codigo_prima );
                stmt.setInt(6, codigo );
                stmt.setInt(7, certificado );
                stmt.setString(8, nombre );
                stmt.setString(9, fecha_ingreso );
                stmt.setString(10, fecha_nacimiento );
                stmt.setString(11, sexo );
                stmt.setString(12, estado_civil );
                stmt.setString(13, parentesco );
                stmt.setString(14, status );
                stmt.setString(15, fecha_registro );
                stmt.setString(16, plazos_espera );
                stmt.setString(17, poliza_exceso );
                stmt.setString(18, codigo_filial );
                stmt.setInt(19, cedula );
                stmt.setString(20, fecha_egreso );
                stmt.setString(21, codigo_empleado );
                stmt.setString(22, aseguradora_exceso );
                stmt.setString(23, numero_poliza_exceso );
                stmt.setString(24, tipo_de_empleado );
                stmt.setString(25, hecho_por );
                stmt.setString(26, observaciones );
                stmt.setString(27, plan_exceso );
                stmt.setString(28, fecha_inicio_exceso );
                stmt.setDouble(29, codigo_prima_exceso );
                stmt.setDouble(30, sueldo );
                stmt.setString(31, plazos_de_espera_ezceso );
                stmt.setString(32, observaciones_exceso );
                stmt.setString(33, revisar_basico );
                stmt.setString(34, revisar_exceso );
                stmt.setString(35, fecha_ingreso_exceso );
                stmt.setString(36, fecha_egreso_exceso );
                stmt.setDouble(37, prorrata );
                stmt.setString(38, cmf );
                stmt.setString(39, rl );
                stmt.setString(40, direccion );
                stmt.setString(41, maternidad );
                stmt.setString(42, exclusion_enfermedades );
                stmt.setString(43, sts_procesado );
                stmt.setString(44, cuenta_bancaria );
                stmt.setDouble(45, cup );
                stmt.setString(46, sts_procesado_exc );
                stmt.setString(47, centro_costo );
                stmt.setString(48, sts_procesado_egreso );
                stmt.setString(49, sts_procesado_egreso_exc );
                stmt.setString(50, numero_poliza );
                stmt.setString(51, odontologico );
                stmt.setString(52, modificado_por );
                stmt.setString(53, fecha_modificacion );
                stmt.setString(54, maquina );
                stmt.setString(55, fecha_modif );
                stmt.setString(56, ficha );
                stmt.setString(57, congenita );
                stmt.setString(58, pre_existente );
                stmt.setInt(59, codigo_odontologico );
                stmt.setString(60, ambulancia );
                stmt.setInt(61, codigo_ambulancia );
                stmt.setString(62, asistencia );
                stmt.setInt(63, codigo_asistencia );
                stmt.setString(64, bono_plan_incentivo );
                stmt.setString(65, aut_ren );
                stmt.setInt(66, descuento );
                stmt.setString(67, anexo_vida );
                stmt.setInt(68, codigo_anexo_vida );
                stmt.setString(69, renovar );
                stmt.setString(70, usuario_renovacion );
                stmt.setString(71, observaciones_renovacion );
                stmt.setString(72, email );
                stmt.setInt(73, id_masivo );
                stmt.setString(74, mat_exceso );
                stmt.setString(75, enfermedad_cronica );
                stmt.setString(76, modulo_cronico );
                stmt.setString(77, mat_exceso2 );
                stmt.setString(78, codigo_medico_mil_razones );
                stmt.setDouble(79, suma_maternidad );
                stmt.setString(80, nacionalidad );
                stmt.setString(81, cod_area );
                stmt.setString(82, celular );
                stmt.setString(83, f_ing_mat_exc );
                stmt.setDouble(84, suma_maternidad_exc );
                stmt.setString(85, codigo_exclusion );
                stmt.setString(86, fecha_solicitud_ingreso );
                stmt.setString(87, fecha_solicitud_egreso);



                stmt.execute();
                String resultado = stmt.getString(1);

                RecRespStringDto sol = new RecRespStringDto();
                sol.setRetorno(resultado);

                resp.setRetorno((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    ////Modulo de suscripcion  Consulta

    public List<InsSuscripcionDto> Consulta_Suscripcion() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<InsSuscripcionDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_suscripcion()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    InsSuscripcionDto sol = new InsSuscripcionDto();
                    ObjectMapper mapper = new ObjectMapper();
                  //  RespConsSuscripDto obj = mapper.readValue("",RespConsSuscripDto.class);

                    sol.setNumero_contrato(rs.getString("numero_contrato"));
                    sol.setCodigo_localidad(rs.getString("codigo_localidad"));
                    sol.setPlan(rs.getString("plan"));
                    sol.setFecha_inicio(rs.getString("fecha_inicio"));
                    sol.setCodigo_prima(rs.getInt("codigo_prima"));
                    sol.setCodigo(rs.getInt("codigo"));
                    sol.setCertificado(rs.getInt("certificado"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setFecha_ingreso(rs.getString("fecha_ingreso"));
                    sol.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                    sol.setSexo(rs.getString("sexo"));
                    sol.setEstado_civil(rs.getString("estado_civil"));
                    sol.setParentesco(rs.getString("parentesco"));
                    sol.setStatus(rs.getString("status"));
                    sol.setFecha_registro(rs.getString("fecha_registro"));
                    sol.setPlazos_espera(rs.getString("plazos_espera"));
                    sol.setPoliza_exceso(rs.getString("poliza_exceso"));
                    sol.setCodigo_filial(rs.getString("codigo_filial"));
                    sol.setCedula(rs.getInt("cedula"));
                    sol.setFecha_egreso(rs.getString("fecha_egreso"));
                    sol.setObservaciones_renovacion(rs.getString("observaciones_renovacion"));
                    sol.setEmail(rs.getString("email"));
                    sol.setCod_area(rs.getString("cod_area"));
                    sol.setCelular(rs.getString("celular"));
                    sol.setDireccion(rs.getString("direccion"));
                    sol.setMaternidad(rs.getString("maternidad"));
                    sol.setNacionalidad(rs.getString("nacionalidad"));
                    sol.setHecho_por(rs.getString("hecho_por"));
                    sol.setObservaciones(rs.getString("observaciones"));
                    sol.setExclusion_enfermedades(rs.getString("exclusion_enfermedades"));
                    sol.setCuenta_bancaria(rs.getString("cuenta_bancaria"));
                    sol.setEnfermedad_cronica(rs.getString("enfermedad_cronica"));
                    sol.setModulo_cronico(rs.getString("modulo_cronico"));
                    sol.setNumero_poliza(rs.getString("numero_poliza"));
                    sol.setEmail(rs.getString("email"));
                    sol.setCod_area(rs.getString("cod_area"));
                    sol.setCelular(rs.getString("celular"));
                    sol.setOdontologico(rs.getString("odontologico"));





                    resp.add(sol);
                }
                ObjectMapper mapper = new ObjectMapper();

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<InsSuscripcionDto> Consulta_Suscripcion_indv(Integer cedula, String contrato) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<InsSuscripcionDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_suscripcion_indv(?)}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setInt(1, cedula);
                stmt.setString(2, contrato);

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    InsSuscripcionDto sol = new InsSuscripcionDto();
                    ObjectMapper mapper = new ObjectMapper();
                    //  RespConsSuscripDto obj = mapper.readValue("",RespConsSuscripDto.class);

//                    sol.setNumero_contrato(rs.getString("numero_contrato"));
//                    sol.setCodigo_localidad(rs.getString("codigo_localidad"));
//                    sol.setPlan(rs.getString("plan"));
//                    sol.setFecha_inicio(rs.getString("fecha_inicio"));
//                    sol.setCodigo_prima(rs.getInt("codigo_prima"));
//                    sol.setCodigo(rs.getInt("codigo"));
//                    sol.setCertificado(rs.getInt("certificado"));
//                    sol.setNombre(rs.getString("nombre"));
//                    sol.setFecha_ingreso(rs.getString("fecha_ingreso"));
//                    sol.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
//                    sol.setSexo(rs.getString("sexo"));
//                    sol.setEstado_civil(rs.getString("estado_civil"));
//                    sol.setParentesco(rs.getString("parentesco"));
//                    sol.setStatus(rs.getString("status"));
//                    sol.setFecha_registro(rs.getString("fecha_registro"));
//                    sol.setPlazos_espera(rs.getString("plazos_espera"));
//                    sol.setPoliza_exceso(rs.getString("poliza_exceso"));
//                    sol.setCodigo_filial(rs.getString("codigo_filial"));
//                    sol.setCedula(rs.getInt("cedula"));
//                    sol.setFecha_egreso(rs.getString("fecha_egreso"));
//                    sol.setEmail(rs.getString("email"));
//                    sol.setCod_area(rs.getString("cod_area"));
//                    sol.setCelular(rs.getString("celular"));
                    sol.setNumero_contrato(rs.getString("numero_contrato"));
                    sol.setCodigo_localidad(rs.getString("codigo_localidad"));
                    sol.setPlan(rs.getString("plan"));
                    sol.setFecha_inicio(rs.getString("fecha_inicio"));
                    sol.setCodigo_prima(rs.getInt("codigo_prima"));
                    sol.setCodigo(rs.getInt("codigo"));
                    sol.setCertificado(rs.getInt("certificado"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setFecha_ingreso(rs.getString("fecha_ingreso"));
                    sol.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                    sol.setSexo(rs.getString("sexo"));
                    sol.setEstado_civil(rs.getString("estado_civil"));
                    sol.setParentesco(rs.getString("parentesco"));
                    sol.setStatus(rs.getString("status"));
                    sol.setFecha_registro(rs.getString("fecha_registro"));
                    sol.setPlazos_espera(rs.getString("plazos_espera"));
                    sol.setPoliza_exceso(rs.getString("poliza_exceso"));
                    sol.setCodigo_filial(rs.getString("codigo_filial"));
                    sol.setCedula(rs.getInt("cedula"));
                    sol.setFecha_egreso(rs.getString("fecha_egreso"));
                    sol.setObservaciones_renovacion(rs.getString("observaciones_renovacion"));
                    sol.setEmail(rs.getString("email"));
                    sol.setCod_area(rs.getString("cod_area"));
                    sol.setCelular(rs.getString("celular"));
                    sol.setDireccion(rs.getString("direccion"));
                    sol.setMaternidad(rs.getString("maternidad"));
                    sol.setNacionalidad(rs.getString("nacionalidad"));
                    sol.setHecho_por(rs.getString("hecho_por"));
                    sol.setObservaciones(rs.getString("observaciones"));
                    sol.setExclusion_enfermedades(rs.getString("exclusion_enfermedades"));
                    sol.setCuenta_bancaria(rs.getString("cuenta_bancaria"));
                    sol.setEnfermedad_cronica(rs.getString("enfermedad_cronica"));
                    sol.setModulo_cronico(rs.getString("modulo_cronico"));
                    sol.setNumero_poliza(rs.getString("numero_poliza"));
                    sol.setEmail(rs.getString("email"));
                    sol.setCod_area(rs.getString("cod_area"));
                    sol.setCelular(rs.getString("celular"));
                    sol.setOdontologico(rs.getString("odontologico"));
                    sol.setFecha_solicitud_egreso(rs.getString("fecha_solicitud_egreso"));






                    resp.add(sol);
                }
                ObjectMapper mapper = new ObjectMapper();

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


public RecRespStringDto ActualizoSuscripcion(  String numero_contrato,
                                               String  codigo_localidad,
                                               String  plan,
                                               String  fecha_inicio,
                                               Integer  codigo_prima,
                                               Integer  codigo,
                                               Integer   certificado,
                                               String  nombre,
                                               String  fecha_ingreso,
                                               String fecha_nacimiento,
                                               String sexo,
                                               String estado_civil,
                                               String parentesco,
                                               String  status,
                                               String fecha_registro,
                                               String plazos_espera,
                                               String poliza_exceso,
                                               String  codigo_filial,
                                               Integer  cedula,
                                               String fecha_egreso,
                                               String codigo_empleado,
                                               String aseguradora_exceso,
                                               String numero_poliza_exceso,
                                               String tipo_de_empleado,
                                               String hecho_por,
                                               String observaciones,
                                               String plan_exceso,
                                               String fecha_inicio_exceso,
                                               Double codigo_prima_exceso,
                                               Double sueldo,
                                               String plazos_de_espera_ezceso,
                                               String observaciones_exceso,
                                               String revisar_basico,
                                               String revisar_exceso,
                                               String fecha_ingreso_exceso,
                                               String fecha_egreso_exceso,
                                               Double prorrata,
                                               String cmf,
                                               String rl,
                                               String direccion,
                                               String maternidad,
                                               String exclusion_enfermedades,
                                               String sts_procesado,
                                               String cuenta_bancaria,
                                               Double cup,
                                               String sts_procesado_exc,
                                               String centro_costo,
                                               String sts_procesado_egreso,
                                               String sts_procesado_egreso_exc,
                                               String numero_poliza,
                                               String odontologico,
                                               String modificado_por,
                                               String fecha_modificacion,
                                               String maquina,
                                               String fecha_modif,
                                               String ficha,
                                               String congenita,
                                               String pre_existente,
                                               Integer codigo_odontologico,
                                               String ambulancia,
                                               Integer codigo_ambulancia,
                                               String asistencia,
                                               Integer codigo_asistencia,
                                               String bono_plan_incentivo,
                                               String aut_ren,
                                               Integer descuento,
                                               String anexo_vida,
                                               Integer codigo_anexo_vida,
                                               String renovar,
                                               String usuario_renovacion,
                                               String observaciones_renovacion,
                                               String email,
                                               Integer id_masivo,
                                               String mat_exceso,
                                               String enfermedad_cronica,
                                               String modulo_cronico,
                                               String mat_exceso2,
                                               String codigo_medico_mil_razones,
                                               Double suma_maternidad,
                                               String nacionalidad,
                                               String cod_area,
                                               String celular,
                                               String f_ing_mat_exc,
                                               Double suma_maternidad_exc,
                                               String codigo_exclusion,
                                               String fecha_solicitud_ingreso,
                                               String fecha_solicitud_egreso

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RecRespStringDto resp = new RecRespStringDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.actualiza_suscripcion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                //   CallableStatement stmt = conn.prepareCall("{call qualitas.nueva_suscripcion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                stmt.registerOutParameter(1, Types.CHAR);
                stmt.setString(1, numero_contrato);
                stmt.setString(2, codigo_localidad );
                stmt.setString(3, plan );
                stmt.setString(4, fecha_inicio );
                stmt.setInt(5, codigo_prima );
                stmt.setInt(6, codigo );
                stmt.setInt(7, certificado );
                stmt.setString(8, nombre );
                stmt.setString(9, fecha_ingreso );
                stmt.setString(10, fecha_nacimiento );
                stmt.setString(11, sexo );
                stmt.setString(12, estado_civil );
                stmt.setString(13, parentesco );
                stmt.setString(14, status );
                stmt.setString(15, fecha_registro );
                stmt.setString(16, plazos_espera );
                stmt.setString(17, poliza_exceso );
                stmt.setString(18, codigo_filial );
                stmt.setInt(19, cedula );
                stmt.setString(20, fecha_egreso );
                stmt.setString(21, codigo_empleado );
                stmt.setString(22, aseguradora_exceso );
                stmt.setString(23, numero_poliza_exceso );
                stmt.setString(24, tipo_de_empleado );
                stmt.setString(25, hecho_por );
                stmt.setString(26, observaciones );
                stmt.setString(27, plan_exceso );
                stmt.setString(28, fecha_inicio_exceso );
                stmt.setDouble(29, codigo_prima_exceso );
                stmt.setDouble(30, sueldo );
                stmt.setString(31, plazos_de_espera_ezceso );
                stmt.setString(32, observaciones_exceso );
                stmt.setString(33, revisar_basico );
                stmt.setString(34, revisar_exceso );
                stmt.setString(35, fecha_ingreso_exceso );
                stmt.setString(36, fecha_egreso_exceso );
                stmt.setDouble(37, prorrata );
                stmt.setString(38, cmf );
                stmt.setString(39, rl );
                stmt.setString(40, direccion );
                stmt.setString(41, maternidad );
                stmt.setString(42, exclusion_enfermedades );
                stmt.setString(43, sts_procesado );
                stmt.setString(44, cuenta_bancaria );
                stmt.setDouble(45, cup );
                stmt.setString(46, sts_procesado_exc );
                stmt.setString(47, centro_costo );
                stmt.setString(48, sts_procesado_egreso );
                stmt.setString(49, sts_procesado_egreso_exc );
                stmt.setString(50, numero_poliza );
                stmt.setString(51, odontologico );
                stmt.setString(52, modificado_por );
                stmt.setString(53, fecha_modificacion );
                stmt.setString(54, maquina );
                stmt.setString(55, fecha_modif );
                stmt.setString(56, ficha );
                stmt.setString(57, congenita );
                stmt.setString(58, pre_existente );
                stmt.setInt(59, codigo_odontologico );
                stmt.setString(60, ambulancia );
                stmt.setInt(61, codigo_ambulancia );
                stmt.setString(62, asistencia );
                stmt.setInt(63, codigo_asistencia );
                stmt.setString(64, bono_plan_incentivo );
                stmt.setString(65, aut_ren );
                stmt.setInt(66, descuento );
                stmt.setString(67, anexo_vida );
                stmt.setInt(68, codigo_anexo_vida );
                stmt.setString(69, renovar );
                stmt.setString(70, usuario_renovacion );
                stmt.setString(71, observaciones_renovacion );
                stmt.setString(72, email );
                stmt.setInt(73, id_masivo );
                stmt.setString(74, mat_exceso );
                stmt.setString(75, enfermedad_cronica );
                stmt.setString(76, modulo_cronico );
                stmt.setString(77, mat_exceso2 );
                stmt.setString(78, codigo_medico_mil_razones );
                stmt.setDouble(79, suma_maternidad );
                stmt.setString(80, nacionalidad );
                stmt.setString(81, cod_area );
                stmt.setString(82, celular );
                stmt.setString(83, f_ing_mat_exc );
                stmt.setDouble(84, suma_maternidad_exc );
                stmt.setString(85, codigo_exclusion );
                stmt.setString(86, fecha_solicitud_ingreso );
                stmt.setString(87, fecha_solicitud_egreso);



                stmt.execute();
                String resultado = stmt.getString(1);

                RecRespStringDto sol = new RecRespStringDto();
                sol.setRetorno(resultado);

                resp.setRetorno((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


  ///////Consulta estaus suscripcion


    public List<EstatusSuscDto> Estatus_Suscripcion() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<EstatusSuscDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.estatus_suscripcion()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    EstatusSuscDto sol = new EstatusSuscDto();
                    ObjectMapper mapper = new ObjectMapper();
                    //  RespConsSuscripDto obj = mapper.readValue("",RespConsSuscripDto.class);

                   // sol.setCodigo_suscripcion(rs.getInt("codigo_suscripcion"));
                    sol.setDescripcion(rs.getString("descripcion"));





                    resp.add(sol);
                }
                ObjectMapper mapper = new ObjectMapper();

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    ///////Consulta Parentesco


    public List<ParentescoDto> Parentesco() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ParentescoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.parentesco()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ParentescoDto sol = new ParentescoDto();
                    ObjectMapper mapper = new ObjectMapper();
                    //  RespConsSuscripDto obj = mapper.readValue("",RespConsSuscripDto.class);

                   // sol.setCodigo_parentesco(rs.getString("codigo_parentesco"));
                    sol.setDescripcion(rs.getString("descripcion"));





                    resp.add(sol);
                }
                ObjectMapper mapper = new ObjectMapper();

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


 ///////////////ramo sub ramo
public List<RamoSubRamoDto> RamoSubRamo(String ramo) {

    Connection conn = null;
    ConexionDto conexion = new ConexionDto();
    List<RamoSubRamoDto> resp =   new ArrayList<>();

    try {
        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
        conn.setAutoCommit(false);
        if (conn != null) {



            CallableStatement stmt = conn.prepareCall("{? = call qualitas.ramo_subramo()}");
            stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
            stmt.setString(1, ramo);



            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

            while (rs.next()) {


                RamoSubRamoDto sol = new RamoSubRamoDto();
                ObjectMapper mapper = new ObjectMapper();

                sol.setCodigo_sub_ramo(rs.getString("codigo_sub_ramo"));
                sol.setDescripcion(rs.getString("descripcion"));





                resp.add(sol);
            }


            rs.close();
            stmt.close();
            conn.close();


            System.out.println("Connection Exitosa");
        } else {
            System.out.println("Connection Fallida");
        }

    } catch (Exception e) {
        System.out.println(e);
    }

    return resp;

    }

    ///////////////ramo
    public List<RamoDto> Ramo() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<RamoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.ramo_ramo()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    RamoDto sol = new RamoDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setCod_ramo_pol(rs.getString("cod_ramo_pol"));
                    sol.setDescripcion(rs.getString("descripcion"));





                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    ///////////////forma pago
    public List<FormaPagoDto> FormaPago() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<FormaPagoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.forma_pago()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    FormaPagoDto sol = new FormaPagoDto();
                    ObjectMapper mapper = new ObjectMapper();
                    //  RespConsSuscripDto obj = mapper.readValue("",RespConsSuscripDto.class);

                   // sol.setCodigo_pago(rs.getInt("codigo_pago"));
                    sol.setDescripcion(rs.getString("descripcion"));





                    resp.add(sol);
                }
                ObjectMapper mapper = new ObjectMapper();

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }





    ///////////////tipo contrato
    public List<FormaPagoDto> TipoContrato() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<FormaPagoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.tipo_contrato()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    FormaPagoDto sol = new FormaPagoDto();
                    ObjectMapper mapper = new ObjectMapper();
                    //  RespConsSuscripDto obj = mapper.readValue("",RespConsSuscripDto.class);

                    // sol.setCodigo_pago(rs.getInt("codigo_pago"));
                    sol.setDescripcion(rs.getString("descripcion"));





                    resp.add(sol);
                }
                ObjectMapper mapper = new ObjectMapper();

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    ///////////////estatus_contrato
    public List<FormaPagoDto> EstatusContrato() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<FormaPagoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.estatus_contrato()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    FormaPagoDto sol = new FormaPagoDto();
                    ObjectMapper mapper = new ObjectMapper();
                    //  RespConsSuscripDto obj = mapper.readValue("",RespConsSuscripDto.class);

                    // sol.setCodigo_pago(rs.getInt("codigo_pago"));
                    sol.setDescripcion(rs.getString("descripcion"));





                    resp.add(sol);
                }
                ObjectMapper mapper = new ObjectMapper();

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    ///////////////sucursal
    public List<SucursalDto> Sucursal() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<SucursalDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.sucursal()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    SucursalDto sol = new SucursalDto();
                    ObjectMapper mapper = new ObjectMapper();
                    //  RespConsSuscripDto obj = mapper.readValue("",RespConsSuscripDto.class);

                    sol.setCodigo_sucursal(rs.getInt("codigo_sucursal"));
                    sol.setDescripcion(rs.getString("descripcion"));





                    resp.add(sol);
                }
                ObjectMapper mapper = new ObjectMapper();

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }



    ///////////////Localidad
public List<LocalidadDto> Localidad() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<LocalidadDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.localidad()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    LocalidadDto sol = new LocalidadDto();
                    ObjectMapper mapper = new ObjectMapper();
                    //  RespConsSuscripDto obj = mapper.readValue("",RespConsSuscripDto.class);

                    sol.setCodigo_localidad(rs.getString("codigo_localidad"));
                    sol.setNombre(rs.getString("nombre"));





                    resp.add(sol);
                }
                ObjectMapper mapper = new ObjectMapper();

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    ///////////////sexo
    public List<FormaPagoDto> Sexo() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<FormaPagoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.sexo()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    FormaPagoDto sol = new FormaPagoDto();
                    ObjectMapper mapper = new ObjectMapper();
                    //  RespConsSuscripDto obj = mapper.readValue("",RespConsSuscripDto.class);

                    // sol.setCodigo_pago(rs.getInt("codigo_pago"));
                    sol.setDescripcion(rs.getString("descripcion"));





                    resp.add(sol);
                }
                ObjectMapper mapper = new ObjectMapper();

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }
    ///////////////EstadoCivil
    public List<FormaPagoDto> EstadoCivil() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<FormaPagoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.estado_civil()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    FormaPagoDto sol = new FormaPagoDto();
                    ObjectMapper mapper = new ObjectMapper();
                    //  RespConsSuscripDto obj = mapper.readValue("",RespConsSuscripDto.class);

                    // sol.setCodigo_pago(rs.getInt("codigo_pago"));
                    sol.setDescripcion(rs.getString("descripcion"));





                    resp.add(sol);
                }
                ObjectMapper mapper = new ObjectMapper();

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    ///////////////producto
    public List<ProductoDto> Producto(String ramo) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ProductoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.producto()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setString(1, ramo);



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ProductoDto sol = new ProductoDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setCod_prod(rs.getString("cod_prod"));
                    sol.setDesc_prod(rs.getString("desc_prod"));
                    sol.setFe_inicio(rs.getString("fe_inicio"));
                    sol.setFe_termino(rs.getString("fe_termino"));
                    sol.setSt_prod(rs.getString("st_prod"));
                    sol.setFe_st_prod(rs.getString("fe_st_prod"));





                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }
///////////////Planes

    public List<PlanesSalidaDto> Planes(String ramo, String producto) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<PlanesSalidaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.planes(?)}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setString(1, ramo);
                stmt.setString(2, producto);


                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    PlanesSalidaDto sol = new PlanesSalidaDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setCod_plan(rs.getString("cod_plan"));
                    sol.setDesc_plan(rs.getString("desc_plan"));
                    sol.setFe_inicio(rs.getString("fe_inicio"));
                    sol.setFe_hasta(rs.getString("fe_hasta"));
                    sol.setFe_st_plan(rs.getString("fe_st_plan"));
                    sol.setSt_plan(rs.getString("st_plan"));
                    sol.setDescripcion(rs.getString("descripcion"));





                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

///////////////Cobertura

    public List<CobertSalidaDto> Cobertura(String ramo, String producto, String plan) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<CobertSalidaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.coberturas(?,?)}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setString(1, ramo);
                stmt.setString(2, producto);
                stmt.setString(3, plan);


                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    CobertSalidaDto sol = new CobertSalidaDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setCod_ramo_cont(rs.getString("cod_ramo_cont"));
                    sol.setCod_cobertura(rs.getString("cod_cobertura"));
                    sol.setDescripcion(rs.getString("descripcion"));
                    sol.setCod_moneda(rs.getString("cod_moneda"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setFe_efectiva(rs.getString("fe_efectiva"));
                    sol.setFe_hasta(rs.getString("fe_hasta"));
                    sol.setCod_sta(rs.getString("cod_sta"));
                    sol.setDescripcion_sts(rs.getString("descripcion_sts"));
                    sol.setFec_sta(rs.getString("fec_sta"));





                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    ///////////////Suma Asegurada

    public List<SumaAsegSalidaDto> SumaAsegurada(String ramo, String producto, String plan, String cobertura) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<SumaAsegSalidaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.suma_asegurada(?,?,?)}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setString(1, ramo);
                stmt.setString(2, producto);
                stmt.setString(3, plan);
                stmt.setString(4, cobertura);


                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    SumaAsegSalidaDto sol = new SumaAsegSalidaDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setCod_ramo_cont(rs.getString("cod_ramo_cont"));
                    sol.setCod_cobertura(rs.getString("cod_cobertura"));
                    sol.setDescripcion(rs.getString("descripcion"));
                    sol.setSuma_asegurada(rs.getDouble("suma_asegurada"));
                    sol.setMontomensual(rs.getDouble("montomensual"));






                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    ///////////////Baremos

    public List<BaremosSalidaDto> Baremos(String ramo, String producto, String plan, String cobertura) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<BaremosSalidaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.servicios_baremos(?,?,?)}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setString(1, ramo);
                stmt.setString(2, producto);
                stmt.setString(3, plan);
                stmt.setString(4, cobertura);


                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    BaremosSalidaDto sol = new BaremosSalidaDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setCod_plan(rs.getString("cod_plan"));
                    sol.setCod_ramo_cont(rs.getString("cod_ramo_cont"));
                    sol.setCod_cobertura(rs.getString("cod_cobertura"));
                    sol.setCod_servicio(rs.getInt("cod_servicio"));
                    sol.setDescripcion(rs.getString("descripcion"));
                    sol.setPlazo_espera(rs.getInt("plazo_espera"));
                    sol.setEdad_minima(rs.getString("edad_minima"));
                    sol.setEdad_min_calculado(rs.getString("edad_min_calculado"));
                    sol.setEdad_maxima(rs.getString("edad_maxima"));
                    sol.setEdad_max_calculado(rs.getString("edad_max_calculado"));



                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    ///////////////Reclamos

    public List<ConsultaDatosReclamoDto> ConsultaDataReclamo(Integer certificado) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ConsultaDatosReclamoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_datos_reclamo()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setInt(1, certificado);



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ConsultaDatosReclamoDto sol = new ConsultaDatosReclamoDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setTipo_poliza(rs.getString("tipo_poliza"));
                    sol.setCertificado_titular(rs.getInt("certificado_titular"));
                    sol.setNumero_contrato(rs.getString("numero_contrato"));
                    sol.setNombre_contratante(rs.getString("nombre_contratante"));
                    sol.setNombre_titular(rs.getString("nombre_titular"));
                    sol.setCodigo_plan(rs.getString("codigo_plan"));
                    sol.setDescricion_plan(rs.getString("descricion_plan"));
                    sol.setNombre_beneficiario(rs.getString("nombre_beneficiario"));
                    sol.setCedula_beneficiario(rs.getInt("CEDULA_BENEFICIARIO"));
                    sol.setParentesco_beneficiario(rs.getString("parentesco_beneficiario"));
                    sol.setEdad(rs.getString("edad"));
                    sol.setCiudad_titular(rs.getString("ciudad_titular"));
                    sol.setSexo(rs.getString("sexo"));
                    sol.setResponsable(rs.getString("responsable"));
                    sol.setMatrix(rs.getString("MATRIX"));
                    sol.setCodigo_suscripcion(rs.getString("codigo"));
                    sol.setSubramo(rs.getString("descripcionsub"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    ///////////////Servicios Reclamos

    public List<ServiciosDto> ConsultaServiciosReclamo() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ServiciosDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_servicios_reclamo()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type




                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ServiciosDto sol = new ServiciosDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setCod_servicio(rs.getInt("cod_servicio"));
                    sol.setDescripcion(rs.getString("descripcion"));

                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }
///////////////ConsultaClinicasReclamo

    public List<ClinicasDto> ConsultaClinicasReclamo() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ClinicasDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_clinica_reclamo()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type




                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ClinicasDto sol = new ClinicasDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setCodigo(rs.getString("codigo"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setRif(rs.getString("rif"));
                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    ///////////////consulta_medicos_reclamo

    public List<MedicoDto> ConsultaMedicosReclamo() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<MedicoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_medicos_reclamo()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type




                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    MedicoDto sol = new MedicoDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setCodigo(rs.getString("codigo"));
                    sol.setNombre(rs.getString("nombre"));

                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    ///////////////Reclamos

    public List<IntervencionDto> ConsultaIntervReclamo(Integer servicio) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<IntervencionDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_baremos_reclamo()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setInt(1, servicio);



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    IntervencionDto sol = new IntervencionDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setCod_intervencion(rs.getInt("cod_intervencion"));
                    sol.setDescripcion(rs.getString("descripcion"));
                    sol.setMonto(rs.getDouble("monto"));
                    sol.setTipo_reclamo(rs.getString("tipo_reclamo"));




                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }



 public List<EstatusDto> ConsultaEstatusReclamo() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<EstatusDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_estatus_reclamo()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type




                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    EstatusDto sol = new EstatusDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setCod_estatus(rs.getString("cod_estatus"));
                    sol.setDescripcion(rs.getString("descripcion"));

                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

///////////////Cobertura

    public RespReclamoDto NuevoReclamo(Integer    P_codigo_suscripcion  ,
                                       String    P_codigo_intervencion  ,
                                       String    P_codigo_tipo_servicio  ,
                                       String    P_codigo_medico ,
                                       String    P_codigo_clinica ,
                                       String    P_fecha_ingreso  ,
                                       Integer    P_numero_servicio  ,
                                       String    P_tipo_poliza   ,
                                       String    P_hecho_por  ,
                                       String    P_aprobado_por  ,
                                       String    P_fecha_recepcion  ,
                                       String    P_fecha_ocurrencia  ,
                                       String    P_status  ,
                                       String    P_departamento ,
                                       String    P_tipo_cheque    ,
                                       String    P_fecha_egreso ,
                                       String    P_fecha_pago ,
                                       Integer    P_clase_pago ,
                                       String    P_codigo_medico_opinion ,
                                       String    P_numero_aval_clave,
                                       Double   P_deducible ,
                                       Double   P_gastos_clinica ,
                                       Double   P_gastos_no_amparados_neto ,
                                       Double   P_honorarios_medicos ,
                                       Double   P_gastos_ambulatorios ,
                                       Double   P_descuento_clinicas ,
                                       Double   P_descuento_medico ,
                                       Double   P_descuento_neto ,
                                       Double   P_porcentaje_descuento ,
                                       Double   P_porcentaje_reembolso ,
                                       Double   P_monto_facturado ,
                                       Double   P_monto_pagado ,
                                       Double   P_neto_a_pagar ,
                                       Double   P_total_a_pagar ,
                                       Double   P_total_facturado ,
                                       Double   P_total_elegible ,
                                       Double   P_sub_total ,
                                       Double   P_islr_neto ,
                                       String    P_autorizado ,
                                       String    P_envio_carta,
                                       String    P_fecha_envio_administracion ,
                                       String    P_fecha_envio_factura ,
                                       String    P_fecha_recepcion_factura ,
                                       Double    P_descuento_clinica_compromiso ,
                                       Double    P_descuento_medico_compromiso ,
                                       String     P_numero_factura ,
                                       String     P_monto_facturado_letra  ,
                                       String     P_monto_pagado_letra  ,
                                       String     P_diagnostico  ,
                                       String     P_comentarios  ,
                                       String     P_observaciones_auditado  ,
                                       String     P_segunda_opinion_medica  ,
                                       Double    P_ahorro_opinion ,
                                       String     P_observaciones_opinion  ,
                                       String     P_fecha_registro  ,
                                       Double    P_ajuste_baremo ,
                                       String     P_medico_auditor  ,
                                       Integer     P_factura ,
                                       Integer     P_numero ,
                                       Double    P_iva,
                                       Integer     P_control ,
                                       Integer     P_ca_rel,
                                       String     P_tipo_reclamo,
                                       Double       P_iva_retenido,
                                       Integer     P_porcentaje_retencion,
                                       Integer     P_comprobante,
                                       String     P_numero_contrato  ,
                                       String     P_hecho_por_fact  ,
                                       String     P_modificado_por  ,
                                       String     P_fecha_modificacion,
                                       String     P_fecha_modif ,
                                       String     P_maquina  ,
                                       String     P_celular  ,
                                       String     P_cod_area,
                                       String     P_fecha_valor ,
                                       String     P_fecha_recepcion_atc ,
                                       Integer     P_referencia_atc ,
                                       String     P_codigo_filial  ,
                                       String     P_centro_costo  ,
                                       Integer     P_nu_servicio_unico ,
                                       String     P_fecha_emision_factura ,
                                       String     P_remesa  ,
                                       String     P_operador  ,
                                       String     P_fecha_operador ,
                                       String     P_cod_cobertura  ,
                                       String     P_factura_global,
                                       Integer   p_numero_finiquito
                                        ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RespReclamoDto resp =  new RespReclamoDto();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.nuevo_reclamo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
//                CallableStatement stmt = conn.prepareCall("{? = call qualitas.nuevo_reclamo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                stmt.registerOutParameter(1, Types.INTEGER); // Set the output parameter type
                stmt.setInt(1, P_codigo_suscripcion  );
                stmt.setString(2, P_codigo_intervencion  );
                stmt.setString(3, P_codigo_tipo_servicio  );
                stmt.setString(4, P_codigo_medico );
                stmt.setString(5, P_codigo_clinica );
                stmt.setString(6, P_fecha_ingreso  );
                stmt.setInt(7, P_numero_servicio  );
                stmt.setString(8, P_tipo_poliza   );
                stmt.setString(9, P_hecho_por  );
                stmt.setString(10, P_aprobado_por  );
                stmt.setString(11, P_fecha_recepcion  );
                stmt.setString(12, P_fecha_ocurrencia  );
                stmt.setString(13, P_status  );
                stmt.setString(14, P_departamento );
                stmt.setString(15, P_tipo_cheque    );
                stmt.setString(16, P_fecha_egreso );
                stmt.setString(17, P_fecha_pago );
                stmt.setInt(18, P_clase_pago );
                stmt.setString(19, P_codigo_medico_opinion );
                stmt.setString(20, P_numero_aval_clave  );
                stmt.setDouble(21, P_deducible );
                stmt.setDouble(22, P_gastos_clinica );
                stmt.setDouble(23, P_gastos_no_amparados_neto );
                stmt.setDouble(24, P_honorarios_medicos );
                stmt.setDouble(25, P_gastos_ambulatorios );
                stmt.setDouble(26, P_descuento_clinicas );
                stmt.setDouble(27, P_descuento_medico );
                stmt.setDouble(28, P_descuento_neto );
                stmt.setDouble(29, P_porcentaje_descuento );
                stmt.setDouble(30, P_porcentaje_reembolso );
                stmt.setDouble(31, P_monto_facturado );
                stmt.setDouble(32, P_monto_pagado );
                stmt.setDouble(33, P_neto_a_pagar );
                stmt.setDouble(34, P_total_a_pagar );
                stmt.setDouble(35, P_total_facturado );
                stmt.setDouble(36, P_total_elegible );
                stmt.setDouble(37, P_sub_total );
                stmt.setDouble(38, P_islr_neto );
                stmt.setString(39, P_autorizado );
                stmt.setString(40, P_envio_carta );
                stmt.setString(41, P_fecha_envio_administracion );
                stmt.setString(42, P_fecha_envio_factura );
                stmt.setString(43, P_fecha_recepcion_factura );
                stmt.setDouble(44, P_descuento_clinica_compromiso );
                stmt.setDouble(45, P_descuento_medico_compromiso );
                stmt.setString(46, P_numero_factura );
                stmt.setString(47, P_monto_facturado_letra  );
                stmt.setString(48, P_monto_pagado_letra  );
                stmt.setString(49, P_diagnostico  );
                stmt.setString(50, P_comentarios  );
                stmt.setString(51, P_observaciones_auditado  );
                stmt.setString(52, P_segunda_opinion_medica  );
                stmt.setDouble(53, P_ahorro_opinion );
                stmt.setString(54, P_observaciones_opinion  );
                stmt.setString(55, P_fecha_registro  );
                stmt.setDouble(56, P_ajuste_baremo );
                stmt.setString(57, P_medico_auditor  );
                stmt.setInt(58, P_factura );
                stmt.setInt(59, P_numero );
                stmt.setDouble(60, P_iva );
                stmt.setInt(61, P_control );
                stmt.setInt(62, P_ca_rel );
                stmt.setString(63, P_tipo_reclamo  );
                stmt.setDouble(64, P_iva_retenido );
                stmt.setInt(65, P_porcentaje_retencion );
                stmt.setInt(66, P_comprobante );
                stmt.setString(67, P_numero_contrato  );
                stmt.setString(68, P_hecho_por_fact  );
                stmt.setString(69, P_modificado_por  );
                stmt.setString(70, P_fecha_modificacion );
                stmt.setString(71, P_fecha_modif );
                stmt.setString(72, P_maquina  );
                stmt.setString(73, P_celular  );
                stmt.setString(74, P_cod_area  );
                stmt.setString(75, P_fecha_valor );
                stmt.setString(76, P_fecha_recepcion_atc );
                stmt.setInt(77, P_referencia_atc );
                stmt.setString(78, P_codigo_filial  );
                stmt.setString(79, P_centro_costo  );
                stmt.setInt(80, P_nu_servicio_unico );
                stmt.setString(81, P_fecha_emision_factura );
                stmt.setString(82, P_remesa  );
                stmt.setString(83, P_operador  );
                stmt.setString(84, P_fecha_operador );
                stmt.setString(85, P_cod_cobertura  );
                stmt.setString(86, P_factura_global );
                stmt.setInt(87, p_numero_finiquito );



                stmt.execute();
                Integer resultado = stmt.getInt(1);

                RespReclamoDto sol = new RespReclamoDto();
                sol.setRespuestarecl(resultado);

               // resp.((resultado));
                resp.setRespuestarecl((resultado));
                conn.commit();


               // rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

public RespReclamoDto  DetalleReclamo(Integer idreclamo, Integer tipoServicio, Integer tipoIntervecion,
                                          Double monto

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RespReclamoDto resp = new RespReclamoDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.detalle_nuevo(?,?,?,?)}");
                //LocalDate localDate = LocalDate.of(fechaMovimiento);
                //  java.sql.Date sqlDate = java.sql.Date.valueOf(fechaMovimiento);

                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.setInt(1, idreclamo);
                stmt.setInt(2, tipoServicio);
                stmt.setInt(3, tipoIntervecion);
                stmt.setDouble(4, monto);


                stmt.execute();
                Integer resultado = stmt.getInt(1);

                RespReclamoDto sol = new RespReclamoDto();
                sol.setRespuestarecl(resultado);

                resp.setRespuestarecl((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public List<ConsultaMontosDto> ConsultaMontos(Double montototal) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ConsultaMontosDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.calcular_montos()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setDouble(1, montototal);



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ConsultaMontosDto sol = new ConsultaMontosDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setP_monto_total(rs.getDouble("p_monto_total"));
                    sol.setP_gastoglinica(rs.getDouble("p_gastoglinica"));
                    sol.setP_descclinica(rs.getDouble("p_descclinica"));
                    sol.setP_honmedicos(rs.getDouble("p_honmedicos"));
                    sol.setP_descmedico(rs.getDouble("p_descmedico"));
                    sol.setP_tfacturado(rs.getDouble("p_tfacturado"));
                    sol.setP_descfact(rs.getDouble("p_descfact"));
                    sol.setP_montofacturado(rs.getDouble("p_montofacturado"));
                    sol.setP_gastosnoamp(rs.getDouble("p_gastosnoamp"));
                    sol.setP_totalelegible(rs.getDouble("p_totalelegible"));
                    sol.setP_deducible(rs.getDouble("p_deducible"));
                    sol.setP_subtotal(rs.getDouble("p_subtotal"));
                    sol.setP_reembolso(rs.getDouble("p_reembolso"));
                    sol.setP_totalapagar(rs.getDouble("p_totalapagar"));
                    sol.setP_descNeto(rs.getDouble("p_descNeto"));
                    sol.setP_mtopagado(rs.getDouble("p_mtopagado"));
                    sol.setP_montoislr(rs.getDouble("p_montoislr"));
                    sol.setP_ivaretenido(rs.getDouble("p_ivaretenido"));
                    sol.setP_netoapagar(rs.getDouble("p_netoapagar"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }



    public List<ConsultaFacturaDto> ConsultaFactura(String factura, String contrato,Integer cedula ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ConsultaFacturaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_factura(?,?)}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
               // stmt.setString(1, factura);
                if (factura != null) {
                    stmt.setString(1, factura);
                } else {
                    stmt.setNull(1, Types.VARCHAR);
                }
                //stmt.setString(2, contrato);
                if (contrato != null) {
                    stmt.setString(2, contrato);
                } else {
                    stmt.setNull(2, Types.VARCHAR);
                }
               // stmt.setInt(3, cedula);
                if (cedula != null) {
                    stmt.setInt(3, cedula);
                } else {
                    stmt.setNull(3, Types.INTEGER);
                }




                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ConsultaFacturaDto sol = new ConsultaFacturaDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setFactura(rs.getString("factura"));
                    sol.setFecha_emision(rs.getString("fecha_emision"));
                    sol.setNumero_contrato(rs.getString("numero_contrato"));
                    sol.setDescripcion(rs.getString("descripcion"));
                    sol.setMonto_facturado(rs.getDouble("monto_facturado"));
                    sol.setPorcentaje_retencion(rs.getDouble("porcentaje_retencion"));
                    sol.setMonto_retenido(rs.getDouble("monto_retenido"));
                    sol.setStatus(rs.getString("status"));
                    sol.setCodigo_sucursal(rs.getInt("codigo_sucursal"));
                    sol.setMonto_bs(rs.getDouble("monto_bs"));
                    sol.setMonto_retenido_bs(rs.getDouble("monto_retenido_bs"));
                    sol.setMonto_neto_bs(rs.getDouble("monto_neto_bs"));
                    sol.setCertificado(rs.getInt("certificado"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

public RetornaDto CambiaEstatusFactura(String factura, String estatus

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RetornaDto resp = new RetornaDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.cambio_estatus_factura(?,?)}");
                //LocalDate localDate = LocalDate.of(fechaMovimiento);
                //  java.sql.Date sqlDate = java.sql.Date.valueOf(fechaMovimiento);

                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, factura);
                stmt.setString(2, estatus);


                stmt.execute();
                String resultado = stmt.getString(1);

                RetornaDto sol = new RetornaDto();
                sol.setRetorna(resultado);

                resp.setRetorna((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<PlanesSuscripcionDto> PlanesSus() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<PlanesSuscripcionDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.planes_suscripcion()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    PlanesSuscripcionDto sol = new PlanesSuscripcionDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setCod_plan(rs.getString("cod_plan"));
                    sol.setDesc_plan(rs.getString("desc_plan"));
                    sol.setSumaasegurada(rs.getDouble("suma_asegurada"));






                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public List<EstatusFacturaDto> EstatusFactura() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<EstatusFacturaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_estatus_factura()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    EstatusFacturaDto sol = new EstatusFacturaDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setEstatus(rs.getString("estatus"));
                    sol.setDescripcion(rs.getString("descripcion"));

                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    public List<ConsultaDatosSuscDto> ConsultaDatosSuscripcion(String contrato

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ConsultaDatosSuscDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.consulta_datos_suscripcion(?)}");


                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setString(1, contrato);



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ConsultaDatosSuscDto sol = new ConsultaDatosSuscDto();
                    ObjectMapper mapper = new ObjectMapper();


                    sol.setNumero_contrato(rs.getString("NUMERO_CONTRATO"));
                    sol.setNombre_contratante(rs.getString("NOMBRE_CONTRATANTE"));
                    sol.setFecha_desde(rs.getString("fecha_desde"));
                    sol.setFecha_hasta(rs.getString("fecha_hasta"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public List<OtrosDatosSusDto> OtrosDatosSuscripcion(String contrato

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<OtrosDatosSusDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.consulta_otros_sucripcion(?)}");


                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setString(1, contrato);



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    OtrosDatosSusDto sol = new OtrosDatosSusDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setCertificado(rs.getInt("CERTIFICADO_TITULAR"));
                    sol.setNumero_contrato(rs.getString("NUMERO_CONTRATO"));
                    sol.setNombre_contratante(rs.getString("NOMBRE_CONTRATANTE"));
                    sol.setNombre_titular(rs.getString("NOMBRE_TITULAR"));
                    sol.setCodigo_plan(rs.getString("codigo_plan"));
                    sol.setDescripcion_plan(rs.getString("DESCRICION_PLAN"));
                    sol.setFecha_desde(rs.getString("fecha_desde"));
                    sol.setFecha_hasta(rs.getString("fecha_hasta"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }
    public ObetnerEdadDto ObtenerEdad(String fechainicio, String fechafin

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        ObetnerEdadDto resp = new ObetnerEdadDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call seguro.obtener_edad(?,?)}");
             //   LocalDate localDate = LocalDate.of(fechainicio);
                java.sql.Date sqlDateini = java.sql.Date.valueOf(fechainicio);
                java.sql.Date sqlDatefin = java.sql.Date.valueOf(fechafin);
                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.setDate(1, sqlDateini);
                stmt.setDate(2, sqlDatefin);


                stmt.execute();
                Integer resultado = stmt.getInt(1);

                ObetnerEdadDto sol = new ObetnerEdadDto();
                sol.setEdad(resultado);

                resp.setEdad((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public List<ConsultaCronoDto> ConsultaCronograma(String contrato, Integer suscripcion

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ConsultaCronoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.consulta_cronograma(?,?)}");


                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setString(1, contrato);
                stmt.setInt(2, suscripcion);



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ConsultaCronoDto sol = new ConsultaCronoDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setNuemrocuota(rs.getInt("NUMERO_CUOTA"));
                    sol.setFechapago(rs.getString("FECHA_PAGO"));
                    sol.setCuotamensualdiv(rs.getDouble("CUOTA_MENSUAL_DIV"));
                    sol.setCuotamensualbs(rs.getDouble("CUOTA_MENSUAL_BS"));
                    sol.setEstatus(rs.getString("ESTATUS"));
                    sol.setCuotaanual(rs.getDouble("CUOTA_ANUAL"));
                    sol.setFechaefectivapago(rs.getString("FECHA_EFECTIVA_PAGO"));
                    sol.setTasa(rs.getDouble("tasa"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public RetornoCobraCuotasDto ActualizaCuotas(String contrato,
                                                 Integer suscripcion,
                                                 Integer cuotas,
                                                 Integer mumero,
                                                 Double  montobs,
                                                 Double  montodiv,
                                                 String  fechapago,
                                                 String  usuario

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RetornoCobraCuotasDto resp = new RetornoCobraCuotasDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.actualiza_cuotas(?,?,?,?,?,?,?,?)}");
                //   LocalDate localDate = LocalDate.of(fechainicio);
               // java.sql.Date sqlDateini = java.sql.Date.valueOf(fechainicio);
               // java.sql.Date sqlDatefin = java.sql.Date.valueOf(fechafin);
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, contrato);
                stmt.setInt(2, suscripcion);
                stmt.setInt(3, cuotas);
                stmt.setInt(4, mumero);
                stmt.setDouble(5, montobs);
                stmt.setDouble(6, montodiv);
                stmt.setString(7, fechapago);
                stmt.setString(8, usuario);

                stmt.execute();
                String resultado = stmt.getString(1);

                RetornoCobraCuotasDto sol = new RetornoCobraCuotasDto();
                sol.setResultado(resultado);

                resp.setResultado((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public RetornoCobraCuotasDto CobraCuota(String contrato, Integer suscripcion, Integer cuotas, Integer mumero

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RetornoCobraCuotasDto resp = new RetornoCobraCuotasDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.cobra_cuotas(?,?,?,?)}");
                //   LocalDate localDate = LocalDate.of(fechainicio);
                // java.sql.Date sqlDateini = java.sql.Date.valueOf(fechainicio);
                // java.sql.Date sqlDatefin = java.sql.Date.valueOf(fechafin);
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, contrato);
                stmt.setInt(2, suscripcion);
                stmt.setInt(3, cuotas);
                stmt.setInt(4, mumero);

                 stmt.execute();
                String resultado = stmt.getString(1);

                RetornoCobraCuotasDto sol = new RetornoCobraCuotasDto();
                sol.setResultado(resultado);

                resp.setResultado((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public RetornoCobraCuotasDto CobraCuotaIndv(String contrato, Integer suscripcion, Integer cuotas, Integer mumero

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RetornoCobraCuotasDto resp = new RetornoCobraCuotasDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.cobra_cuotas_indiv(?,?,?,?)}");
                //   LocalDate localDate = LocalDate.of(fechainicio);
                // java.sql.Date sqlDateini = java.sql.Date.valueOf(fechainicio);
                // java.sql.Date sqlDatefin = java.sql.Date.valueOf(fechafin);
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, contrato);
                stmt.setInt(2, suscripcion);
                stmt.setInt(3, cuotas);
                stmt.setInt(4, mumero);

                stmt.execute();
                String resultado = stmt.getString(1);

                RetornoCobraCuotasDto sol = new RetornoCobraCuotasDto();
                sol.setResultado(resultado);

                resp.setResultado((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }




    public List<ConsultaDatosPagosDto> ConsultaDatosPagos( Integer cedula

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ConsultaDatosPagosDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.consulta_datos_pagos(?)}");


                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setInt(1, cedula);




                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ConsultaDatosPagosDto sol = new ConsultaDatosPagosDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setContrato(rs.getString("NUMERO_CONTRATO"));
                    sol.setNombre(rs.getString("contratante"));
                    sol.setSuscripcion(rs.getInt("codigo_suscrpcion"));
                    sol.setFechadesde(rs.getString("fecha_desde"));
                    sol.setFechahasta(rs.getString("fecha_hasta"));



                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    public List<ConsultaDatosPagosDto> ConsultaContratosGen( Integer cedula

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ConsultaDatosPagosDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.consulta_contratos_gen(?)}");


                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setInt(1, cedula);




                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ConsultaDatosPagosDto sol = new ConsultaDatosPagosDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setContrato(rs.getString("NUMERO_CONTRATO"));
                                       sol.setFechadesde(rs.getString("fecha_desde"));
                    sol.setFechahasta(rs.getString("fecha_hasta"));
                    sol.setEstatus(rs.getString("status"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    public List<ConsultaRepContratoDto> ReporteContrato(String contrato

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ConsultaRepContratoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.reporte_contrato(?)}");


                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setString(1, contrato);




                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ConsultaRepContratoDto sol = new ConsultaRepContratoDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setContrato(rs.getString("NUMERO_CONTRATO"));
                    sol.setContratante(rs.getString("NOMBRE_CONTRATANTE"));
                    sol.setRepresentante(rs.getString("RESPONSABLE"));
                    sol.setCedula(rs.getInt("RIF"));
                    sol.setBeneficiario(rs.getString("BENEFICIARIO"));
                    sol.setTipo_documento(rs.getString("tipo_documento"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }



    public List<ConsultaRepReciboDto> ReporteRecibo(Integer certificado

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ConsultaRepReciboDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.reporte_cuadro_recibo(?)}");


                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setInt(1, certificado);




                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ConsultaRepReciboDto sol = new ConsultaRepReciboDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setContrato(rs.getString("contrato"));
                    sol.setFechedesde(rs.getString("fecha_desde"));
                    sol.setNombrecontratante(rs.getString("nombre_contratante"));
                    sol.setRif(rs.getInt("rif"));
                    sol.setDireccion(rs.getString("direccion"));
                    sol.setPlan(rs.getString("plan"));
                    sol.setCuotamensual(rs.getDouble("costo_mensual_titular"));
                    sol.setCuotamensualfami(rs.getDouble("costo_mensual_familiares"));
                    sol.setMontoanual(rs.getDouble("monto_anual"));
                    sol.setTelf_factura(rs.getString("telf_factura"));
                    sol.setP_total_afilial(rs.getInt("cantidad_afiliados"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public List<SalidaDisminucionDto> ProcesoDismiucion(Integer certificado

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<SalidaDisminucionDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.proceso_dismunicion_poliza(?)}");


                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setInt(1, certificado);




                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    SalidaDisminucionDto sol = new SalidaDisminucionDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setContrato(rs.getString("contrato"));
                    sol.setContratante(rs.getString("nombre_contratante"));
                    sol.setFechadesde(rs.getString("fecha_desde"));
                    sol.setFechahasta(rs.getString("fecha_hasta"));
                    sol.setCedula(rs.getInt("cedula"));
                    sol.setCodigosuscricion(rs.getInt("codigo"));
                    sol.setSumaasegurada(rs.getDouble("suma_asegurada"));
                    sol.setSumaasegurada(rs.getDouble("suma_asegurada"));
                    sol.setGastosclinicas(rs.getDouble("gastos_clinicas"));
                    sol.setSaldoactual(rs.getDouble("saldo_actual"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public SalidaReferenciaDto ReferenciaCuota(String fechapago,
                                                 Integer formapago,
                                                 String referencia,
                                                 Double Monto,
                                                 String moneda,
                                                 String correo,
                                                 String contrato,
                                                 Integer suscripcion,
                                                 Integer banco

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        SalidaReferenciaDto resp = new SalidaReferenciaDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.crea_referencia(?,?,?,?,?,?,?,?,?)}");
                //   LocalDate localDate = LocalDate.of(fechainicio);
                // java.sql.Date sqlDateini = java.sql.Date.valueOf(fechainicio);
                // java.sql.Date sqlDatefin = java.sql.Date.valueOf(fechafin);
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, fechapago);
                stmt.setInt(2, formapago);
                stmt.setString(3, referencia);
                stmt.setDouble(4, Monto);
                stmt.setString(5, moneda);
                stmt.setString(6, correo);
                stmt.setString(7, contrato);
                stmt.setInt(8, suscripcion);
                stmt.setInt(9, banco);





                stmt.execute();
                String resultado = stmt.getString(1);

                SalidaReferenciaDto sol = new SalidaReferenciaDto();
                sol.setReferencia(resultado);

                resp.setReferencia((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<SalidaCobranzaDto> ConsultaGestionCobra() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<SalidaCobranzaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_gestion_cobranza()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type




                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    SalidaCobranzaDto sol = new SalidaCobranzaDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setContrato(rs.getString("numero_contrato"));
                    sol.setContratante(rs.getString("contratante"));
                    sol.setCodigosuscricion(rs.getInt("codigo_suscripcion"));
                    sol.setTelefono(rs.getString("telefono"));
                    sol.setCuota(rs.getInt("cuota"));
                    sol.setFechapago(rs.getString("fechapago"));
                    sol.setMontocuota(rs.getDouble("monto_cuota"));
                    sol.setFechapromesa(rs.getString("fecha_promesa_pago"));
                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public List<ConsultaReclamoDto> ConsultaReclamo(Integer reclamo

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ConsultaReclamoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.consulta_reclamo(?)}");


                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setInt(1, reclamo);




                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ConsultaReclamoDto sol = new ConsultaReclamoDto();
                    ObjectMapper mapper = new ObjectMapper();
                    sol.setId_reclamo(rs.getInt("id_reclamo"));
                    sol.setCodigo_suscripcion(rs.getInt("codigo_suscripcion"));
                    sol.setCodigo_intervencion(rs.getString("codigo_intervencion"));
                    sol.setCodigo_tipo_servicio(rs.getString("codigo_tipo_servicio"));
                    sol.setCodigo_medico(rs.getString("codigo_medico"));
                    sol.setCodigo_clinica(rs.getString("codigo_clinica"));
                    sol.setFecha_ingreso(rs.getString("fecha_ingreso"));
                    sol.setNumero_servicio(rs.getInt("numero_servicio"));
                    sol.setTipo_poliza(rs.getString("tipo_poliza"));
                    sol.setHecho_por(rs.getString("hecho_por"));
                    sol.setAprobado_por(rs.getString("aprobado_por"));
                    sol.setFecha_recepcion(rs.getString("fecha_recepcion"));
                    sol.setFecha_ocurrencia(rs.getString("fecha_ocurrencia"));
                    sol.setStatus(rs.getString("status"));
                    sol.setDepartamento(rs.getString("departamento"));
                    sol.setTipo_cheque(rs.getString("tipo_cheque"));
                    sol.setFecha_egreso(rs.getString("fecha_egreso"));
                    sol.setFecha_pago(rs.getString("fecha_pago"));
                    sol.setClase_pago(rs.getInt("clase_pago"));
                    sol.setCodigo_medico_opinion(rs.getString("codigo_medico_opinion"));
                    sol.setNumero_aval_clave(rs.getString("numero_aval_clave"));
                    sol.setDeducible(rs.getDouble("deducible"));
                    sol.setGastos_clinica(rs.getDouble("gastos_clinica"));
                    sol.setGastos_no_amparados_neto(rs.getDouble("gastos_no_amparados_neto"));
                    sol.setHonorarios_medicos(rs.getDouble("honorarios_medicos"));
                    sol.setGastos_ambulatorios(rs.getDouble("gastos_ambulatorios"));
                    sol.setDescuento_clinicas(rs.getDouble("descuento_clinicas"));
                    sol.setDescuento_medico(rs.getDouble("descuento_medico"));
                    sol.setDescuento_neto(rs.getDouble("descuento_neto"));
                    sol.setPorcentaje_descuento(rs.getDouble("porcentaje_descuento"));
                    sol.setPorcentaje_reembolso(rs.getDouble("porcentaje_reembolso"));
                    sol.setMonto_facturado(rs.getDouble("monto_facturado"));
                    sol.setMonto_pagado(rs.getDouble("monto_pagado"));
                    sol.setNeto_a_pagar(rs.getDouble("neto_a_pagar"));
                    sol.setTotal_a_pagar(rs.getDouble("total_a_pagar"));
                    sol.setTotal_facturado(rs.getDouble("total_facturado"));
                    sol.setTotal_elegible(rs.getDouble("total_elegible"));
                    sol.setSub_total(rs.getDouble("sub_total"));
                    sol.setIslr_neto(rs.getDouble("islr_neto"));
                    sol.setAutorizado(rs.getString("autorizado"));
                    sol.setEnvio_carta(rs.getString("envio_carta"));
                    sol.setFecha_envio_administracion(rs.getString("fecha_envio_administracion"));
                    sol.setFecha_envio_factura(rs.getString("fecha_envio_factura"));
                    sol.setFecha_recepcion_factura(rs.getString("fecha_recepcion_factura"));
                    sol.setDescuento_clinica_compromiso(rs.getDouble("descuento_clinica_compromiso"));
                    sol.setDescuento_medico_compromiso(rs.getDouble("descuento_medico_compromiso"));
                    sol.setNumero_factura(rs.getString("numero_factura"));
                    sol.setMonto_facturado_letra(rs.getString("monto_facturado_letra"));
                    sol.setMonto_pagado_letra(rs.getString("monto_pagado_letra"));
                    sol.setDiagnostico(rs.getString("diagnostico"));
                    sol.setComentarios(rs.getString("comentarios"));
                    sol.setObservaciones_auditado(rs.getString("observaciones_auditado"));
                    sol.setSegunda_opinion_medica(rs.getString("segunda_opinion_medica"));
                    sol.setAhorro_opinion(rs.getDouble("ahorro_opinion"));
                    sol.setObservaciones_opinion(rs.getString("observaciones_opinion"));
                    sol.setFecha_registro(rs.getString("fecha_registro"));
                    sol.setAjuste_baremo(rs.getDouble("ajuste_baremo"));
                    sol.setMedico_auditor(rs.getString("medico_auditor"));
                    sol.setFactura(rs.getInt("factura"));
                    sol.setNumero(rs.getInt("numero"));
                    sol.setIva(rs.getDouble("iva"));
                    sol.setControl(rs.getInt("control"));
                    sol.setCa_rel(rs.getInt("ca_rel"));
                    sol.setTipo_reclamo(rs.getString("tipo_reclamo"));
                    sol.setIva_retenido(rs.getDouble("iva_retenido"));
                    sol.setPorcentaje_retencion(rs.getInt("porcentaje_retencion"));
                    sol.setComprobante(rs.getInt("comprobante"));
                    sol.setNumero_contrato(rs.getString("numero_contrato"));
                    sol.setHecho_por_fact(rs.getString("hecho_por_fact"));
                    sol.setModificado_por(rs.getString("modificado_por"));
                    sol.setFecha_modificacion(rs.getString("fecha_modificacion"));
                    sol.setFecha_modif(rs.getString("fecha_modif"));
                    sol.setMaquina(rs.getString("maquina"));
                    sol.setCelular(rs.getString("celular"));
                    sol.setCod_area(rs.getString("cod_area"));
                    sol.setFecha_valor(rs.getString("fecha_valor"));
                    sol.setFecha_recepcion_atc(rs.getString("fecha_recepcion_atc"));
                    sol.setReferencia_atc(rs.getInt("referencia_atc"));
                    sol.setCodigo_filial(rs.getString("codigo_filial"));
                    sol.setCentro_costo(rs.getString("centro_costo"));
                    sol.setNu_servicio_unico(rs.getInt("nu_servicio_unico"));
                    sol.setFecha_emision_factura(rs.getString("fecha_emision_factura"));
                    sol.setRemesa(rs.getString("remesa"));
                    sol.setOperador(rs.getString("operador"));
                    sol.setFecha_operador(rs.getString("fecha_operador"));
                    sol.setCod_cobertura(rs.getString("cod_cobertura"));
                    sol.setFactura_global(rs.getString("factura_global"));
                    sol.setNumero_finiquito(rs.getInt("numero_finiquito"));



                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public List<CertifcadoReclamoDto> ConsultaInicialReclamo(Integer certificado

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<CertifcadoReclamoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.consulta_inicial_reclamo(?)}");


                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setInt(1, certificado);




                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    CertifcadoReclamoDto sol = new CertifcadoReclamoDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setIdreclamo(rs.getInt("id_reclamo"));
                    sol.setCedula(rs.getInt("cedula"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setCodigosuscripcion(rs.getInt("codigo"));
                    sol.setNumero_contrato(rs.getString("numero_contrato"));
                    sol.setEstatus(rs.getString("estatus"));
                    sol.setFecha_ingreso(rs.getString("fecha_ingreso"));
                    sol.setParentesco(rs.getString("parentesco"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    public RecpuestaSuccesDto ActualizaServicio(Integer idreclamo,
                                                Integer servicio,
                                                Integer intervencion,
                                                Double Monto

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RecpuestaSuccesDto resp = new RecpuestaSuccesDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.actualiza_servicio_interv(?,?,?,?)}");
                //   LocalDate localDate = LocalDate.of(fechainicio);
                // java.sql.Date sqlDateini = java.sql.Date.valueOf(fechainicio);
                // java.sql.Date sqlDatefin = java.sql.Date.valueOf(fechafin);
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setInt(1, idreclamo);
                stmt.setInt(2, servicio);
                stmt.setInt(3, intervencion);
                stmt.setDouble(4, Monto);






                stmt.execute();
                String resultado = stmt.getString(1);

                RecpuestaSuccesDto sol = new RecpuestaSuccesDto();
                sol.setRetorna(resultado);

                resp.setRetorna((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public RecpuestaSuccesDto DeleteServicio(Integer idreclamo,
                                                Integer servicio,
                                                Integer intervencion,
                                                Double Monto

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RecpuestaSuccesDto resp = new RecpuestaSuccesDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.elimina_servicio_interv(?,?,?,?)}");
                //   LocalDate localDate = LocalDate.of(fechainicio);
                // java.sql.Date sqlDateini = java.sql.Date.valueOf(fechainicio);
                // java.sql.Date sqlDatefin = java.sql.Date.valueOf(fechafin);
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setInt(1, idreclamo);
                stmt.setInt(2, servicio);
                stmt.setInt(3, intervencion);
                stmt.setDouble(4, Monto);






                stmt.execute();
                String resultado = stmt.getString(1);

                RecpuestaSuccesDto sol = new RecpuestaSuccesDto();
                sol.setRetorna(resultado);

                resp.setRetorna((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public RespReclamoDto ActualizoReclamo(
                                       Integer    P_codigo_suscripcion  ,
                                       String    P_codigo_intervencion  ,
                                       String    P_codigo_tipo_servicio  ,
                                       String    P_codigo_medico ,
                                       String    P_codigo_clinica ,
                                       String    P_fecha_ingreso  ,
                                       Integer    P_numero_servicio  ,
                                       String    P_tipo_poliza   ,
                                       String    P_hecho_por  ,
                                       String    P_aprobado_por  ,
                                       String    P_fecha_recepcion  ,
                                       String    P_fecha_ocurrencia  ,
                                       String    P_status  ,
                                       String    P_departamento ,
                                       String    P_tipo_cheque    ,
                                       String    P_fecha_egreso ,
                                       String    P_fecha_pago ,
                                       Integer    P_clase_pago ,
                                       String    P_codigo_medico_opinion ,
                                       String    P_numero_aval_clave,
                                       Double   P_deducible ,
                                       Double   P_gastos_clinica ,
                                       Double   P_gastos_no_amparados_neto ,
                                       Double   P_honorarios_medicos ,
                                       Double   P_gastos_ambulatorios ,
                                       Double   P_descuento_clinicas ,
                                       Double   P_descuento_medico ,
                                       Double   P_descuento_neto ,
                                       Double   P_porcentaje_descuento ,
                                       Double   P_porcentaje_reembolso ,
                                       Double   P_monto_facturado ,
                                       Double   P_monto_pagado ,
                                       Double   P_neto_a_pagar ,
                                       Double   P_total_a_pagar ,
                                       Double   P_total_facturado ,
                                       Double   P_total_elegible ,
                                       Double   P_sub_total ,
                                       Double   P_islr_neto ,
                                       String    P_autorizado ,
                                       String    P_envio_carta,
                                       String    P_fecha_envio_administracion ,
                                       String    P_fecha_envio_factura ,
                                       String    P_fecha_recepcion_factura ,
                                       Double    P_descuento_clinica_compromiso ,
                                       Double    P_descuento_medico_compromiso ,
                                       String     P_numero_factura ,
                                       String     P_monto_facturado_letra  ,
                                       String     P_monto_pagado_letra  ,
                                       String     P_diagnostico  ,
                                       String     P_comentarios  ,
                                       String     P_observaciones_auditado  ,
                                       String     P_segunda_opinion_medica  ,
                                       Double    P_ahorro_opinion ,
                                       String     P_observaciones_opinion  ,
                                       String     P_fecha_registro  ,
                                       Double    P_ajuste_baremo ,
                                       String     P_medico_auditor  ,
                                       Integer     P_factura ,
                                       Integer     P_numero ,
                                       Double    P_iva,
                                       Integer     P_control ,
                                       Integer     P_ca_rel,
                                       String     P_tipo_reclamo,
                                       Double       P_iva_retenido,
                                       Integer     P_porcentaje_retencion,
                                       Integer     P_comprobante,
                                       String     P_numero_contrato  ,
                                       String     P_hecho_por_fact  ,
                                       String     P_modificado_por  ,
                                       String     P_fecha_modificacion,
                                       String     P_fecha_modif ,
                                       String     P_maquina  ,
                                       String     P_celular  ,
                                       String     P_cod_area,
                                       String     P_fecha_valor ,
                                       String     P_fecha_recepcion_atc ,
                                       Integer     P_referencia_atc ,
                                       String     P_codigo_filial  ,
                                       String     P_centro_costo  ,
                                       Integer     P_nu_servicio_unico ,
                                       String     P_fecha_emision_factura ,
                                       String     P_remesa  ,
                                       String     P_operador  ,
                                       String     P_fecha_operador ,
                                       String     P_cod_cobertura  ,
                                       String     P_factura_global,
                                       Integer   p_numero_finiquito,
                                       Integer    P_id_reclamo
    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RespReclamoDto resp =  new RespReclamoDto();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.actualiza_reclamo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
//                CallableStatement stmt = conn.prepareCall("{? = call qualitas.nuevo_reclamo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                stmt.registerOutParameter(1, Types.INTEGER); // Set the output parameter type
                stmt.setInt(1, P_codigo_suscripcion  );
                stmt.setString(2, P_codigo_intervencion  );
                stmt.setString(3, P_codigo_tipo_servicio  );
                stmt.setString(4, P_codigo_medico );
                stmt.setString(5, P_codigo_clinica );
                stmt.setString(6, P_fecha_ingreso  );
                stmt.setInt(7, P_numero_servicio  );
                stmt.setString(8, P_tipo_poliza   );
                stmt.setString(9, P_hecho_por  );
                stmt.setString(10, P_aprobado_por  );
                stmt.setString(11, P_fecha_recepcion  );
                stmt.setString(12, P_fecha_ocurrencia  );
                stmt.setString(13, P_status  );
                stmt.setString(14, P_departamento );
                stmt.setString(15, P_tipo_cheque    );
                stmt.setString(16, P_fecha_egreso );
                stmt.setString(17, P_fecha_pago );
                stmt.setInt(18, P_clase_pago );
                stmt.setString(19, P_codigo_medico_opinion );
                stmt.setString(20, P_numero_aval_clave  );
                stmt.setDouble(21, P_deducible );
                stmt.setDouble(22, P_gastos_clinica );
                stmt.setDouble(23, P_gastos_no_amparados_neto );
                stmt.setDouble(24, P_honorarios_medicos );
                stmt.setDouble(25, P_gastos_ambulatorios );
                stmt.setDouble(26, P_descuento_clinicas );
                stmt.setDouble(27, P_descuento_medico );
                stmt.setDouble(28, P_descuento_neto );
                stmt.setDouble(29, P_porcentaje_descuento );
                stmt.setDouble(30, P_porcentaje_reembolso );
                stmt.setDouble(31, P_monto_facturado );
                stmt.setDouble(32, P_monto_pagado );
                stmt.setDouble(33, P_neto_a_pagar );
                stmt.setDouble(34, P_total_a_pagar );
                stmt.setDouble(35, P_total_facturado );
                stmt.setDouble(36, P_total_elegible );
                stmt.setDouble(37, P_sub_total );
                stmt.setDouble(38, P_islr_neto );
                stmt.setString(39, P_autorizado );
                stmt.setString(40, P_envio_carta );
                stmt.setString(41, P_fecha_envio_administracion );
                stmt.setString(42, P_fecha_envio_factura );
                stmt.setString(43, P_fecha_recepcion_factura );
                stmt.setDouble(44, P_descuento_clinica_compromiso );
                stmt.setDouble(45, P_descuento_medico_compromiso );
                stmt.setString(46, P_numero_factura );
                stmt.setString(47, P_monto_facturado_letra  );
                stmt.setString(48, P_monto_pagado_letra  );
                stmt.setString(49, P_diagnostico  );
                stmt.setString(50, P_comentarios  );
                stmt.setString(51, P_observaciones_auditado  );
                stmt.setString(52, P_segunda_opinion_medica  );
                stmt.setDouble(53, P_ahorro_opinion );
                stmt.setString(54, P_observaciones_opinion  );
                stmt.setString(55, P_fecha_registro  );
                stmt.setDouble(56, P_ajuste_baremo );
                stmt.setString(57, P_medico_auditor  );
                stmt.setInt(58, P_factura );
                stmt.setInt(59, P_numero );
                stmt.setDouble(60, P_iva );
                stmt.setInt(61, P_control );
                stmt.setInt(62, P_ca_rel );
                stmt.setString(63, P_tipo_reclamo  );
                stmt.setDouble(64, P_iva_retenido );
                stmt.setInt(65, P_porcentaje_retencion );
                stmt.setInt(66, P_comprobante );
                stmt.setString(67, P_numero_contrato  );
                stmt.setString(68, P_hecho_por_fact  );
                stmt.setString(69, P_modificado_por  );
                stmt.setString(70, P_fecha_modificacion );
                stmt.setString(71, P_fecha_modif );
                stmt.setString(72, P_maquina  );
                stmt.setString(73, P_celular  );
                stmt.setString(74, P_cod_area  );
                stmt.setString(75, P_fecha_valor );
                stmt.setString(76, P_fecha_recepcion_atc );
                stmt.setInt(77, P_referencia_atc );
                stmt.setString(78, P_codigo_filial  );
                stmt.setString(79, P_centro_costo  );
                stmt.setInt(80, P_nu_servicio_unico );
                stmt.setString(81, P_fecha_emision_factura );
                stmt.setString(82, P_remesa  );
                stmt.setString(83, P_operador  );
                stmt.setString(84, P_fecha_operador );
                stmt.setString(85, P_cod_cobertura  );
                stmt.setString(86, P_factura_global );
                stmt.setInt(87, p_numero_finiquito );
                stmt.setInt(88, P_id_reclamo );



                stmt.execute();
                Integer resultado = stmt.getInt(1);

                RespReclamoDto sol = new RespReclamoDto();
                sol.setRespuestarecl(resultado);

                // resp.((resultado));
                resp.setRespuestarecl((resultado));
                conn.commit();


                // rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public List<ConsultaServicioIntDto> ConsultaServicioInterv(Integer reclamo) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ConsultaServicioIntDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_servicio_reclamo()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setInt(1, reclamo );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    ConsultaServicioIntDto sol = new ConsultaServicioIntDto();

                    sol.setCodigoTipoServicio(rs.getInt("codigo_tipo_servicio"));
                    sol.setDescripcionServ(rs.getString("descripcionserv"));
                    sol.setCodigointervencion(rs.getInt("codigo_examen"));
                    sol.setDescripcionInterv(rs.getString("descripcioninterv"));
                    sol.setMonto(rs.getDouble("monto"));
                    resp.add(sol);
               }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public NumeroContratoDto SecuenciaContrato() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        NumeroContratoDto resp = new NumeroContratoDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{call qualitas.genera_secuencia_contrato(?)}");
                stmt.registerOutParameter(1, Types.VARCHAR);


                stmt.execute();
                String resultado = stmt.getString(1);

                NumeroContratoDto sol = new NumeroContratoDto();
                sol.setContrato(resultado);

                resp.setContrato((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<SalidDatosContratosDto> ConsulatInicContrato(String certificado) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<SalidDatosContratosDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_ini_contrato()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setString(1, certificado );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    SalidDatosContratosDto sol = new SalidDatosContratosDto();

                    sol.setCedula(rs.getString("rif"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setNumero_contrato(rs.getString("numero_contrato"));
                    sol.setTipocontrato(rs.getString("tipocontrato"));
                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public List<SalidaBeneficiarioDto> ValidaBeneficiario(Integer cedula) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<SalidaBeneficiarioDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.valida_estatus_beneficiario()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setInt(1, cedula );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    SalidaBeneficiarioDto sol = new SalidaBeneficiarioDto();

                    sol.setCuotapendinte(rs.getInt("cuota"));
                    sol.setSumaasegurada(rs.getDouble("v_suma_asegurada"));
                    sol.setGastosclinica(rs.getDouble("v_gastos_clinicas"));
                    sol.setSaldo(rs.getDouble("v_saldo_actual"));
                    sol.setCuotapagada(rs.getInt("v_cuota_paga"));
                    sol.setDiaspendiente(rs.getInt("dias_transcurridos"));
                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    public List<NacionalidadDto> Nacionalidad() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<NacionalidadDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.nacionalidad()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
               // stmt.setInt(1, cedula );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    NacionalidadDto sol = new NacionalidadDto();

                    sol.setCodigo(rs.getString("cod_nacionalidad"));
                    sol.setDescripcion(rs.getString("descripcion"));

                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public List<ReferenciaFormaPDto> ReferenciaFormaPago() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ReferenciaFormaPDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.referencia_pago()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                // stmt.setInt(1, cedula );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    ReferenciaFormaPDto sol = new ReferenciaFormaPDto();

                    sol.setId(rs.getInt("id"));
                    sol.setDescripcion(rs.getString("descripcion"));

                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }



    public List<TipoMonedaDto> TipoMoneda() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<TipoMonedaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.tipo_moneda()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                // stmt.setInt(1, cedula );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    TipoMonedaDto sol = new TipoMonedaDto();

                    sol.setId(rs.getString("id"));
                    sol.setDescripcion(rs.getString("descripcion"));

                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    public List<ReferenciaFormaPDto> Banco() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ReferenciaFormaPDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.banco()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                // stmt.setInt(1, cedula );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    ReferenciaFormaPDto sol = new ReferenciaFormaPDto();

                    sol.setId(rs.getInt("id"));
                    sol.setDescripcion(rs.getString("descripcion"));

                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

public List<RetornaReferenciaDto> ReferenciaPagosCrono( String fecha1, String fecha2

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<RetornaReferenciaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.reporte_referencia_cobro(?,?)}");


                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setString(1, fecha1);
                stmt.setString(2, fecha2);




                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    RetornaReferenciaDto sol = new RetornaReferenciaDto();
                    ObjectMapper mapper = new ObjectMapper();


                    sol.setFechapago(rs.getString("fecha_pago"));
                    sol.setFormapago(rs.getString("forma_pago"));
                    sol.setReferenciapago(rs.getString("referencia_pago"));
                    sol.setMonto(rs.getDouble("monto"));
                    sol.setMoneda(rs.getString("cod_moneda"));
                    sol.setCorreo(rs.getString("correo"));
                    sol.setContrato(rs.getString("numero_contrato"));
                    sol.setSuscripcion(rs.getInt("codigo_suscripcion"));
                    sol.setIdreferencia(rs.getInt("id_referencia"));
                    sol.setBanco(rs.getString("banco"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    public RetornaGestionDto GestionPromesaPago(String fechapago,
                                                  String observaciones,
                                                  Integer suscripcion,
                                                  Integer cuota

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RetornaGestionDto resp = new RetornaGestionDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.crea_gestion_pago_cobro(?,?,?,?)}");
                //   LocalDate localDate = LocalDate.of(fechainicio);
                // java.sql.Date sqlDateini = java.sql.Date.valueOf(fechainicio);
                // java.sql.Date sqlDatefin = java.sql.Date.valueOf(fechafin);
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, fechapago);
                stmt.setString(2, observaciones);
                stmt.setInt(3, suscripcion);
                stmt.setInt(4, cuota);






                stmt.execute();
                String resultado = stmt.getString(1);

                RetornaGestionDto sol = new RetornaGestionDto();
                sol.setReferencia(resultado);

                resp.setReferencia((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<ReferenciaFormaPDto> ConsultaIntemediario() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ReferenciaFormaPDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_intermediario()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                // stmt.setInt(1, cedula );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    ReferenciaFormaPDto sol = new ReferenciaFormaPDto();

                    sol.setId(rs.getInt("id"));
                    sol.setDescripcion(rs.getString("descripcion"));

                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public List<RetornaDto> ConsultaPlanBeneficiario(String contrato

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        //RetornaDto resp = new RetornaDto();
        List<RetornaDto> resp =   new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.consulta_plan_benef(?)}");
                //LocalDate localDate = LocalDate.of(fechaMovimiento);
                //  java.sql.Date sqlDate = java.sql.Date.valueOf(fechaMovimiento);

                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setString(1, contrato);



                stmt.execute();
                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    RetornaDto sol = new RetornaDto();

                    sol.setRetorna(rs.getString("plan"));


                    resp.add(sol);
                }



                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<RecibeTasaBcvDto> ConsultaTasaBcv() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<RecibeTasaBcvDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_tasa_bcv()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                // stmt.setInt(1, cedula );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    RecibeTasaBcvDto sol = new RecibeTasaBcvDto();

                    sol.setCodmoneda(rs.getString("cod_moneda"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setFechaoperacion(rs.getString("fecha_operacion"));
                    sol.setMontoventa(rs.getDouble("monto_venta"));
                    sol.setMontocompra(rs.getDouble("monto_compra"));

                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public RecRespStringDto ActualizaBcv(String codigo, String monto, String fecha) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        //List<MsiNewDTO> resp =   new ArrayList<>();
        RecRespStringDto resp = new RecRespStringDto();

        MsiNewDTO sol1 = new MsiNewDTO();
        try {
            Class.forName("org.postgresql.Driver");
            // Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{call qualitas.actualiza_tasa_bcv(?,?,?)}");
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, codigo);
                stmt.setString(2, fecha);
                stmt.setString(3, monto);

                // Set the output parameter type

                stmt.execute();
                String resultado = stmt.getString(1);

                RecRespStringDto sol = new RecRespStringDto();
                sol.setRetorno(resultado);

                resp.setRetorno((resultado));

                conn.commit();

                stmt.close();
                conn.close();
                //ResultSet rs = (ResultSet) stmt.getInt(1); // Obtener el resultado como ResultSet
                //ResultSet rs = stmt.getResultSet();

                // Procesar los datos del ResultSet


                conn.commit();
                stmt.close();
                conn.close();
                System.out.println("Finaliza proceso de Insertar Apliacacion");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return resp;
    }

    public List<RecibeNombreBcoDto> ConsultaBancos() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<RecibeNombreBcoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_bancos()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                // stmt.setInt(1, cedula );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    RecibeNombreBcoDto sol = new RecibeNombreBcoDto();

                    sol.setIdbanco(rs.getInt("id_banco"));
                    sol.setBanco(rs.getString("banco"));
                    sol.setNumero_cuenta(rs.getString("numero_cuenta"));
                    sol.setSaldo_anterior(rs.getDouble("saldo_anterior"));
                    sol.setSaldo_actual(rs.getDouble("saldo_actual"));

                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    public RecpuestaSuccesDto CronogramaIndividual(String contrato, String fechapago

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RecpuestaSuccesDto resp = new RecpuestaSuccesDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.proceso_cronograma_modficado_ind(?,?)}");

                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, contrato);
                stmt.setString(2, fechapago);

                stmt.execute();
                String resultado = stmt.getString(1);

                RecpuestaSuccesDto sol = new RecpuestaSuccesDto();
                sol.setRetorna(resultado);

                resp.setRetorna((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public RecpuestaSuccesDto ValidacionCuota(String contrato, String fechapago

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RecpuestaSuccesDto resp = new RecpuestaSuccesDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.valida_fecha_cuota(?,?)}");

                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, contrato);
                stmt.setString(2, fechapago);

                stmt.execute();
                String resultado = stmt.getString(1);

                RecpuestaSuccesDto sol = new RecpuestaSuccesDto();
                sol.setRetorna(resultado);

                resp.setRetorna((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public List<RetornaBenefiDto> ConsultaBeneficiario(Integer cedula

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        //RetornaDto resp = new RetornaDto();
        List<RetornaBenefiDto> resp =   new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.consulta_beneficiario(?)}");
                //LocalDate localDate = LocalDate.of(fechaMovimiento);
                //  java.sql.Date sqlDate = java.sql.Date.valueOf(fechaMovimiento);

                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setInt(1, cedula);



                stmt.execute();
                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    RetornaBenefiDto sol = new RetornaBenefiDto();

                    sol.setNumerocontrato(rs.getString("v_numero_contrato"));
                    sol.setNombre(rs.getString("v_nombre"));
                    sol.setFechadesde(rs.getString("v_fecha_desde"));
                    sol.setFechahasta(rs.getString("v_fecha_hasta"));
                    sol.setStatussuscripcion(rs.getString("v_status"));
                    sol.setSexo(rs.getString("v_sexo"));
                    sol.setEstadocivil(rs.getString("v_estado_civil"));
                    sol.setParentesco(rs.getString("v_parentesco"));
                    sol.setStatuscontrato(rs.getString("vs_status"));
                    sol.setFechanacimiento(rs.getString("v_fecha_nacimiento"));
                    sol.setCuotaspendiente(rs.getInt("cuota"));
                    sol.setSumaasegurada(rs.getDouble("v_suma_asegurada"));
                    sol.setGastosclinicas(rs.getDouble("v_gastos_clinicas"));
                    sol.setSaldoactual(rs.getDouble("v_saldo_actual"));
                    sol.setPlan(rs.getString("plan"));


                    resp.add(sol);
                }



                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public List<DisminucionRespDto> ConsultaDetallePoliza(Integer cedula

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        //RetornaDto resp = new RetornaDto();
        List<DisminucionRespDto> resp =   new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.detalle_disminucion(?)}");
                //LocalDate localDate = LocalDate.of(fechaMovimiento);
                //  java.sql.Date sqlDate = java.sql.Date.valueOf(fechaMovimiento);

                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setInt(1, cedula);



                stmt.execute();
                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    DisminucionRespDto sol = new DisminucionRespDto();

                    sol.setNumerocontrato(rs.getString("numero_contrato"));
                    sol.setIdreclamo(rs.getInt("id_reclamo"));
                    sol.setDescripcionserv(rs.getString("descripcionserv"));
                    sol.setDescripcioninterv(rs.getString("descripcioninterv"));
                    sol.setMonto(rs.getDouble("monto"));
                    sol.setPlan(rs.getString("plan"));
                    sol.setStatus(rs.getString("status"));

                    resp.add(sol);
                }



                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public RecpuestaSuccesDto CronogramaIndividualHist(String contrato, String fechapago, Double monto, Double montobs

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RecpuestaSuccesDto resp = new RecpuestaSuccesDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.proceso_cronograma_modficado_hist(?,?,?,?)}");

                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, contrato);
                stmt.setString(2, fechapago);
                stmt.setDouble(3, monto);
                stmt.setDouble(4, montobs);

                stmt.execute();
                String resultado = stmt.getString(1);

                RecpuestaSuccesDto sol = new RecpuestaSuccesDto();
                sol.setRetorna(resultado);

                resp.setRetorna((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<SalidDatosContratosDto> ConsulatContratoCedula(Integer certificado) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<SalidDatosContratosDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_contrato_por_cedula()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setInt(1, certificado );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    SalidDatosContratosDto sol = new SalidDatosContratosDto();

                    sol.setCedula(rs.getString("rif"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setNumero_contrato(rs.getString("numero_contrato"));
                    sol.setStatus(rs.getString("status"));
                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    public List<TipoContratoDto> ConsultaTipoPlanCon(String contrato) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<TipoContratoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_tipo_plan_contrato()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setString(1, contrato );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    TipoContratoDto sol = new TipoContratoDto();


                    sol.setTipoplan(rs.getString("tipo"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public List<EstadisticasDto> Estadisticas() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<EstadisticasDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_servicios_estadistico()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();
                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    EstadisticasDto sol = new EstadisticasDto();
                    ObjectMapper mapper = new ObjectMapper();

                    sol.setCantidad(rs.getInt("totales"));
                    sol.setDescripcion(rs.getString("descripcion"));
                    sol.setTipo(rs.getString("TIPO"));

                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public List<RepContratoDto> ConsultaRepContratos(String tipo, String fechadesde, String fechahasta) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<RepContratoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_rep_contratos(?,?)}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                if (tipo != null) {
                    stmt.setString(1, tipo);
                } else {
                    stmt.setNull(1, Types.VARCHAR);
                }
                stmt.setString(2, fechadesde );
                stmt.setString(3, fechahasta );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    RepContratoDto sol = new RepContratoDto();

                    sol.setNumerocontrato(rs.getString("numero_contrato"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setCedula(rs.getString("cedula"));
                    sol.setFechacreacion(rs.getString("fecha_creacion"));
                    sol.setFechadesde(rs.getString("fecha_desde"));
                    sol.setFechahasta(rs.getString("fecha_hasta"));
                    sol.setEstatus(rs.getString("estatus"));
                    sol.setTipo(rs.getString("tipo"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }


    public List<RepSuscripcionDto> ConsultaRepSuscripcion(String tipo, String fechadesde, String fechahasta) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<RepSuscripcionDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_rep_suscripcion(?,?)}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
              //  stmt.setString(1, tipo );
                if (tipo != null) {
                    stmt.setString(1, tipo);
                } else {
                    stmt.setNull(1, Types.VARCHAR);
                }



                stmt.setString(2, fechadesde );
                stmt.setString(3, fechahasta );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    RepSuscripcionDto sol = new RepSuscripcionDto();

                    sol.setNumerocontrato(rs.getString("numero_contrato"));
                    sol.setNombrecontratante(rs.getString("nombrecontratante"));
                    sol.setPlan(rs.getString("plan"));
                    sol.setCertificado(rs.getInt("certificado"));
                    sol.setCedula(rs.getString("cedula"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setFechanacimiento(rs.getString("fecha_nacimiento"));
                    sol.setSexo(rs.getString("sexo"));
                    sol.setEstadocivil(rs.getString("estado_civil"));
                    sol.setParentesco(rs.getString("parentesco"));
                    sol.setEstatus(rs.getString("status"));
                    sol.setFecharegistro(rs.getString("fecha_registro"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

    public List<RepReclamoDto> ConsultaRepReclamo(String fechadesde, String fechahasta) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<RepReclamoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_rep_reclamo(?)}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setString(1, fechadesde );
                stmt.setString(2, fechahasta );



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    RepReclamoDto sol = new RepReclamoDto();

                    sol.setNumerocontrato(rs.getString("numero_contrato"));
                    sol.setCedula(rs.getString("cedula"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setIdreclamo(rs.getInt("id_reclamo"));
                    sol.setCertificado(rs.getInt("certificado"));
                    sol.setFechaingreso(rs.getString("fecha_ingreso"));
                    sol.setFecharecepcion(rs.getString("fecha_recepcion"));
                    sol.setFechaocurrencia(rs.getString("fecha_ocurrencia"));
                    sol.setStatus(rs.getString("status"));
                    sol.setFechaegreso(rs.getString("fecha_egreso"));
                    sol.setMontofacturado(rs.getDouble("monto_facturado"));
                    sol.setCantidadservicios(rs.getInt("cantidad_servicios"));
                    sol.setCantidadinter(rs.getInt("cantidad_inter"));

                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;

    }

 public List<SalidaRepDto> ListaReporte() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<SalidaRepDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_aplicacion()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    SalidaRepDto sol = new SalidaRepDto();

                    sol.setCodigo(rs.getString("codigo"));
                    sol.setNombre(rs.getString("nombre"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public RetornoCobraCuotasDto CargaMasiva(Integer certificado,
                                            String  nombre,
                                            String fecha_ingreso,
                                            String fecha_nacimiento,
                                            String sexo,
                                            String  estado_civil,
                                            String parentesco,
                                            String status,
                                            String fecha_registro,
                                            String plazos_espera,
                                            String poliza_exceso,
                                            String nacionalidad,
                                            Integer cedula,
                                            String email,
                                            String cod_area,
                                            String celular,
                                            String observaciones_renovacion

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RetornoCobraCuotasDto resp = new RetornoCobraCuotasDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.inserta_carga_masiva(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setInt(1, certificado);
                stmt.setString(2, nombre);
                stmt.setString(3, fecha_ingreso);
                stmt.setString(4, fecha_nacimiento);

                stmt.setString(5, sexo);
                stmt.setString(6, estado_civil);
                stmt.setString(7, parentesco);
                stmt.setString(8, status);
                stmt.setString(9, fecha_registro);
                stmt.setString(10, plazos_espera);
                stmt.setString(11, poliza_exceso);
                stmt.setString(12, nacionalidad);
                stmt.setInt(13, cedula);
                stmt.setString(14, email);
                stmt.setString(15, cod_area);
                stmt.setString(16, celular);
                stmt.setString(17, observaciones_renovacion);




                stmt.execute();
                String resultado = stmt.getString(1);

                RetornoCobraCuotasDto sol = new RetornoCobraCuotasDto();
                sol.setResultado(resultado);

                resp.setResultado((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public RetornoCobraCuotasDto ProcesoCargaMasiva(String contrato

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RetornoCobraCuotasDto resp = new RetornoCobraCuotasDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.proceso_envia_masivo_new(?)}");
                //   LocalDate localDate = LocalDate.of(fechainicio);
                // java.sql.Date sqlDateini = java.sql.Date.valueOf(fechainicio);
                // java.sql.Date sqlDatefin = java.sql.Date.valueOf(fechafin);
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, contrato);


                stmt.execute();
                String resultado = stmt.getString(1);

                RetornoCobraCuotasDto sol = new RetornoCobraCuotasDto();
                sol.setResultado(resultado);

                resp.setResultado((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<CargaMasivaDto> ConsultaMasiva() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<CargaMasivaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.consulta_carga_masiva()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    CargaMasivaDto sol = new CargaMasivaDto();

                    sol.setCertificado(rs.getInt("certificado"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setFecha_ingreso(rs.getString("fecha_ingreso"));
                    sol.setFecha_nacimiento(rs.getString("fecha_nacimiento"));
                    sol.setSexo(rs.getString("sexo"));
                    sol.setEstado_civil(rs.getString("estado_civil"));
                    sol.setParentesco(rs.getString("parentesco"));
                    sol.setStatus(rs.getString("status"));
                    sol.setFecha_registro(rs.getString("fecha_registro"));
                    sol.setPlazos_espera(rs.getString("plazos_espera"));
                    sol.setPoliza_exceso(rs.getString("poliza_exceso"));
                    sol.setNacionalidad(rs.getString("nacionalidad"));
                    sol.setCedula(rs.getInt("cedula"));
                    sol.setEmail(rs.getString("email"));
                    sol.setCod_area(rs.getString("cod_area"));
                    sol.setCelular(rs.getString("celular"));
                    sol.setObservaciones_renovacion(rs.getString("plan"));




                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public List<ConvierteTasaDto> Proceso_tasa(Double monto, String codigo, Double tasa ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ConvierteTasaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call qualitas.proceso_tasa(?,?)}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setDouble(1, monto);
                stmt.setString(2, codigo);
                stmt.setDouble(3, tasa);

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ConvierteTasaDto sol = new ConvierteTasaDto();

                    sol.setMonto(rs.getDouble("monto"));
                    sol.setMontobs(rs.getDouble("v_monto_bs"));
                    sol.setMontodolar(rs.getDouble("v_monto_dolar"));
                    sol.setMontoEuro(rs.getDouble("v_monto_euro"));



                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public RetornoCobraCuotasDto Proceso_factura_ind(Integer suscripcion,
                                                     String contrato,
                                                     Double montobs,
                                                     Double montodolar,
                                                     Double montoeuro,
                                                     Integer cantidad


    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RetornoCobraCuotasDto resp = new RetornoCobraCuotasDto();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call qualitas.cobra_cuotas_general(?,?,?,?,?,?)}");
                //   LocalDate localDate = LocalDate.of(fechainicio);
                // java.sql.Date sqlDateini = java.sql.Date.valueOf(fechainicio);
                // java.sql.Date sqlDatefin = java.sql.Date.valueOf(fechafin);
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setInt(1, suscripcion);
                stmt.setString(2, contrato);
                stmt.setDouble(3, montobs);
                stmt.setDouble(4, montodolar);
                stmt.setDouble(5, montoeuro);
                stmt.setInt(6, cantidad);

                stmt.execute();
                String resultado = stmt.getString(1);

                RetornoCobraCuotasDto sol = new RetornoCobraCuotasDto();
                sol.setResultado(resultado);

                resp.setResultado((resultado));

                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }
// --- Métodos para Especialidades ---

    public Integer crearEspecialidad(EspecialidadDto especialidad) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Integer nuevaEspecialidadId = null;
        CallableStatement stmt = null;

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);

            stmt = conn.prepareCall("{? = call citas_medicas.crear_especialidad(?, ?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, especialidad.getNombre());
            stmt.setString(3, especialidad.getDescripcion());

            stmt.execute();
            nuevaEspecialidadId = stmt.getInt(1);
            if (stmt.wasNull()) { // Check if the returned ID was NULL (e.g., on unique violation)
                nuevaEspecialidadId = null;
            }

            conn.commit();
            System.out.println("crearEspecialidad: Executed successfully. ID: " + nuevaEspecialidadId);
        } catch (SQLException se) {
            System.err.println("Error SQL en crearEspecialidad: " + se.getMessage());
            se.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    System.err.println("Error haciendo rollback en crearEspecialidad: " + e.getMessage());
                }
            }
            // Consider throwing a custom exception or returning a specific error indicator
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en crearEspecialidad. " + cnfe.getMessage());
            cnfe.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado en crearEspecialidad: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error haciendo rollback en crearEspecialidad: " + ex.getMessage());
                }
            }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en crearEspecialidad: " + e.getMessage());
            }
        }
        return nuevaEspecialidadId;
    }

    public List<EspecialidadDto> obtenerEspecialidades() {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<EspecialidadDto> especialidades = new ArrayList<>();
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            // No setAutoCommit(false) needed for read-only operations if SP doesn't require it

            stmt = conn.prepareCall("{? = call citas_medicas.obtener_especialidades()}");
            stmt.registerOutParameter(1, Types.OTHER); // For cursor
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                EspecialidadDto especialidad = new EspecialidadDto();
                especialidad.setEspecialidadId(rs.getInt("especialidad_id"));
                especialidad.setNombre(rs.getString("nombre"));
                especialidad.setDescripcion(rs.getString("descripcion"));
                especialidades.add(especialidad);
            }
            System.out.println("obtenerEspecialidades: Fetched " + especialidades.size() + " records.");
        } catch (SQLException se) {
            System.err.println("Error SQL en obtenerEspecialidades: " + se.getMessage());
            se.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en obtenerEspecialidades. " + cnfe.getMessage());
            cnfe.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado en obtenerEspecialidades: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en obtenerEspecialidades: " + e.getMessage());
            }
        }
        return especialidades;
    }

    public EspecialidadDto obtenerEspecialidadPorId(Integer especialidadId) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        EspecialidadDto especialidad = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        if (especialidadId == null) return null;

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());

            stmt = conn.prepareCall("{? = call citas_medicas.obtener_especialidad_por_id(?)}");
            stmt.registerOutParameter(1, Types.OTHER);
            stmt.setInt(2, especialidadId);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            if (rs.next()) {
                especialidad = new EspecialidadDto();
                especialidad.setEspecialidadId(rs.getInt("especialidad_id"));
                especialidad.setNombre(rs.getString("nombre"));
                especialidad.setDescripcion(rs.getString("descripcion"));
                System.out.println("obtenerEspecialidadPorId: Found especialidad with ID " + especialidadId);
            } else {
                System.out.println("obtenerEspecialidadPorId: No especialidad found with ID " + especialidadId);
            }
        } catch (SQLException se) {
            System.err.println("Error SQL en obtenerEspecialidadPorId: " + se.getMessage());
            se.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en obtenerEspecialidadPorId. " + cnfe.getMessage());
            cnfe.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado en obtenerEspecialidadPorId: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en obtenerEspecialidadPorId: " + e.getMessage());
            }
        }
        return especialidad;
    }



    public EspecialidadDto   obtenerEspecialidadPorId_new(Integer especialidadId) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        PacienteDto paciente = null;
        EspecialidadDto especialidad = null;
        ResultSet rs = null;
        List<ProductoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call citas_medicas.obtener_especialidad_por_id(?)}");
                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setInt(2, especialidadId);
                stmt.execute();

                //ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet
                rs = (ResultSet) stmt.getObject(1);

                if (rs.next()) {
                    especialidad = new EspecialidadDto();
                    especialidad.setEspecialidadId(rs.getInt("especialidad_id"));
                    especialidad.setNombre(rs.getString("nombre"));
                    especialidad.setDescripcion(rs.getString("descripcion"));
                    System.out.println("obtenerEspecialidadPorId: Found especialidad with ID " + especialidadId);
                } else {
                    System.out.println("obtenerEspecialidadPorId: No especialidad found with ID " + especialidadId);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return especialidad;

    }

    public String actualizarEspecialidad(EspecialidadDto especialidad) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        String resultado = "ERROR: Unknown";
        CallableStatement stmt = null;

        if (especialidad == null || especialidad.getEspecialidadId() == null) {
            return "ERROR: Especialidad o ID nulo.";
        }

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);

            stmt = conn.prepareCall("{? = call citas_medicas.actualizar_especialidad(?, ?, ?)}");
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setInt(2, especialidad.getEspecialidadId());
            stmt.setString(3, especialidad.getNombre());
            stmt.setString(4, especialidad.getDescripcion());

            stmt.execute();
            resultado = stmt.getString(1);
            conn.commit();
            System.out.println("actualizarEspecialidad: Result for ID " + especialidad.getEspecialidadId() + ": " + resultado);
        } catch (SQLException se) {
            System.err.println("Error SQL en actualizarEspecialidad: " + se.getMessage());
            se.printStackTrace();
            resultado = "ERROR: " + se.getMessage();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    System.err.println("Error haciendo rollback en actualizarEspecialidad: " + e.getMessage());
                }
            }
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en actualizarEspecialidad. " + cnfe.getMessage());
            cnfe.printStackTrace();
            resultado = "ERROR: Driver no encontrado.";
        } catch (Exception e) {
            System.err.println("Error inesperado en actualizarEspecialidad: " + e.getMessage());
            e.printStackTrace();
            resultado = "ERROR: " + e.getMessage();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error haciendo rollback en actualizarEspecialidad: " + ex.getMessage());
                }
            }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en actualizarEspecialidad: " + e.getMessage());
            }
        }
        return resultado;
    }

    public String eliminarEspecialidad(Integer especialidadId) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        String resultado = "ERROR: Unknown";
        CallableStatement stmt = null;

        if (especialidadId == null) {
            return "ERROR: ID de especialidad nulo.";
        }

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);

            stmt = conn.prepareCall("{? = call citas_medicas.eliminar_especialidad(?)}");
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setInt(2, especialidadId);

            stmt.execute();
            resultado = stmt.getString(1);
            conn.commit();
            System.out.println("eliminarEspecialidad: Result for ID " + especialidadId + ": " + resultado);
        } catch (SQLException se) {
            System.err.println("Error SQL en eliminarEspecialidad: " + se.getMessage());
            se.printStackTrace();
            resultado = "ERROR: " + se.getMessage(); // Could be FK violation if medicos reference it
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    System.err.println("Error haciendo rollback en eliminarEspecialidad: " + e.getMessage());
                }
            }
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en eliminarEspecialidad. " + cnfe.getMessage());
            cnfe.printStackTrace();
            resultado = "ERROR: Driver no encontrado.";
        } catch (Exception e) {
            System.err.println("Error inesperado en eliminarEspecialidad: " + e.getMessage());
            e.printStackTrace();
            resultado = "ERROR: " + e.getMessage();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error haciendo rollback en eliminarEspecialidad: " + ex.getMessage());
                }
            }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en eliminarEspecialidad: " + e.getMessage());
            }
        }
        return resultado;
    }

    // --- Métodos para Pacientes ---

    public Integer crearPaciente(PacienteDto paciente) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Integer nuevoPacienteId = null;
        CallableStatement stmt = null;

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);

            stmt = conn.prepareCall("{? = call citas_medicas.crear_paciente(?, ?, ?, ?, ?, ?, ?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, paciente.getCedula());
            stmt.setString(3, paciente.getNombre());
            stmt.setString(4, paciente.getApellido());
            if (paciente.getFechaNacimiento() != null) {
                stmt.setDate(5, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            } else {
                stmt.setNull(5, Types.DATE);
            }
            stmt.setString(6, paciente.getTelefono());
            stmt.setString(7, paciente.getCorreoElectronico());
            stmt.setString(8, paciente.getDireccion());

            stmt.execute();
            nuevoPacienteId = stmt.getInt(1);
            if (stmt.wasNull()) {
                nuevoPacienteId = null;
            }
            conn.commit();
            System.out.println("crearPaciente: Executed successfully. ID: " + nuevoPacienteId);
        } catch (SQLException se) {
            System.err.println("Error SQL en crearPaciente: " + se.getMessage());
            se.printStackTrace();
            if (conn != null) try { conn.rollback(); } catch (SQLException e) { e.printStackTrace(); }
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en crearPaciente. " + cnfe.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado en crearPaciente: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en crearPaciente: " + e.getMessage());
            }
        }
        return nuevoPacienteId;
    }

    public List<PacienteDto> obtenerPacientes() {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<PacienteDto> pacientes = new ArrayList<>();
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            stmt = conn.prepareCall("{? = call citas_medicas.obtener_pacientes()}");
            stmt.registerOutParameter(1, Types.OTHER);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                PacienteDto paciente = new PacienteDto();
                paciente.setPacienteId(rs.getInt("paciente_id"));
                paciente.setCedula(rs.getString("cedula"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento")); // Automatically java.sql.Date
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setCorreoElectronico(rs.getString("correo_electronico"));
                paciente.setDireccion(rs.getString("direccion"));
                paciente.setFechaRegistro(rs.getTimestamp("fecha_registro")); // Automatically java.sql.Timestamp
                pacientes.add(paciente);
            }
            System.out.println("obtenerPacientes: Fetched " + pacientes.size() + " records.");
        } catch (Exception e) {
            System.err.println("Error en obtenerPacientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en obtenerPacientes: " + e.getMessage());
            }
        }
        return pacientes;
    }

    public PacienteDto obtenerPacientePorId(Integer pacienteId) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        PacienteDto paciente = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        if (pacienteId == null) return null;

        try {
//            Class.forName(conexion.getDrivers());
//            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
           // stmt = conn.prepareCall("{? = call citas_medicas.obtener_paciente_por_id(?)}");
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);

            stmt = conn.prepareCall("{? = call citas_medicas.obtener_paciente_por_id(?)}");

            stmt.registerOutParameter(1, Types.OTHER);
            stmt.setInt(2, pacienteId);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            if (rs.next()) {
                paciente = new PacienteDto();
                paciente.setPacienteId(rs.getInt("paciente_id"));
                paciente.setCedula(rs.getString("cedula"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setCorreoElectronico(rs.getString("correo_electronico"));
                paciente.setDireccion(rs.getString("direccion"));
                paciente.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                System.out.println("obtenerPacientePorId: Found paciente with ID " + pacienteId);
            } else {
                System.out.println("obtenerPacientePorId: No paciente found with ID " + pacienteId);
            }
        } catch (Exception e) {
            System.err.println("Error en obtenerPacientePorId: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en obtenerPacientePorId: " + e.getMessage());
            }
        }
        return paciente;
    }

    public PacienteDto obtenerPacientePorId_new(Integer pacienteId) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        PacienteDto paciente = null;
        ResultSet rs = null;
        List<ProductoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call citas_medicas.obtener_paciente_por_id_new(?)}");
                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setInt(2, pacienteId);
                stmt.execute();

                //ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet
                rs = (ResultSet) stmt.getObject(1);
                if (rs.next()) {
                    paciente = new PacienteDto();
                    paciente.setPacienteId(rs.getInt("paciente_id"));
                    paciente.setCedula(rs.getString("cedula"));
                    paciente.setNombre(rs.getString("nombre"));
                    paciente.setApellido(rs.getString("apellido"));
                    paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    paciente.setTelefono(rs.getString("telefono"));
                    paciente.setCorreoElectronico(rs.getString("correo_electronico"));
                    paciente.setDireccion(rs.getString("direccion"));
                    paciente.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                    System.out.println("obtenerPacientePorId: Found paciente with ID " + pacienteId);
                } else {
                    System.out.println("obtenerPacientePorId: No paciente found with ID " + pacienteId);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return paciente;

    }

    public List<CitaDto> obtenerCitaPorId_new(Integer citaId, Integer cedula) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<CitaDto> citas = new ArrayList<>();
        List<CitaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    conexion.getUrl() + conexion.getDbname(),
                    conexion.getUser(),
                    conexion.getPass()
            );
            conn.setAutoCommit(false);

            if (conn != null) {
                CallableStatement stmt = conn.prepareCall("{? = call citas_medicas.obtener_cita_por_id(?,?)}");
                stmt.registerOutParameter(1, Types.OTHER);
                if (citaId != null) {
                    stmt.setInt(2, citaId);
                } else {
                    stmt.setNull(2, Types.INTEGER);
                }
                if (cedula != null) {
                    stmt.setInt(3, cedula);
                } else {
                    stmt.setNull(3, Types.INTEGER);
                }

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1);

               while (rs.next()) {


                   CitaDto sol = new CitaDto();

                  sol.setCitaId(rs.getInt("cita_id"));
                  sol.setPacienteId(rs.getInt("paciente_id"));
                  sol.setPacienteNombreCompleto(rs.getString("nombrepaciente"));
                  sol.setMedicoId(rs.getInt("medico_id"));
                  sol.setMedicoNombreCompleto(rs.getString("nombremedico"));
                  sol.setEspecialidad(rs.getInt("especialidad"));
                  sol.setMedicoEspecialidad(rs.getString("medicoEspecialidad"));
                  sol.setFechaHora(rs.getString("fecha_hora"));
                  sol.setMotivo(rs.getString("motivo"));
                  sol.setNotasMedico(rs.getString("notas_medico"));
                  sol.setNotasPaciente(rs.getString("notas_paciente"));
                  sol.setFechaCreacion(rs.getString("fecha_creacion"));
                  sol.setFechaActualizacion(rs.getString("fecha_actualizacion"));
                  sol.setNumerocontrato(rs.getString("numero_contrato"));
                  sol.setCodigosuscripcion(rs.getInt("codigo_suscripcion"));
                  sol.setCedula(rs.getInt("cedula"));
                  sol.setClaveclinica(rs.getInt("clave_clinica"));
                  sol.setClinicaid(rs.getInt("clinica_id"));
                  sol.setNombreclinica(rs.getString("nombreclinica"));
                  sol.setEstado(rs.getInt("estado"));
                  sol.setEstatus(rs.getString("estatus"));
                  sol.setContratante(rs.getString("contratante"));
                  sol.setPrecio(rs.getDouble("precio"));







                    resp.add(sol);
                }

                rs.close();
                stmt.close();
                conn.close();
                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<MedicosDto> obtenerMedico_new(Integer medicoId, String clinica) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<MedicosDto> citas = new ArrayList<>();
        List<MedicosDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    conexion.getUrl() + conexion.getDbname(),
                    conexion.getUser(),
                    conexion.getPass()
            );
            conn.setAutoCommit(false);

            if (conn != null) {
                CallableStatement stmt = conn.prepareCall("{? = call citas_medicas.obtener_medicos(?,?)}");
                stmt.registerOutParameter(1, Types.OTHER);
                if (medicoId != null) {
                    stmt.setInt(2, medicoId);
                } else {
                    stmt.setNull(2, Types.INTEGER);
                }
                if (clinica != null) {
                    stmt.setString(3, clinica);
                } else {
                    stmt.setNull(3, Types.VARCHAR);
                }

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1);

                while (rs.next()) {


                    MedicosDto sol = new MedicosDto();

                    sol.setMedicoId(rs.getInt("medico_id"));
                    sol.setCedula(rs.getString("cedula"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setApellido(rs.getString("apellido"));
                    sol.setEspecialidadId(rs.getInt("especialidad_id"));
                    sol.setNombreEspecialidad(rs.getString("nombreEspecialidad"));
                    sol.setTelefono(rs.getString("telefono"));
                    sol.setCorreoElectronico(rs.getString("correo_electronico"));
                    sol.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                    sol.setEstatus(rs.getString("estatus"));
                    sol.setIdclinica(rs.getString("id_clinica"));





                    resp.add(sol);
                }

                rs.close();
                stmt.close();
                conn.close();
                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }



    public List<ConsultaMedicosDto> obtenerMedicoActual(Integer medicoId, String clinica) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ConsultaMedicosDto> citas = new ArrayList<>();
        List<ConsultaMedicosDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    conexion.getUrl() + conexion.getDbname(),
                    conexion.getUser(),
                    conexion.getPass()
            );
            conn.setAutoCommit(false);

            if (conn != null) {
                CallableStatement stmt = conn.prepareCall("{? = call citas_medicas.obtener_medicos(?,?)}");
                stmt.registerOutParameter(1, Types.OTHER);
                if (medicoId != null) {
                    stmt.setInt(2, medicoId);
                } else {
                    stmt.setNull(2, Types.INTEGER);
                }
                if (clinica != null) {
                    stmt.setString(3, clinica);
                } else {
                    stmt.setNull(3, Types.VARCHAR);
                }

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1);

                while (rs.next()) {


                    ConsultaMedicosDto sol = new ConsultaMedicosDto();

                    sol.setMedicoId(rs.getInt("medico_id"));
                    sol.setCedula(rs.getString("cedula"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setApellido(rs.getString("apellido"));
                    sol.setEspecialidadId(rs.getInt("especialidad_id"));
                    sol.setNombreEspecialidad(rs.getString("nombreEspecialidad"));
                    sol.setTelefono(rs.getString("telefono"));
                    sol.setCorreoElectronico(rs.getString("correo_electronico"));
                    sol.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                    sol.setId_estatus(rs.getString("id_estatus"));
                    sol.setEstatus(rs.getString("estatus"));
                    sol.setIdclinica(rs.getString("id_clinica"));
                    sol.setNombreclinica(rs.getString("nombreclinica"));





                    resp.add(sol);
                }

                rs.close();
                stmt.close();
                conn.close();
                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }




    public PacienteDto obtenerPacientePorCedula(String cedula) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        PacienteDto paciente = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        if (cedula == null || cedula.trim().isEmpty()) return null;

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            stmt = conn.prepareCall("{? = call citas_medicas.obtener_paciente_por_cedula(?)}");
            stmt.registerOutParameter(1, Types.OTHER);
            stmt.setString(2, cedula);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            if (rs.next()) {
                paciente = new PacienteDto();
                paciente.setPacienteId(rs.getInt("paciente_id"));
                paciente.setCedula(rs.getString("cedula"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                paciente.setTelefono(rs.getString("telefono"));
                paciente.setCorreoElectronico(rs.getString("correo_electronico"));
                paciente.setDireccion(rs.getString("direccion"));
                paciente.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                System.out.println("obtenerPacientePorCedula: Found paciente with cedula " + cedula);
            } else {
                System.out.println("obtenerPacientePorCedula: No paciente found with cedula " + cedula);
            }
        } catch (Exception e) {
            System.err.println("Error en obtenerPacientePorCedula: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en obtenerPacientePorCedula: " + e.getMessage());
            }
        }
        return paciente;
    }

    public String actualizarPaciente(PacienteDto paciente) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        String resultado = "ERROR: Unknown";
        CallableStatement stmt = null;

        if (paciente == null || paciente.getPacienteId() == null) {
            return "ERROR: Paciente o ID nulo.";
        }

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);

            stmt = conn.prepareCall("{? = call citas_medicas.actualizar_paciente(?, ?, ?, ?, ?, ?, ?, ?)}");
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setInt(2, paciente.getPacienteId());
            stmt.setString(3, paciente.getCedula());
            stmt.setString(4, paciente.getNombre());
            stmt.setString(5, paciente.getApellido());
            if (paciente.getFechaNacimiento() != null) {
                stmt.setDate(6, new java.sql.Date(paciente.getFechaNacimiento().getTime()));
            } else {
                stmt.setNull(6, Types.DATE);
            }
            stmt.setString(7, paciente.getTelefono());
            stmt.setString(8, paciente.getCorreoElectronico());
            stmt.setString(9, paciente.getDireccion());

            stmt.execute();
            resultado = stmt.getString(1);
            conn.commit();
            System.out.println("actualizarPaciente: Result for ID " + paciente.getPacienteId() + ": " + resultado);
        } catch (SQLException se) {
            System.err.println("Error SQL en actualizarPaciente: " + se.getMessage());
            se.printStackTrace();
            resultado = "ERROR: " + se.getMessage();
            if (conn != null) try { conn.rollback(); } catch (SQLException e) { e.printStackTrace(); }
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en actualizarPaciente. " + cnfe.getMessage());
            resultado = "ERROR: Driver no encontrado.";
        } catch (Exception e) {
            System.err.println("Error inesperado en actualizarPaciente: " + e.getMessage());
            e.printStackTrace();
            resultado = "ERROR: " + e.getMessage();
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en actualizarPaciente: " + e.getMessage());
            }
        }
        return resultado;
    }

    public String eliminarPaciente(Integer pacienteId) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        String resultado = "ERROR: Unknown";
        CallableStatement stmt = null;

        if (pacienteId == null) {
            return "ERROR: ID de paciente nulo.";
        }

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);

            stmt = conn.prepareCall("{? = call citas_medicas.eliminar_paciente(?)}");
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setInt(2, pacienteId);

            stmt.execute();
            resultado = stmt.getString(1);
            conn.commit();
            System.out.println("eliminarPaciente: Result for ID " + pacienteId + ": " + resultado);
        } catch (SQLException se) {
            System.err.println("Error SQL en eliminarPaciente: " + se.getMessage());
            se.printStackTrace();
            resultado = "ERROR: " + se.getMessage(); // FK violation if citas exist and ON DELETE is RESTRICT
            if (conn != null) try { conn.rollback(); } catch (SQLException e) { e.printStackTrace(); }
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en eliminarPaciente. " + cnfe.getMessage());
            resultado = "ERROR: Driver no encontrado.";
        } catch (Exception e) {
            System.err.println("Error inesperado en eliminarPaciente: " + e.getMessage());
            e.printStackTrace();
            resultado = "ERROR: " + e.getMessage();
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en eliminarPaciente: " + e.getMessage());
            }
        }
        return resultado;
    }

    // --- Métodos para Médicos ---

    public Integer crearMedico(MedicosDto medico) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Integer nuevoMedicoId = null;
        CallableStatement stmt = null;

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);

            stmt = conn.prepareCall("{? = call citas_medicas.crear_medico(?, ?, ?, ?, ?, ?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setString(2, medico.getCedula());
            stmt.setString(3, medico.getNombre());
            stmt.setString(4, medico.getApellido());
            if (medico.getEspecialidadId() != null) {
                stmt.setInt(5, medico.getEspecialidadId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.setString(6, medico.getTelefono());
            stmt.setString(7, medico.getCorreoElectronico());

            stmt.execute();
            nuevoMedicoId = stmt.getInt(1);
            if (stmt.wasNull()) {
                nuevoMedicoId = null;
            }
            conn.commit();
            System.out.println("crearMedico: Executed successfully. ID: " + nuevoMedicoId);
        } catch (SQLException se) {
            System.err.println("Error SQL en crearMedico: " + se.getMessage() + " SQLState: " + se.getSQLState());
            se.printStackTrace();
            if (conn != null) try { conn.rollback(); } catch (SQLException e) { e.printStackTrace(); }
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en crearMedico. " + cnfe.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado en crearMedico: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en crearMedico: " + e.getMessage());
            }
        }
        return nuevoMedicoId;
    }

    public List<MedicosDto> obtenerMedicos() {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<MedicosDto> medicos = new ArrayList<>();
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            stmt = conn.prepareCall("{? = call citas_medicas.obtener_medicos()}");
            stmt.registerOutParameter(1, Types.OTHER);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                MedicosDto medico = new MedicosDto();
                medico.setMedicoId(rs.getInt("medico_id"));
                medico.setCedula(rs.getString("cedula"));
                medico.setNombre(rs.getString("nombre"));
                medico.setApellido(rs.getString("apellido"));
                medico.setEspecialidadId(rs.getInt("especialidad_id"));
                if (rs.wasNull()) { // Handle null especialidad_id
                    medico.setEspecialidadId(null);
                }
                medico.setTelefono(rs.getString("telefono"));
                medico.setCorreoElectronico(rs.getString("correo_electronico"));
                medico.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                // medico.setNombreEspecialidad() would require a JOIN in SP or separate call
                medicos.add(medico);
            }
            System.out.println("obtenerMedicos: Fetched " + medicos.size() + " records.");
        } catch (Exception e) {
            System.err.println("Error en obtenerMedicos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en obtenerMedicos: " + e.getMessage());
            }
        }
        return medicos;
    }

    public MedicosDto obtenerMedicoPorId(Integer medicoId) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        MedicosDto medico = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        if (medicoId == null) return null;

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            stmt = conn.prepareCall("{? = call citas_medicas.obtener_medico_por_id(?)}");
            stmt.registerOutParameter(1, Types.OTHER);
            stmt.setInt(2, medicoId);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            if (rs.next()) {
                medico = new MedicosDto();
                medico.setMedicoId(rs.getInt("medico_id"));
                medico.setCedula(rs.getString("cedula"));
                medico.setNombre(rs.getString("nombre"));
                medico.setApellido(rs.getString("apellido"));
                medico.setEspecialidadId(rs.getInt("especialidad_id"));
                if (rs.wasNull()) {
                    medico.setEspecialidadId(null);
                }
                medico.setTelefono(rs.getString("telefono"));
                medico.setCorreoElectronico(rs.getString("correo_electronico"));
                medico.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                // medico.setNombreEspecialidad() would require a JOIN in SP or separate call
                System.out.println("obtenerMedicoPorId: Found medico with ID " + medicoId);
            } else {
                System.out.println("obtenerMedicoPorId: No medico found with ID " + medicoId);
            }
        } catch (Exception e) {
            System.err.println("Error en obtenerMedicoPorId: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en obtenerMedicoPorId: " + e.getMessage());
            }
        }
        return medico;
    }


    public MedicosDto  obtenerMedicoPorId_new(Integer medicoId) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        PacienteDto paciente = null;
        MedicosDto medico = null;
        ResultSet rs = null;
        List<ProductoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call citas_medicas.obtener_medico_por_id(?)}");
                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setInt(2, medicoId);
                stmt.execute();

                //ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet
                rs = (ResultSet) stmt.getObject(1);
                if (rs.next()) {
                    medico = new MedicosDto();
                    medico.setMedicoId(rs.getInt("medico_id"));
                    medico.setCedula(rs.getString("cedula"));
                    medico.setNombre(rs.getString("nombre"));
                    medico.setApellido(rs.getString("apellido"));
                    medico.setEspecialidadId(rs.getInt("especialidad_id"));
                    if (rs.wasNull()) {
                        medico.setEspecialidadId(null);
                    }
                    medico.setTelefono(rs.getString("telefono"));
                    medico.setCorreoElectronico(rs.getString("correo_electronico"));
                    medico.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                    // medico.setNombreEspecialidad() would require a JOIN in SP or separate call
                    System.out.println("obtenerMedicoPorId: Found medico with ID " + medicoId);
                } else {
                    System.out.println("obtenerMedicoPorId: No medico found with ID " + medicoId);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return medico;

    }


    public List<MedicosDto> obtenerMedicosPorEspecialidad(Integer especialidadId) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<MedicosDto> medicos = new ArrayList<>();
        CallableStatement stmt = null;
        ResultSet rs = null;

        if (especialidadId == null) return medicos; // Return empty list

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            stmt = conn.prepareCall("{? = call citas_medicas.obtener_medicos_por_especialidad(?)}");
            stmt.registerOutParameter(1, Types.OTHER);
            stmt.setInt(2, especialidadId);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                MedicosDto medico = new MedicosDto();
                medico.setMedicoId(rs.getInt("medico_id"));
                medico.setCedula(rs.getString("cedula"));
                medico.setNombre(rs.getString("nombre"));
                medico.setApellido(rs.getString("apellido"));
                medico.setEspecialidadId(rs.getInt("especialidad_id"));
                if (rs.wasNull()) {
                    medico.setEspecialidadId(null);
                }
                medico.setTelefono(rs.getString("telefono"));
                medico.setCorreoElectronico(rs.getString("correo_electronico"));
                medico.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                // medico.setNombreEspecialidad() would require a JOIN in SP or separate call
                medicos.add(medico);
            }
            System.out.println("obtenerMedicosPorEspecialidad: Fetched " + medicos.size() + " records for especialidad ID " + especialidadId);
        } catch (Exception e) {
            System.err.println("Error en obtenerMedicosPorEspecialidad: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en obtenerMedicosPorEspecialidad: " + e.getMessage());
            }
        }
        return medicos;
    }

    public String actualizarMedico(MedicosDto medico) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        String resultado = "ERROR: Unknown";
        CallableStatement stmt = null;

        if (medico == null || medico.getMedicoId() == null) {
            return "ERROR: Medico o ID nulo.";
        }

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);

            stmt = conn.prepareCall("{? = call citas_medicas.actualizar_medico(?, ?, ?, ?, ?, ?, ?)}");
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setInt(2, medico.getMedicoId());
            stmt.setString(3, medico.getCedula());
            stmt.setString(4, medico.getNombre());
            stmt.setString(5, medico.getApellido());
            if (medico.getEspecialidadId() != null) {
                stmt.setInt(6, medico.getEspecialidadId());
            } else {
                stmt.setNull(6, Types.INTEGER);
            }
            stmt.setString(7, medico.getTelefono());
            stmt.setString(8, medico.getCorreoElectronico());

            stmt.execute();
            resultado = stmt.getString(1);
            conn.commit();
            System.out.println("actualizarMedico: Result for ID " + medico.getMedicoId() + ": " + resultado);
        } catch (SQLException se) {
            System.err.println("Error SQL en actualizarMedico: " + se.getMessage());
            se.printStackTrace();
            resultado = "ERROR: " + se.getMessage();
            if (conn != null) try { conn.rollback(); } catch (SQLException e) { e.printStackTrace(); }
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en actualizarMedico. " + cnfe.getMessage());
            resultado = "ERROR: Driver no encontrado.";
        } catch (Exception e) {
            System.err.println("Error inesperado en actualizarMedico: " + e.getMessage());
            e.printStackTrace();
            resultado = "ERROR: " + e.getMessage();
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en actualizarMedico: " + e.getMessage());
            }
        }
        return resultado;
    }

    public ClaveDto creanuevacita(CrearCitaRequestDto citaRequest) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        ClaveDto resp =   new ClaveDto();


        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call citas_medicas.crear_citas(?, ?, ?, ?, ?, ?,?,?,?,?)}");
                stmt.registerOutParameter(1, Types.INTEGER); // Set the output parameter type
                stmt.setInt(2, citaRequest.getPacienteId());
                stmt.setInt(3, citaRequest.getMedicoId());
                stmt.setString(4, citaRequest.getFecha());
                stmt.setString(5, citaRequest.getMotivo());
                stmt.setString(6, citaRequest.getNotasPaciente());
                stmt.setString(7, citaRequest.getContrato());
                stmt.setInt(8, citaRequest.getSuscripcion());
                stmt.setInt(9, citaRequest.getCedula());
                stmt.setString(10, citaRequest.getClinica());
                stmt.setInt(11, citaRequest.getEstatus());



                stmt.execute();
                Integer resultado = stmt.getInt(1);
                ClaveDto sol = new ClaveDto();
                sol.setClave(resultado);
                // resp.((resultado));
                resp.setClave((resultado));



                conn.commit();
                // rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }




    public RespuestaCitasDto actualizaCitasNew(ActulizaCitaDto citaRequest) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        RespuestaCitasDto resp =   new RespuestaCitasDto();


        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call citas_medicas.actualizar_citas(?, ?, ?, ?)}");
                stmt.registerOutParameter(1, Types.VARCHAR); // Set the output parameter type
                stmt.setInt(2, citaRequest.getCitaid());
                stmt.setString(3, citaRequest.getFecha_hora());
                stmt.setInt(4, citaRequest.getEstado());
                stmt.setString(5, citaRequest.getUsuario());




                stmt.execute();
                String resultado = stmt.getString(1);
                RespuestaCitasDto sol = new RespuestaCitasDto();
                sol.setRespuesta(resultado);
                // resp.((resultado));
                resp.setRespuesta((resultado));



                conn.commit();
                // rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }




    // --- Métodos para Citas ---

    public Integer crearCita(CrearCitaRequestDto citaRequest) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Integer nuevaCitaId = null;
        CallableStatement stmt = null;

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);

            stmt = conn.prepareCall("{? = call citas_medicas.crear_cita(?, ?, ?, ?, ?, ?,?,?,?)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.setInt(2, citaRequest.getPacienteId());
            stmt.setInt(3, citaRequest.getMedicoId());
            stmt.setString(4,citaRequest.getFecha());
            stmt.setString(5, citaRequest.getMotivo());
            stmt.setString(6, citaRequest.getNotasPaciente());
            stmt.setString(7, citaRequest.getContrato());
            stmt.setInt(8, citaRequest.getSuscripcion());
            stmt.setInt(9, citaRequest.getCedula());
            stmt.setString(10, "Programada"); // Default estado as per SP definition

            stmt.execute();
            nuevaCitaId = stmt.getInt(1);
            if (stmt.wasNull()) {
                nuevaCitaId = null;
            }
            conn.commit();
            System.out.println("crearCita: Executed successfully. ID: " + nuevaCitaId);
        } catch (SQLException se) {
            System.err.println("Error SQL en crearCita: " + se.getMessage() + " SQLState: " + se.getSQLState());
            se.printStackTrace();
            if (conn != null) try { conn.rollback(); } catch (SQLException e) { e.printStackTrace(); }
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en crearCita. " + cnfe.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado en crearCita: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en crearCita: " + e.getMessage());
            }
        }
        return nuevaCitaId;
    }

    private CitaDto mapResultSetToCitaDto(ResultSet rs) throws SQLException {
        CitaDto cita = new CitaDto();
        cita.setCitaId(rs.getInt("cita_id"));
        cita.setPacienteId(rs.getInt("paciente_id"));
        cita.setMedicoId(rs.getInt("medico_id"));
        cita.setFechaHora(rs.getString("fecha_hora"));
        cita.setMotivo(rs.getString("motivo"));
     //   cita.setEstado(rs.getString("estado"));
        cita.setNotasMedico(rs.getString("notas_medico"));
        cita.setNotasPaciente(rs.getString("notas_paciente"));
       // cita.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
       // cita.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion"));
        cita.setNumerocontrato(rs.getString("numero_contrato"));
        cita.setCodigosuscripcion(rs.getInt("codigo_suscripcion"));
        cita.setCedula(rs.getInt("cedula"));


        // pacienteNombreCompleto, medicoNombreCompleto, medicoEspecialidad are not set here
        return cita;
    }

    public CitaDto obtenerCitaPorId(Integer citaId) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        CitaDto cita = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        if (citaId == null) return null;

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            stmt = conn.prepareCall("{? = call citas_medicas.obtener_cita_por_id(?)}");
            stmt.registerOutParameter(1, Types.OTHER);
            stmt.setInt(2, citaId);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            if (rs.next()) {
                cita = mapResultSetToCitaDto(rs);
                System.out.println("obtenerCitaPorId: Found cita with ID " + citaId);
            } else {
                System.out.println("obtenerCitaPorId: No cita found with ID " + citaId);
            }
        } catch (Exception e) {
            System.err.println("Error en obtenerCitaPorId: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en obtenerCitaPorId: " + e.getMessage());
            }
        }
        return cita;
    }




    public List<CitaDto> obtenerCitasPorPaciente(Integer pacienteId, java.util.Date fechaDesde, java.util.Date fechaHasta) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<CitaDto> citas = new ArrayList<>();
        CallableStatement stmt = null;
        ResultSet rs = null;

        if (pacienteId == null) return citas;

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            stmt = conn.prepareCall("{? = call citas_medicas.obtener_citas_por_paciente(?, ?, ?)}");
            stmt.registerOutParameter(1, Types.OTHER);
            stmt.setInt(2, pacienteId);
            stmt.setTimestamp(3, fechaDesde == null ? null : new java.sql.Timestamp(fechaDesde.getTime()));
            stmt.setTimestamp(4, fechaHasta == null ? null : new java.sql.Timestamp(fechaHasta.getTime()));
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                citas.add(mapResultSetToCitaDto(rs));
            }
            System.out.println("obtenerCitasPorPaciente: Fetched " + citas.size() + " records for paciente ID " + pacienteId);
        } catch (Exception e) {
            System.err.println("Error en obtenerCitasPorPaciente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en obtenerCitasPorPaciente: " + e.getMessage());
            }
        }
        return citas;
    }

    public List<CitaDto> obtenerCitasPorMedico(Integer medicoId, java.util.Date fechaDesde, java.util.Date fechaHasta) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<CitaDto> citas = new ArrayList<>();
        CallableStatement stmt = null;
        ResultSet rs = null;

        if (medicoId == null) return citas;

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            stmt = conn.prepareCall("{? = call citas_medicas.obtener_citas_por_medico(?, ?, ?)}");
            stmt.registerOutParameter(1, Types.OTHER);
            stmt.setInt(2, medicoId);
            stmt.setTimestamp(3, fechaDesde == null ? null : new java.sql.Timestamp(fechaDesde.getTime()));
            stmt.setTimestamp(4, fechaHasta == null ? null : new java.sql.Timestamp(fechaHasta.getTime()));
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                citas.add(mapResultSetToCitaDto(rs));
            }
            System.out.println("obtenerCitasPorMedico: Fetched " + citas.size() + " records for medico ID " + medicoId);
        } catch (Exception e) {
            System.err.println("Error en obtenerCitasPorMedico: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en obtenerCitasPorMedico: " + e.getMessage());
            }
        }
        return citas;
    }

    public String actualizarEstadoCita(Integer citaId, String nuevoEstado, String notasMedico) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        String resultado = "ERROR: Unknown";
        CallableStatement stmt = null;

        if (citaId == null || nuevoEstado == null || nuevoEstado.trim().isEmpty()) {
            return "ERROR: Cita ID o nuevo estado nulo/vacío.";
        }

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);

            stmt = conn.prepareCall("{? = call citas_medicas.actualizar_estado_cita(?, ?, ?)}");
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setInt(2, citaId);
            stmt.setString(3, nuevoEstado);
            stmt.setString(4, notasMedico); // Can be null

            stmt.execute();
            resultado = stmt.getString(1);
            conn.commit();
            System.out.println("actualizarEstadoCita: Result for ID " + citaId + ": " + resultado);
        } catch (SQLException se) {
            System.err.println("Error SQL en actualizarEstadoCita: " + se.getMessage());
            se.printStackTrace();
            resultado = "ERROR: " + se.getMessage();
            if (conn != null) try { conn.rollback(); } catch (SQLException e) { e.printStackTrace(); }
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en actualizarEstadoCita. " + cnfe.getMessage());
            resultado = "ERROR: Driver no encontrado.";
        } catch (Exception e) {
            System.err.println("Error inesperado en actualizarEstadoCita: " + e.getMessage());
            e.printStackTrace();
            resultado = "ERROR: " + e.getMessage();
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en actualizarEstadoCita: " + e.getMessage());
            }
        }
        return resultado;
    }

    public String reprogramarCita(Integer citaId, java.util.Date nuevaFechaHora, String notasPaciente) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        String resultado = "ERROR: Unknown";
        CallableStatement stmt = null;

        if (citaId == null || nuevaFechaHora == null) {
            return "ERROR: Cita ID o nueva fecha/hora nula.";
        }

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);

            stmt = conn.prepareCall("{? = call citas_medicas.reprogramar_cita(?, ?, ?)}");
            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setInt(2, citaId);
            stmt.setTimestamp(3, new java.sql.Timestamp(nuevaFechaHora.getTime()));
            stmt.setString(4, notasPaciente); // Can be null

            stmt.execute();
            resultado = stmt.getString(1);
            conn.commit();
            System.out.println("reprogramarCita: Result for ID " + citaId + ": " + resultado);
        } catch (SQLException se) {
            System.err.println("Error SQL en reprogramarCita: " + se.getMessage());
            se.printStackTrace();
            resultado = "ERROR: " + se.getMessage();
            if (conn != null) try { conn.rollback(); } catch (SQLException e) { e.printStackTrace(); }
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en reprogramarCita. " + cnfe.getMessage());
            resultado = "ERROR: Driver no encontrado.";
        } catch (Exception e) {
            System.err.println("Error inesperado en reprogramarCita: " + e.getMessage());
            e.printStackTrace();
            resultado = "ERROR: " + e.getMessage();
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en reprogramarCita: " + e.getMessage());
            }
        }
        return resultado;
    }

    public List<RegistrarInventarioDto> consultar_inventario(String codaticulo,
                                                             Integer codaplicativo) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<RegistrarInventarioDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call farmacia.consulta_inventario(?,?)}");
                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setString(1, codaticulo);
                stmt.setInt(2, codaplicativo);


                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    // Procesar los datos del ResultSet
                    RegistrarInventarioDto sol = new RegistrarInventarioDto();

                    sol.setCodarticulo(rs.getString("cod_articulo"));
                    sol.setDescripcion(rs.getString("descripcion"));
                    sol.setCodtipo(rs.getInt("cod_tipo"));
                    sol.setCantidad(rs.getInt("cantidad"));
                    sol.setPreciocosto(rs.getDouble("precio_costo"));
                    sol.setPrecioventa(rs.getDouble("precio_venta"));
                    sol.setDescuento(rs.getDouble("descuento"));
                    sol.setVolumen(rs.getInt("volumen"));
                    sol.setDescstatus(rs.getString("descripcion_estaus"));
                    sol.setFechamod(rs.getString("fecha_mod"));
                    sol.setUsuario(rs.getString("usuario"));
                    sol.setCodaplicativo(rs.getInt("cod_aplicativo"));
                    sol.setFecharegistro(rs.getString("fecha_registro"));


                    resp.add(sol);
                }
                conn.commit();
                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<Estados> Estatus_inventario() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<Estados> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call farmacia.consulta_estatus_inventario()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    Estados sol = new Estados();

                    sol.setCodigo(rs.getString("codigo"));
                    sol.setNombre(rs.getString("nombre"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<Estados> Tipo_inventario(Integer codaplicativo ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<Estados> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call farmacia.consulta_tipos_inventario()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setInt(1, codaplicativo);


                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    Estados sol = new Estados();

                    sol.setCodigo(rs.getString("codigo"));
                    sol.setNombre(rs.getString("nombre"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }
    public Resultado registrar_inventario(
            String   descripcion,
            Integer  codtipo,
            Integer  cantidad,
            Double   preciocosto,
            Double   precioventa,
            Double   descuento,
            Integer  volumen,
            String   status,
            String   fechamod,
            String   usuario,
            Integer  codaplicativo,
            String   fecharegistro) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Resultado resp =   new Resultado();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call farmacia.registrar_inventario(?,?,?,?,?,?,?,?,?,?,?,?)}");
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, descripcion);
                stmt.setInt(2, codtipo);
                stmt.setInt(3, cantidad);
                stmt.setDouble(4, preciocosto);
                stmt.setDouble(5, precioventa);
                stmt.setDouble(6, descuento);
                stmt.setInt(7, volumen);
                stmt.setString(8, status);
                stmt.setString(9, fechamod);
                stmt.setString(10, usuario);
                stmt.setInt(11, codaplicativo);
                stmt.setString(12, fecharegistro);



                stmt.execute();
                String resultado = stmt.getString(1);
                Resultado sol = new Resultado();
                sol.setRetorno(resultado);
                // resp.((resultado));
                resp.setRetorno((resultado));



                conn.commit();
                // rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public Resultado actualiza_inventario(
            String   codarticulo,
            String   descripcion,
            Integer  codtipo,
            Integer  cantidad,
            Double   preciocosto,
            Double   precioventa,
            Double   descuento,
            Integer  volumen,
            String   status,
            String   fechamod,
            String   usuario,
            Integer  codaplicativo,
            String   fecharegistro) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Resultado resp =   new Resultado();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call farmacia.actualiza_inventario(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, codarticulo);
                stmt.setString(2, descripcion);
                stmt.setInt(3, codtipo);
                stmt.setInt(4, cantidad);
                stmt.setDouble(5, preciocosto);
                stmt.setDouble(6, precioventa);
                stmt.setDouble(7, descuento);
                stmt.setInt(8, volumen);
                stmt.setString(9, status);
                stmt.setString(10, fechamod);
                stmt.setString(11, usuario);
                stmt.setInt(12, codaplicativo);
                stmt.setString(13, fecharegistro);



                stmt.execute();
                String resultado = stmt.getString(1);
                Resultado sol = new Resultado();
                sol.setRetorno(resultado);
                // resp.((resultado));
                resp.setRetorno((resultado));



                conn.commit();
                // rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public List<TipoVolumenDto> Consulta_volumen(RegistrarInventarioDto registro) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<TipoVolumenDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call farmacia.consulta_volumen()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setInt(1, registro.getCodaplicativo());



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    TipoVolumenDto sol = new TipoVolumenDto();

                    sol.setCodvolumen(rs.getInt("cod_volumen"));
                    sol.setDescripcion(rs.getString("descripcion"));
                    sol.setStatus(rs.getString("status"));
                    sol.setFechacreacion(rs.getString("fecha_creacion"));
                    sol.setUsuario(rs.getString("usuario"));
                    sol.setVolumen(rs.getInt("volumen"));
                    sol.setIdaplicativo(rs.getInt("id_aplicativo"));
                    sol.setPrecio(rs.getDouble("precio_venta"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }



    private HistoriaMedicaDto mapRowToHistoriaMedicaDto(ResultSet rs) throws SQLException {
        HistoriaMedicaDto dto = new HistoriaMedicaDto();
        dto.setHistoriaId(rs.getInt("historia_id"));
        dto.setCitaId(rs.getObject("cita_id") != null ? rs.getInt("cita_id") : null); // Handle potential NULL for cita_id
        dto.setPacienteId(rs.getInt("paciente_id"));
        dto.setMedicoId(rs.getInt("medico_id"));
        dto.setFechaCreacion(rs.getTimestamp("fecha_creacion"));
        dto.setDiagnostico(rs.getString("diagnostico"));
        dto.setTratamiento(rs.getString("tratamiento"));
        dto.setNotasAdicionales(rs.getString("notas_adicionales"));
        dto.setArchivosAdjuntos(rs.getString("archivos_adjuntos")); // Assuming JSONB is read as String
        return dto;
    }


    public Integer crearHistoriaMedica(CrearHistoriaMedicaRequestDto historiaDto) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Integer nuevaHistoriaId = null;
        CallableStatement stmt = null;
        String sql = "{? = call citas_medicas.crear_historia_medica(?, ?, ?, ?, ?, ?, ?)}";

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);

            stmt = conn.prepareCall(sql);
            stmt.registerOutParameter(1, Types.INTEGER); // historia_id (RETURN value)

            // Set IN parameters
            if (historiaDto.getCitaId() != null) {
                stmt.setInt(2, historiaDto.getCitaId());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setInt(3, historiaDto.getPacienteId());
            stmt.setInt(4, historiaDto.getMedicoId());
            stmt.setString(5, historiaDto.getDiagnostico());
            stmt.setString(6, historiaDto.getTratamiento());
            stmt.setString(7, historiaDto.getNotasAdicionales());
            // For JSONB, pass as String. Ensure PostgreSQL driver handles JSONB correctly.
            if (historiaDto.getArchivosAdjuntos() != null) {
                stmt.setString(8, historiaDto.getArchivosAdjuntos());
            } else {
                stmt.setNull(8, Types.OTHER); // Or Types.VARCHAR if function expects text for JSON
            }

            stmt.execute();
            nuevaHistoriaId = stmt.getInt(1);
            if (stmt.wasNull()) {
                nuevaHistoriaId = null;
            }

            conn.commit();
            System.out.println("crearHistoriaMedica: Executed successfully. New historia_id: " + nuevaHistoriaId);
        } catch (SQLException se) {
            System.err.println("Error SQL en crearHistoriaMedica: " + se.getMessage() + " SQLState: " + se.getSQLState());
            se.printStackTrace();
            if (conn != null) try { conn.rollback(); } catch (SQLException e) { e.printStackTrace(); }
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en crearHistoriaMedica. " + cnfe.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado en crearHistoriaMedica: " + e.getMessage());
            e.printStackTrace();
            if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en crearHistoriaMedica: " + e.getMessage());
            }
        }
        return nuevaHistoriaId;
    }

    public HistoriaMedicaDto obtenerHistoriaMedicaPorId(Integer historiaId) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        HistoriaMedicaDto historiaMedica = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        String sql = "{? = call citas_medicas.obtener_historia_medica_por_id(?)}";

        if (historiaId == null) return null;

        try {
            Class.forName(conexion.getDrivers());
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());

            stmt = conn.prepareCall(sql);
            stmt.registerOutParameter(1, Types.OTHER); // For cursor
            stmt.setInt(2, historiaId);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            if (rs.next()) {
                historiaMedica = mapRowToHistoriaMedicaDto(rs);
                System.out.println("obtenerHistoriaMedicaPorId: Found historia_medica with ID " + historiaId);
            } else {
                System.out.println("obtenerHistoriaMedicaPorId: No historia_medica found with ID " + historiaId);
            }
        } catch (SQLException se) {
            System.err.println("Error SQL en obtenerHistoriaMedicaPorId: " + se.getMessage());
            se.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: Driver no encontrado en obtenerHistoriaMedicaPorId. " + cnfe.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado en obtenerHistoriaMedicaPorId: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando recursos en obtenerHistoriaMedicaPorId: " + e.getMessage());
            }
        }
        return historiaMedica;
    }


    public List<ProcesoImpuestosDto> Proceso_impuesto(ProcesoTasaDto proceso) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<ProcesoImpuestosDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call farmacia.proceso_impuestos(?)}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setDouble(1, proceso.getMonto());
                stmt.setString(2, proceso.getCodigo());

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    ProcesoImpuestosDto sol = new ProcesoImpuestosDto();

                    sol.setIvabs(rs.getDouble("ivabs"));
                    sol.setIgtfbs(rs.getDouble("igtfbs"));
                    sol.setIvadolar(rs.getDouble("ivadolar"));
                    sol.setIgtfdolar(rs.getDouble("igtfdolar"));
                    sol.setIvaeuro(rs.getDouble("ivaeuro"));
                    sol.setIgtfeuro(rs.getDouble("igtfeuro"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public Resultado registrar_factura(
            String  fechaemision,
            Integer idcliente,
            String  idarticulo,
            Integer cantidad,
            Double  precioventa,
            Double  descuento,
            String  descripcion,
            Double  preciounitario,
            Double  preciounitariodolar,
            Double  preciounitarioeuro,
            Double  subtotal,
            Double  subtotaldolar,
            Double  subtotaleuro,
            Double  montosubtotal,
            Double  montosubtotaldolar,
            Double  montosubtotaleuro,
            Double  montoiva,
            Double  montoivadolar,
            Double  montoivaeuro,
            Double  montoigtf,
            Double  montoigtfdolar,
            Double  montoigtfeuro,
            Double  montototal,
            Double  montototaldolar,
            Double  montototaleuro,
            String  status,
            String  fechamod,
            String  usuario,
            Integer codaplicativo,
            String  despacho) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Resultado resp =   new Resultado();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call farmacia.registrar_factura(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, fechaemision);
                stmt.setInt(2, idcliente);
                stmt.setString(3, idarticulo);
                stmt.setInt(4, cantidad);
                stmt.setDouble(5, precioventa);
                stmt.setDouble(6, descuento);
                stmt.setString(7, descripcion);
                stmt.setDouble(8, preciounitario);
                stmt.setDouble(9, preciounitariodolar);
                stmt.setDouble(10, preciounitarioeuro);
                stmt.setDouble(11, subtotal);
                stmt.setDouble(12, subtotaldolar);
                stmt.setDouble(13, subtotaleuro);
                stmt.setDouble(14, montosubtotal);
                stmt.setDouble(15, montosubtotaldolar);
                stmt.setDouble(16, montosubtotaleuro);
                stmt.setDouble(17, montoiva);
                stmt.setDouble(18, montoivadolar);
                stmt.setDouble(19, montoivaeuro);
                stmt.setDouble(20, montoigtf);
                stmt.setDouble(21, montoigtfdolar);
                stmt.setDouble(22, montoigtfeuro);
                stmt.setDouble(23, montototal);
                stmt.setDouble(24, montototaldolar);
                stmt.setDouble(25, montototaleuro);
                stmt.setString(26, status);
                stmt.setString(27, fechamod);
                stmt.setString(28, usuario);
                stmt.setInt(29, codaplicativo);
                stmt.setString(30, despacho);




                stmt.execute();
                String resultado = stmt.getString(1);
                Resultado sol = new Resultado();
                sol.setRetorno(resultado);
                // resp.((resultado));
                resp.setRetorno((resultado));



                conn.commit();
                // rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public Resultado detalle_factura(
            String  factura,
            Integer idcliente,
            String  idarticulo,
            String  descripcion,
            Integer cantidad,
            Double  preciounitario,
            Double  preciounitariodolar,
            Double  preciounitarioeuro,
            Double  descuento,
            Double  descuentodolar,
            Double  descuentoeuro,
            Double  subtotal,
            Double  subtotaldolar,
            Double  subtotaleuro,
            String  status,
            String  fechamod,
            String  usuario,
            Integer codaplicativo,
            Integer volumen ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Resultado resp =   new Resultado();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call farmacia.registrar_factura_detalle(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, factura);
                stmt.setInt(2, idcliente);
                stmt.setString(3, idarticulo);
                stmt.setString(4, descripcion);
                stmt.setInt(5, cantidad);
                stmt.setDouble(6, preciounitario);
                stmt.setDouble(7, preciounitariodolar);
                stmt.setDouble(8, preciounitarioeuro);
                stmt.setDouble(9, descuento);
                stmt.setDouble(10, descuentodolar);
                stmt.setDouble(11, descuentoeuro);
                stmt.setDouble(12, subtotal);
                stmt.setDouble(13, subtotaldolar);
                stmt.setDouble(14, subtotaleuro);
                stmt.setString(15, status);
                stmt.setString(16, fechamod);
                stmt.setString(17, usuario);
                stmt.setInt(18, codaplicativo);
                stmt.setInt(19, volumen);





                stmt.execute();
                String resultado = stmt.getString(1);
                Resultado sol = new Resultado();
                sol.setRetorno(resultado);
                // resp.((resultado));
                resp.setRetorno((resultado));







                conn.commit();
                // rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public Resultado registrar_forma_pago(
            Integer formapago,
            String  fechapago,
            String  referenciapago,
            Double  monto,
            String  codmoneda,
            String  correo,
            String  factura,
            Integer idcliente,
            String  fechamod,
            String  usuariomod,
            Integer idbanco,
            Integer codaplicativo,
            Integer idpunto,
            Integer status) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Resultado resp =   new Resultado();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call farmacia.registrar_forma_pago(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setInt(1, formapago);
                stmt.setString(2, fechapago);
                stmt.setString(3, referenciapago);
                stmt.setDouble(4, monto);
                stmt.setString(5, codmoneda);
                stmt.setString(6, correo);
                stmt.setString(7, factura);
                stmt.setInt(8, idcliente);
                stmt.setString(9, fechamod);
                stmt.setString(10, usuariomod);
                stmt.setInt(11, idbanco);
                stmt.setInt(12, codaplicativo);
                //stmt.setInt(13, idpunto);
                if (idpunto != null) {
                    stmt.setInt(13, idpunto);
                } else {
                    stmt.setNull(13, Types.INTEGER);
                }
                if (status != null) {
                    stmt.setInt(14, status);
                } else {
                    stmt.setNull(14, Types.INTEGER);
                }



                stmt.execute();
                String resultado = stmt.getString(1);
                Resultado sol = new Resultado();
                sol.setRetorno(resultado);
                // resp.((resultado));
                resp.setRetorno((resultado));



                conn.commit();
                // rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<Estados> Consulta_Moneda() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<Estados> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call farmacia.consulta_moneda()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    Estados sol = new Estados();

                    sol.setCodigo(rs.getString("codigo"));
                    sol.setNombre(rs.getString("nombre"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<Estados> Consulta_referencia_pago() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<Estados> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call farmacia.consulta_referencia_pago()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    Estados sol = new Estados();

                    sol.setCodigo(rs.getString("codigo"));
                    sol.setNombre(rs.getString("nombre"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<DetFacturaDto> consultar_detalle_factura(RegsitrarFacturaDto registro) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<DetFacturaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call farmacia.consulta_factura_datalle(?,?)}");
                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setString(1, registro.getFactura());
                stmt.setInt(2, registro.getCodaplicativo());


                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    // Procesar los datos del ResultSet
                    DetFacturaDto sol = new DetFacturaDto();

                    sol.setIddetallefactura(rs.getInt("id_detalle_factura"));
                    sol.setFactura(rs.getString("factura"));
                    sol.setIdcliente(rs.getInt("id_cliente"));
                    sol.setIdarticulo(rs.getString("id_articulo"));
                    sol.setDescripcion(rs.getString("descripcion"));
                    sol.setCantidad(rs.getInt("cantidad"));
                    sol.setPreciounitario(rs.getDouble("precio_unitario"));
                    sol.setPreciounitariodolar(rs.getDouble("precio_unitario_dolar"));
                    sol.setPreciounitarioeuro(rs.getDouble("precio_unitario_euro"));
                    sol.setDescuento(rs.getDouble("descuento"));
                    sol.setDescuentodolar(rs.getDouble("descuento_dolar"));
                    sol.setDescuentoeuro(rs.getDouble("descuento_euro"));
                    sol.setSubtotal(rs.getDouble("sub_total"));
                    sol.setSubtotaldolar(rs.getDouble("sub_total_dolar"));
                    sol.setSubtotaleuro(rs.getDouble("sub_total_euro"));
                    sol.setStatus(rs.getString("status"));
                    sol.setFechamod(rs.getString("fecha_mod"));
                    sol.setUsuario(rs.getString("usuario"));
                    sol.setCodaplicativo(rs.getInt("cod_aplicativo"));

                    resp.add(sol);
                }
                conn.commit();
                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<FormaPagoFDto> consultar_pago(FormaPagoFDto registro) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<FormaPagoFDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call farmacia.consulta_forma_pago(?,?)}");
                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setInt(1, registro.getCodaplicativo() );
                stmt.setString(2, registro.getFactura());


                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    // Procesar los datos del ResultSet
                    FormaPagoFDto sol = new FormaPagoFDto();

                    //  sol.setFormapago(rs.getInt("forma_pago"));
                    sol.setDescripcionfp(rs.getString("descripcionfp"));
                    sol.setFechapago(rs.getString("fecha_pago"));
                    sol.setReferenciapago(rs.getString("referencia_pago"));
                    sol.setMonto(rs.getDouble("monto"));
                    sol.setCodmoneda(rs.getString("cod_moneda"));
                    sol.setCorreo(rs.getString("correo"));
                    sol.setFactura(rs.getString("factura"));
                    sol.setIdcliente(rs.getInt("id_cliente"));
                    sol.setFechamod(rs.getString("fecha_mod"));
                    sol.setUsuariomod(rs.getString("usuario_mod"));
                    sol.setIdreferencia(rs.getInt("id_referencia"));
                    sol.setDescripcionbco(rs.getString("descripcionbco"));
                    sol.setCodaplicativo(rs.getInt("cod_aplicativo"));
                    sol.setIdpuntoventa(rs.getInt("id_punto"));
                    sol.setEstatus_factura(rs.getString("estatus_factura"));



                    resp.add(sol);
                }
                conn.commit();
                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public List<RegsitrarFacturaDto> consultar_factura(RegsitrarFacturaDto registro) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<RegsitrarFacturaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call farmacia.consulta_factura(?,?,?)}");
                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setString(1, registro.getFactura());
                stmt.setString(2,registro.getFechaemision() );
                stmt.setInt(3, registro.getCodaplicativo());


                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    // Procesar los datos del ResultSet
                    RegsitrarFacturaDto sol = new RegsitrarFacturaDto();

                    sol.setFactura(rs.getString("factura"));
                    sol.setFechaemision(rs.getString("fecha_emision"));
                    sol.setIdcliente(rs.getInt("id_cliente"));
                    sol.setIdarticulo(rs.getString("id_articulo"));
                    sol.setCantidad(rs.getInt("cantidad"));
                    sol.setPrecioventa(rs.getDouble("precio_venta"));
                    sol.setDescuento(rs.getDouble("descuento"));
                    sol.setDescripcion(rs.getString("descripcion"));
                    sol.setPreciounitario(rs.getDouble("precio_unitario"));
                    sol.setPreciounitariodolar(rs.getDouble("precio_unitario_dolar"));
                    sol.setPreciounitarioeuro(rs.getDouble("precio_unitario_euro"));
                    sol.setSubtotal(rs.getDouble("sub_total"));
                    sol.setSubtotaldolar(rs.getDouble("sub_total_dolar"));
                    sol.setSubtotaleuro(rs.getDouble("sub_total_euro"));

                    sol.setMontosubtotal(rs.getDouble("monto_sub_total"));
                    sol.setMontosubtotaldolar(rs.getDouble("monto_sub_total_dolar"));
                    sol.setMontosubtotaleuro(rs.getDouble("monto_sub_total_euro"));

                    sol.setMontoiva(rs.getDouble("monto_iva"));
                    sol.setMontoivadolar(rs.getDouble("monto_iva_dolar"));
                    sol.setMontoivaeuro(rs.getDouble("monto_iva_euro"));
                    sol.setMontoigtf(rs.getDouble("monto_igtf"));
                    sol.setMontoigtfdolar(rs.getDouble("monto_igtf_dolar"));
                    sol.setMontoigtfeuro(rs.getDouble("monto_igtf_euro"));
                    sol.setMontototal(rs.getDouble("monto_total"));
                    sol.setMontototaldolar(rs.getDouble("monto_total_dolar"));
                    sol.setMontototaleuro(rs.getDouble("monto_total_euro"));

                    sol.setStatus(rs.getString("status"));
                    sol.setFechamod(rs.getString("fecha_mod"));
                    sol.setUsuario(rs.getString("usuario"));
                    sol.setCodaplicativo(rs.getInt("cod_aplicativo"));

                    resp.add(sol);
                }
                conn.commit();
                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public Resultado atualiza_despacho(
//            String factura,
//            String despacho,
//            Integer codaplicativo
            RegsitrarFacturaDto registro

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Resultado resp =   new Resultado();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call farmacia.actualiza_despacho(?,?,?)}");
                stmt.registerOutParameter(1, Types.VARCHAR);

                stmt.setString(1, registro.getFactura());
                stmt.setString(2, registro.getDespacho());
                stmt.setInt(3, registro.getCodaplicativo());






                stmt.execute();
                String resultado = stmt.getString(1);
                Resultado sol = new Resultado();
                sol.setRetorno(resultado);
                // resp.((resultado));
                resp.setRetorno((resultado));



                conn.commit();
                // rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }
    public Resultado anula_factura(
            RegsitrarFacturaDto registro
    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Resultado resp =   new Resultado();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call farmacia.proceso_anula_factura(?,?)}");
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, registro.getFactura());
                stmt.setInt(2, registro.getCodaplicativo());




                stmt.execute();
                String resultado = stmt.getString(1);
                Resultado sol = new Resultado();
                sol.setRetorno(resultado);
                // resp.((resultado));
                resp.setRetorno((resultado));



                conn.commit();
                // rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }
    public List<Estados> Consulta_puntos() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<Estados> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call farmacia.consulta_pto_vta()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    Estados sol = new Estados();

                    sol.setCodigo(rs.getString("codigo"));
                    sol.setNombre(rs.getString("nombre"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

    public List<Estados> Consulta_banco() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<Estados> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call farmacia.consulta_banco()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    Estados sol = new Estados();

                    sol.setCodigo(rs.getString("codigo"));
                    sol.setNombre(rs.getString("nombre"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }
    public List<RegistrarClientes> consultar_cliente(RegistrarClientes registro) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<RegistrarClientes> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call farmacia.consulta_clientes(?,?,?,?)}");
                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setString(1, registro.getTelefonocel());
                stmt.setInt(2, registro.getCedula());
                stmt.setInt(3, registro.getCodaplicativo());
                stmt.setString(4, registro.getEstatus());


                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    // Procesar los datos del ResultSet
                    RegistrarClientes sol = new RegistrarClientes();
                    sol.setIdcliente(rs.getInt("id_cliente"));
                    sol.setTipoidentificacion(rs.getString("tipo_identificacion"));
                    sol.setCedula(rs.getInt("cedula"));
                    sol.setNombres(rs.getString("nombres"));
                    sol.setApellidos(rs.getString("apellidos"));
                    sol.setEstado(rs.getString("estado"));
                    sol.setMunicipio(rs.getString("municipio"));
                    sol.setParroquia(rs.getString("parroquia"));
                    sol.setComunidad(rs.getString("comunidad"));
                    sol.setCalleavenida(rs.getString("calle_avenida"));
                    sol.setCasaapto(rs.getString("casa_apto"));
                    sol.setDireccion(rs.getString("direccion"));
                    sol.setTelefonoloc(rs.getString("telefono_loc"));
                    sol.setTelefonocel(rs.getString("telefono_cel"));
                    sol.setCorreo(rs.getString("correo"));
                    sol.setFechanacimiento(rs.getString("fecha_nacimiento"));
                    sol.setObservaciones(rs.getString("observaciones"));
                    sol.setEstatus(rs.getString("estatus"));
                    sol.setCredito(rs.getString("credito"));
                    sol.setFechacreacion(rs.getString("fecha_nacimiento"));
                    sol.setCodaplicativo(rs.getInt("cod_aplicativo"));
                    sol.setEdificio(rs.getString("edificio"));





                    resp.add(sol);
                }
                conn.commit();
                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }



    public List<programacionDto> consultar_programacion(Integer idprogrmacion) {
        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<programacionDto> citas = new ArrayList<>();
        List<programacionDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(
                    conexion.getUrl() + conexion.getDbname(),
                    conexion.getUser(),
                    conexion.getPass()
            );
            conn.setAutoCommit(false);

            if (conn != null) {
                CallableStatement stmt = conn.prepareCall("{? = call citas_medicas.consulta_programacion(?)}");
                stmt.registerOutParameter(1, Types.OTHER);
                if (idprogrmacion != null) {
                    stmt.setInt(2, idprogrmacion);
                } else {
                    stmt.setNull(2, Types.INTEGER);
                }

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1);

                while (rs.next()) {


                    programacionDto sol = new programacionDto();

                    sol.setProgramacionid(rs.getInt("programacion_id"));
                    sol.setMedicoid(rs.getInt("medico_id"));
                    sol.setDireccion(rs.getString("direccion"));
                    sol.setOficina(rs.getString("oficina"));
                    sol.setTelefono(rs.getString("telefono"));
                    sol.setContactowhatsapp(rs.getString("contactowhatsapp"));
                    sol.setCorreo(rs.getString("correo"));
                    sol.setFechaprogramacion(rs.getString("fecha_programacion"));
                    sol.setDia(rs.getString("dia"));
                    sol.setEstado(rs.getString("estado"));
                    sol.setHorainicio(rs.getString("hora_inicio"));
                    sol.setHorafin(rs.getString("hora_fin"));
                    sol.setAceptacitaweb(rs.getString("acepta_cita_dom"));
                    sol.setEspecialidadid(rs.getInt("especialidad_id"));





                    resp.add(sol);
                }

                rs.close();
                stmt.close();
                conn.close();
                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }



    public Resultado registrar_programacion(programacionDto entrada) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Resultado resp =   new Resultado();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call citas_medicas.crear_programacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.setInt(1, entrada.getMedicoid());
                stmt.setString(2, entrada.getDireccion());
                stmt.setString(3, entrada.getOficina());
                stmt.setString(4, entrada.getTelefono());
                stmt.setString(5, entrada.getContactowhatsapp());
                stmt.setString(6, entrada.getCorreo());
                stmt.setString(7, entrada.getFechaprogramacion());
                stmt.setString(8, entrada.getDia());
                stmt.setString(9, entrada.getEstado());
                stmt.setString(10, entrada.getHorainicio());
                stmt.setString(11, entrada.getHorafin());
                stmt.setString(12, entrada.getAceptacitaweb());
                stmt.setString(13, entrada.getAceptacitadom());
                stmt.setString(14, entrada.getFechamodificacion());
                stmt.setString(15, entrada.getUsuariomodificacion());
                stmt.setInt(16, entrada.getEspecialidadid());





                stmt.execute();
                Integer resultado = stmt.getInt(1);
                Resultado sol = new Resultado();
                sol.setSecuencia(resultado);
                // resp.((resultado));
                resp.setSecuencia((resultado));



                conn.commit();
                // rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }
    ///////////////sucursal
    public List<SucursalDto> ClinicA() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<SucursalDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call citas_medicas.consulta_clinicas()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    SucursalDto sol = new SucursalDto();
                    ObjectMapper mapper = new ObjectMapper();
                    //  RespConsSuscripDto obj = mapper.readValue("",RespConsSuscripDto.class);

                    sol.setCodigo_sucursal(rs.getInt("codigo_sucursal"));
                    sol.setDescripcion(rs.getString("descripcion"));





                    resp.add(sol);
                }
                ObjectMapper mapper = new ObjectMapper();

                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }


    public List<CitasBenefiDto> ConsultaBeneficiarioCitas(Integer cedula

    ) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        //RetornaDto resp = new RetornaDto();
        List<CitasBenefiDto> resp =   new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{call citas_medicas.consulta_beneficiario(?)}");
                //LocalDate localDate = LocalDate.of(fechaMovimiento);
                //  java.sql.Date sqlDate = java.sql.Date.valueOf(fechaMovimiento);

                stmt.registerOutParameter(1, Types.OTHER);
                stmt.setInt(1, cedula);



                stmt.execute();
                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {
                    CitasBenefiDto sol = new CitasBenefiDto();

                    sol.setContrato(rs.getString("contrato"));
                    sol.setContratante(rs.getString("contratante"));
                    sol.setCedula(rs.getInt("cedula"));
                    sol.setNombre(rs.getString("nombre"));
                    sol.setFechadesde(rs.getString("fecha_desde"));
                    sol.setFechahasta(rs.getString("fecha_hasta"));
                    sol.setSuscripcion(rs.getInt("suscripcion"));
                    sol.setEstatus(rs.getString("status"));



                    resp.add(sol);
                }



                conn.commit();

                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }



    public List<Estados> Estatuscitas() {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        List<Estados> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {



                CallableStatement stmt = conn.prepareCall("{? = call citas_medicas.consulta_estatus_citas()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {


                    Estados sol = new Estados();

                    sol.setCodigo(rs.getString("codigo"));
                    sol.setNombre(rs.getString("nombre"));


                    resp.add(sol);
                }


                rs.close();
                stmt.close();
                conn.close();


                System.out.println("Connection Exitosa");
            } else {
                System.out.println("Connection Fallida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return resp;
    }

}
