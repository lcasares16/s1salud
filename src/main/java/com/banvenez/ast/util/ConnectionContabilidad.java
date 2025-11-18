package com.banvenez.ast.util;

import com.banvenez.ast.dto.ConexionDto;
import com.banvenez.ast.dto.citas.programacionDto;
import com.banvenez.ast.dto.contabilidad.ConceptoAcreenciaDto;
import com.banvenez.ast.dto.contabilidad.DetopercontDto;
import com.banvenez.ast.dto.contabilidad.MaestroCtasDto;
import com.banvenez.ast.dto.contabilidad.OperacionContableDto;
import com.banvenez.ast.dto.farmacia.Estados;
import com.banvenez.ast.dto.farmacia.Resultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component

public class ConnectionContabilidad {
    Connection conn = null;
    ConexionDto conexion = new ConexionDto();

    public List<Estados> consultaTipocta() {

        List<Estados> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.consulta_tipocta()}");
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



    public List<Estados> consultaTipomov() {

        List<Estados> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.consulta_tipomov()}");
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

    public List<Estados> consultaTipooper() {

        List<Estados> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.consulta_tipoper()}");
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


    public List<Estados> consultaCompania() {

        List<Estados> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.consulta_compania()}");
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

    public List<Estados> naturaleza() {

        List<Estados> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.consulta_naturaleza()}");
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


