package com.banvenez.ast.util;

import com.banvenez.ast.dto.ConexionDto;
import com.banvenez.ast.dto.citas.programacionDto;
import com.banvenez.ast.dto.contabilidad.*;
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


    public Resultado registrarasientos(AsientoContableDto entrada) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Resultado resp =   new Resultado();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call contabilidad.fn_generar_asiento_automatico(?,?,?,?,?,?)}");
                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.setString(1, entrada.getIdOperacion());
                stmt.setInt(2, entrada.getIdorigen());
                stmt.setString(3, entrada.getFechaAsiento());
                stmt.setString(4, entrada.getIdUsuario());
                stmt.setString(5, entrada.getCodcia());
                stmt.setDouble(6, entrada.getMonto());






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



    public Resultado registracreencias(AcreenciasDto entrada) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Resultado resp =   new Resultado();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call contabilidad.fn_registrar_acreencia(?,?,?,?,?,?)}");
                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.setString(1, entrada.getStsacre());
                stmt.setString(2, entrada.getTipoacre());
                stmt.setString(3, entrada.getCodmoneda());
                stmt.setDouble(4, entrada.getMtoacrelocal());
                stmt.setInt(5, entrada.getNumid());
                stmt.setString(6, entrada.getTextacre());






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

    public Resultado registrobligacion(ObligacionDto entrada) {

        Connection conn = null;
        ConexionDto conexion = new ConexionDto();
        Resultado resp =   new Resultado();

        try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {

                CallableStatement stmt = conn.prepareCall("{call contabilidad.fn_registrar_obligacion(?,?,?,?,?,?)}");
                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.setString(1, entrada.getTipooblig());
                stmt.setString(2, entrada.getStsoblig());
                stmt.setString(3, entrada.getCodmoneda());
                stmt.setDouble(4, entrada.getMtonetoobliglocal());
                stmt.setInt(5, entrada.getNumid());
                stmt.setString(6, entrada.getTextoblig());






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



    public List<MovCuentaDto> consultamovimientos() {

        List<MovCuentaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.consulta_movimientos()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet

                while (rs.next()) {



                    MovCuentaDto sol = new MovCuentaDto();

                    sol.setIdecta(rs.getString("idecta"));
                    sol.setFecmov(rs.getString("fecmov"));
                    sol.setNcompr(rs.getInt("ncompr"));
                    sol.setCodplani(rs.getString("codplani"));
                    sol.setNummov(rs.getInt("nummov"));
                    sol.setCodcia(rs.getString("codcia"));
                    sol.setCta1(rs.getString("cta1"));
                    sol.setCta2(rs.getString("cta2"));
                    sol.setCta3(rs.getString("cta3"));
                    sol.setCta4(rs.getString("cta4"));
                    sol.setCta5(rs.getString("cta5"));
                    sol.setCta6(rs.getString("cta6"));
                    sol.setCta7(rs.getString("cta7"));
                    sol.setZoncta(rs.getString("zoncta"));
                    sol.setAuxcta(rs.getString("auxcta"));
                    sol.setDetmov(rs.getString("detmov"));
                    sol.setTipmov(rs.getString("tipmov"));
                    sol.setMoncnt(rs.getDouble("moncnt"));
                    sol.setZoncnt(rs.getString("zoncnt"));
                    sol.setRecibo(rs.getInt("recibo"));
                    sol.setTipdoc(rs.getString("tipdoc"));
                    sol.setNumdoc(rs.getString("numdoc"));
                    sol.setFecconci(rs.getString("fecconci"));
                    sol.setIngcaja(rs.getString("ingcaja"));
                    sol.setCorasien(rs.getDouble("corasien"));
                    sol.setDetcta2(rs.getString("detcta2"));
                    sol.setCoractiv(rs.getInt("coractiv"));
                    sol.setCta8(rs.getString("cta8"));
                    sol.setCta9(rs.getString("cta9"));
                    sol.setCta10(rs.getString("cta10"));
                    sol.setTipcomp(rs.getString("tipcomp"));
                    sol.setIndauto(rs.getString("indauto"));
                    sol.setFecinc(rs.getString("fecinc"));
                    sol.setNumrel(rs.getInt("numrel"));
                    sol.setTipodoc(rs.getString("tipodoc"));
                    sol.setConciliado(rs.getString("conciliado"));
                    sol.setIdeop(rs.getInt("ideop"));
                    sol.setMonedaoficialBsf(rs.getString("moneda_oficial_bsf"));
                    sol.setNcompror(rs.getInt("ncompr_or"));



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


    public List<AcreenciasDto> consultaacreencia() {

        List<AcreenciasDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.consulta_acreencia()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type

                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet




                while (rs.next()) {



                    AcreenciasDto sol = new AcreenciasDto();

                    sol.setNumacre(rs.getInt("numacre"));
                    sol.setStsacre(rs.getString("stsacre"));
                    sol.setFecsts(rs.getString("fecsts"));
                    sol.setTipoacre(rs.getString("tipoacre"));
                    sol.setCodmoneda(rs.getString("codmoneda"));
                    sol.setMtoacrelocal(rs.getDouble("mtoacrelocal"));
                    sol.setMtoacremoneda(rs.getDouble("mtoacremoneda"));
                    sol.setFecvencacre(rs.getString("fecvencacre"));
                    sol.setSldoacrelocal(rs.getDouble("sldoacrelocal"));
                    sol.setSldoacremoneda(rs.getDouble("sldoacremoneda"));
                    sol.setCodinterlider(rs.getString("codinterlider"));
                    sol.setTipoid(rs.getString("tipoid"));
                    sol.setNumid(rs.getInt("numid"));
                    sol.setDvid(rs.getString("dvid"));
                    sol.setIdefact(rs.getInt("idefact"));
                    sol.setDptoemi(rs.getString("dptoemi"));
                    sol.setIndtarj(rs.getString("indtarj"));
                    sol.setIndgenrec(rs.getString("indgenrec"));
                    sol.setTextacre(rs.getString("textacre"));
                    sol.setSeriecttorea(rs.getInt("seriecttorea"));


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

    public List<DetalleAcreenciaDto> detalleacreencia(DetalleAcreenciaDto registro) {

        List<DetalleAcreenciaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.consulta_det_acreencia()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type
                if (registro.getNumacre() != null) {
                    stmt.setInt(1, registro.getNumacre());
                } else {
                    stmt.setNull(1, Types.INTEGER);
                }


                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet



                while (rs.next()) {



                    DetalleAcreenciaDto sol = new DetalleAcreenciaDto();

                    sol.setNumacre(rs.getInt("numacre"));
                    sol.setNumdetacre(rs.getInt("numdetacre"));
                    sol.setCodgrupoacre(rs.getString("codgrupoacre"));
                    sol.setCodcptoacre(rs.getString("codcptoacre"));
                    sol.setCodmoneda(rs.getString("codmoneda"));
                    sol.setMtodetacrelocal(rs.getDouble("mtodetacrelocal"));
                    sol.setNatcptoacre(rs.getString("natcptoacre"));
                    sol.setMtocptoacre(rs.getDouble("mtocptoacre"));
                    sol.setPorccptoacre(rs.getDouble("porccptoacre"));
                    sol.setMtodetacremoneda(rs.getDouble("mtodetacremoneda"));
                    sol.setSldodetacrelocal(rs.getDouble("sldodetacrelocal"));
                    sol.setSldodetacremoneda(rs.getDouble("sldodetacremoneda"));


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


    public List<ParamCptoCtaDto> parametrosconcepto() {

        List<ParamCptoCtaDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.consulta_param_cpto()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet



                while (rs.next()) {



                    ParamCptoCtaDto sol = new ParamCptoCtaDto();

                    sol.setCodcptoacre(rs.getString("codcptoacre"));
                    sol.setIdecta(rs.getString("idecta"));
                    sol.setTipmovDef(rs.getString("tipmov_def"));
                    sol.setCodcia(rs.getString("codcia"));


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

    public List<AsientoContableDto> asientoscontable() {

        List<AsientoContableDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.consulta_asientos_contables()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet



                while (rs.next()) {



                    AsientoContableDto sol = new AsientoContableDto();

                    sol.setIdAsiento(rs.getInt("id_asiento"));
                    sol.setFechaAsiento(rs.getString("fecha_asiento"));
                    sol.setDescripcion(rs.getString("descripcion"));
                    sol.setIdOperacion(rs.getString("id_operacion"));
                    sol.setIdUsuario(rs.getString("id_usuario"));
                    sol.setEstado(rs.getString("estado"));


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


    public List<DetalleAsientoDto> asientosdetalle() {

        List<DetalleAsientoDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.consulta_asientos_detalles()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet



                while (rs.next()) {



                    DetalleAsientoDto sol = new DetalleAsientoDto();

                    sol.setIdDetalle(rs.getInt("id_detalle"));
                    sol.setIdAsiento(rs.getInt("id_asiento"));
                    sol.setIdcta(rs.getString("idcta"));
                    sol.setTipoMov(rs.getString("tipo_mov"));
                    sol.setMonto(rs.getDouble("monto"));
                    sol.setReferencia(rs.getString("referencia"));


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

    public List<ObligacionDto> obligacion() {

        List<ObligacionDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.consulta_obligacion()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet



                while (rs.next()) {



                    ObligacionDto sol = new ObligacionDto();

                    sol.setNumoblig(rs.getInt("numoblig"));
                    sol.setTipoid(rs.getString("tipoid"));
                    sol.setNumid(rs.getInt("numid"));
                    sol.setDvid(rs.getString("dvid"));
                    sol.setTipooblig(rs.getString("tipooblig"));
                    sol.setStsoblig(rs.getString("stsoblig"));
                    sol.setFecsts(rs.getString("fecsts"));          // ahora String
                    sol.setFecgtiapago(rs.getString("fecgtiapago")); // ahora String
                    sol.setTextoblig(rs.getString("textoblig"));
                    sol.setCodmoneda(rs.getString("codmoneda"));
                    sol.setNumprto(rs.getString("numprto"));
                    sol.setNumfracprto(rs.getInt("numfracprto"));
                    sol.setMtonetoobliglocal(rs.getDouble("mtonetoobliglocal"));
                    sol.setMtobrutoobligmoneda(rs.getDouble("mtobrutoobligmoneda"));
                    sol.setMtonetoobligmoneda(rs.getDouble("mtonetoobligmoneda"));
                    sol.setMtobrutoobliglocal(rs.getDouble("mtobrutoobliglocal"));
                    sol.setDptoemi(rs.getString("dptoemi"));
                    sol.setSldoobliglocal(rs.getDouble("sldoobliglocal"));
                    sol.setSldoobligmoneda(rs.getDouble("sldoobligmoneda"));
                    sol.setIdesin(rs.getInt("idesin"));
                    sol.setCodinterlider(rs.getString("codinterlider"));
                    sol.setNumreling(rs.getInt("numreling"));
                    sol.setNumoper(rs.getInt("numoper"));
                    sol.setNumrelingpro(rs.getInt("numrelingpro"));
                    sol.setNotacreditofiscal(rs.getInt("notacreditofiscal"));
                    sol.setNumobligant(rs.getInt("numobligant"));
                    sol.setFecanul(rs.getString("fecanul")); // ahora String
                    sol.setCodcobrador(rs.getString("codcobrador"));
                    sol.setEmitecheque(rs.getString("emitecheque"));
                    sol.setIdeimpchq(rs.getInt("ideimpchq"));
                    sol.setDvidadic(rs.getString("dvidadic"));
                    sol.setIderecup(rs.getInt("iderecup"));
                    sol.setNumidadic(rs.getInt("numidadic"));
                    sol.setTipoidadic(rs.getString("tipoidadic"));
                    sol.setVinculo(rs.getString("vinculo"));
                    sol.setNosolicitud(rs.getInt("nosolicitud"));
                    sol.setClaseobli(rs.getString("claseobli"));
                    sol.setTramite(rs.getString("tramite"));
                    sol.setSeriecttorea(rs.getInt("seriecttorea"));


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

    public List<DetalleObligacionDto> obligaciondetalle() {

        List<DetalleObligacionDto> resp =   new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(conexion.getUrl() + conexion.getDbname(), conexion.getUser(), conexion.getPass());
            conn.setAutoCommit(false);
            if (conn != null) {


                CallableStatement stmt = conn.prepareCall("{? = call contabilidad.consulta_obligacion_det()}");
                stmt.registerOutParameter(1, Types.OTHER); // Set the output parameter type



                stmt.execute();

                ResultSet rs = (ResultSet) stmt.getObject(1); // Obtener el resultado como ResultSet



                while (rs.next()) {



                    DetalleObligacionDto sol = new DetalleObligacionDto();

                    sol.setNumoblig(rs.getInt("numoblig"));
                    sol.setNumdetoblig(rs.getInt("numdetoblig"));
                    sol.setCodgrupooblig(rs.getString("codgrupooblig"));
                    sol.setCodcptooblig(rs.getString("codcptooblig"));
                    sol.setCodmoneda(rs.getString("codmoneda"));
                    sol.setMtodetobliglocal(rs.getDouble("mtodetobliglocal"));
                    sol.setNatcptooblig(rs.getString("natcptooblig"));
                    sol.setMtodetobligmoneda(rs.getDouble("mtodetobligmoneda"));
                    sol.setPorccptooblig(rs.getDouble("porccptooblig"));
                    sol.setSldodetobliglocal(rs.getDouble("sldodetobliglocal"));
                    sol.setSldodetobligmoneda(rs.getDouble("sldodetobligmoneda"));


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