    public List<MaestroCtasDto> maestrocuentas() {

        List<MaestroCtasDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.cunsulta_cuentas()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {



                    MaestroCtasDto sol = new MaestroCtasDto();

                    sol.setIdecta(rs.getString("idecta"));
                    sol.setCodcia(rs.getString("codcia"));
                    sol.setTipcta(rs.getString("tipcta"));

                    sol.setTipmov(rs.getString("tipmov"));
                    sol.setStscta(rs.getString("stscta"));

                    sol.setCta1(rs.getString("cta1"));
                    sol.setCta2(rs.getString("cta2"));
                    sol.setCta3(rs.getString("cta3"));
                    sol.setCta4(rs.getString("cta4"));
                    sol.setCta5(rs.getString("cta5"));
                    sol.setCta6(rs.getString("cta6"));
                    sol.setCta7(rs.getString("cta7"));
                    sol.setCta8(rs.getString("cta8"));
                    sol.setCta9(rs.getString("cta9"));
                    sol.setCta10(rs.getString("cta10"));
                    sol.setZoncta(rs.getString("zoncta"));
                    sol.setAuxcta(rs.getString("auxcta"));
                    sol.setNomcta(rs.getString("nomcta"));
                    sol.setCodmoneda(rs.getString("codmoneda"));



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


    public List<MaestroCtasDto> maestrodetalle(MaestroCtasDto registro ) {

        List<MaestroCtasDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.cuentas_detalle()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                stmt.setString(1, registro.getCta1());
                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {



                    MaestroCtasDto sol = new MaestroCtasDto();

                    sol.setIdecta(rs.getString("idecta"));
                    sol.setCodcia(rs.getString("codcia"));
                    sol.setTipcta(rs.getString("tipcta"));

                    sol.setTipmov(rs.getString("tipmov"));
                    sol.setStscta(rs.getString("stscta"));

                    sol.setCta1(rs.getString("cta1"));
                    sol.setCta2(rs.getString("cta2"));
                    sol.setCta3(rs.getString("cta3"));
                    sol.setCta4(rs.getString("cta4"));
                    sol.setCta5(rs.getString("cta5"));
                    sol.setCta6(rs.getString("cta6"));
                    sol.setCta7(rs.getString("cta7"));
                    sol.setCta8(rs.getString("cta8"));
                    sol.setCta9(rs.getString("cta9"));
                    sol.setCta10(rs.getString("cta10"));
                    sol.setZoncta(rs.getString("zoncta"));
                    sol.setAuxcta(rs.getString("auxcta"));
                    sol.setNomcta(rs.getString("nomcta"));
                    sol.setCodmoneda(rs.getString("codmoneda"));



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

    public Resultado registraroperconta(DetopercontDto entrada) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Resultado resp =   new Resultado();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call contabilidad.registrar_detalle_oper(?,?)}");
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, entrada.getDescripcion());
                stmt.setString(2, entrada.getCodigo());






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

    public List<DetopercontDto> opercontab() {

        List<DetopercontDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.detalle_operaciones()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {



                    DetopercontDto sol = new DetopercontDto();

                    sol.setCodoper(rs.getString("codoper"));
                    sol.setDescripcion(rs.getString("descripcion"));
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


    public Resultado registrarcuentas(MaestroCtasDto entrada) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Resultado resp =   new Resultado();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call contabilidad.crear_cuentas(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                stmt.registerOutParameter(1, Types.VARCHAR);
                stmt.setString(1, entrada.getCodcia());
                stmt.setString(2, entrada.getTipcta());
                stmt.setString(3, entrada.getTipmov());
                stmt.setString(4, entrada.getStscta());
                stmt.setString(5, entrada.getCta1());
                stmt.setString(6, entrada.getCta2());
                stmt.setString(7, entrada.getCta3());
                stmt.setString(8, entrada.getCta4());
                stmt.setString(9, entrada.getCta5());
                stmt.setString(10, entrada.getCta6());
                stmt.setString(11, entrada.getCta7());
                stmt.setString(12, entrada.getCta8());
                stmt.setString(13, entrada.getCta9());
                stmt.setString(14, entrada.getCta10());
                stmt.setString(15, entrada.getZoncta());
                stmt.setString(16, entrada.getAuxcta());
                stmt.setString(17, entrada.getNomcta());
                stmt.setString(18, entrada.getCodmoneda());





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

    public List<OperacionContableDto> opercontables() {

        List<OperacionContableDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.operaciones_contables()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {



                    OperacionContableDto sol = new OperacionContableDto();

                    sol.setTipomoneda(rs.getString("tipomoneda"));
                    sol.setRegistrocesion(rs.getString("registrocesion"));
                    sol.setCta6(rs.getString("cta6"));
                    sol.setTipoaux(rs.getString("tipoaux"));
                    sol.setFacultativo(rs.getString("facultativo"));
                    sol.setTipointer(rs.getString("tipointer"));
                    sol.setCodcia(rs.getString("codcia"));
                    sol.setCodoper(rs.getString("codoper"));
                    sol.setCodgrupocpto(rs.getString("codgrupocpto"));
                    sol.setCodcpto(rs.getString("codcpto"));
                    sol.setNumoper(rs.getString("numoper"));
                    sol.setCodramo(rs.getString("codramo"));
                    sol.setCta1(rs.getString("cta1"));
                    sol.setCta2(rs.getString("cta2"));
                    sol.setCta3(rs.getString("cta3"));
                    sol.setCta4(rs.getString("cta4"));
                    sol.setCta5(rs.getString("cta5"));
                    sol.setCta7(rs.getString("cta7"));
                    sol.setDbcr(rs.getString("dbcr"));
                    sol.setPrimacomision(rs.getString("primacomision"));
                    sol.setCoaseguro(rs.getString("coaseguro"));
                    sol.setClaseacepriesgo(rs.getString("claseacepriesgo"));
                    sol.setCentrocosto(rs.getString("centrocosto"));
                    sol.setIndtipomon(rs.getString("indtipomon"));
                    sol.setCta8(rs.getString("cta8"));
                    sol.setCta9(rs.getString("cta9"));
                    sol.setCta10(rs.getString("cta10"));
                    sol.setAuxcta(rs.getString("auxcta"));
                    sol.setIndgprc(rs.getString("indgprc"));



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


    public List<ConceptoAcreenciaDto> concetoacreencia() {

        List<ConceptoAcreenciaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.concepto_acreencia()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {



                    ConceptoAcreenciaDto sol = new ConceptoAcreenciaDto();

                    sol.setCodGrupoAcre(rs.getString("codgrupoacre"));
                    sol.setCodCptoAcre(rs.getString("codcptoacre"));
                    sol.setDescCptoAcre(rs.getString("desccptoacre"));
                    sol.setNatCptoAcre(rs.getString("natcptoacre"));
                    sol.setIndTipoCpto(rs.getString("indtipocpto"));
                    sol.setPorcCptoAcre(rs.getDouble("porccptoacre"));
                    sol.setMtoCptoAcre(rs.getDouble("mtocptoacre"));
                    sol.setIndAuto(rs.getString("indauto"));
                    sol.setIndGenOper(rs.getString("indgenoper"));
                    sol.setTipoId(rs.getString("tipoid"));
                    sol.setNumId(rs.getString("numid"));
                    sol.setDvId(rs.getString("dvid"));
                    sol.setCodCptoAcreCoa(rs.getString("codcptoacrecoa"));
                    sol.setCodGrupoAcreCoa(rs.getString("codgrupoacrecoa"));
                    sol.setIndGtoMan(rs.getString("indgtoman"));
                    sol.setIndImpGtoMan(rs.getString("indimpgtoman"));
                    sol.setIndCptoPServicio(rs.getString("indcptopservicio"));
                    sol.setIndCptoIServicio(rs.getString("indcptoiservicio"));



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
