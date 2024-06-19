package com.banvenez.ast.util;

import com.banvenez.ast.action.ServicioDscStoAction;
import com.banvenez.ast.dao.IntranetcorpDao;
import com.banvenez.ast.dao.SorteoInmDao;
import com.banvenez.ast.dto.Mappers.CronoObjMapper;
import com.banvenez.ast.dto.Mappers.carnetObjMapper;
import com.banvenez.ast.dto.Sorteo.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import javafx.scene.input.KeyCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.net.ssl.SSLContext;
import java.sql.Blob;
import java.util.List;

@Service
@Slf4j
public class CronogramaHttp {

    ConexionesServicios servicios = new ConexionesServicios();
    private IntranetcorpDao daoIntranet = new IntranetcorpDao();

    private RepuestaActCronDTO actualizaCronograma = new RepuestaActCronDTO();

    private RepuestaActCronDTO cierraSorteo = new RepuestaActCronDTO();

    private RepuestaActCronDTO cuentaCrono = new RepuestaActCronDTO();

    public  Integer vVronograma;

    public String jsonEnviado;

    public String jsonRespuesta;
    SorteoInmDao daoMultiInm = new SorteoInmDao();

    private DatosPagoPDto sorteoCandidatos = new DatosPagoPDto();
    private SorteoInmPDTO consultaSorteo = new SorteoInmPDTO();

    private RespuestaGanDTO consultaGan = new RespuestaGanDTO();

    private CronogramaPDTO consultaParametro = new CronogramaPDTO();


    public SalidaJsonDscDto CronogramaEntrada(String horaCronograma){

        SalidaJsonDscDto resp = new SalidaJsonDscDto();

        try {
            

            consultaParametro = daoMultiInm.consultarCronogramaSto(horaCronograma);

            for (JsonCronogramaDTO  cronograma:consultaParametro.getCronoLst() ){
                vVronograma = cronograma.getIdSorteoCronogramaPk();
                JsonDscDTO Json3 = new JsonDscDTO();
                Json3.setDatePay(cronograma.getDatePay());
                Json3.setModalityCode(cronograma.getModalityCode());
                Json3.setProductCode(cronograma.getProductCode());
                Json3.setTimePayInit(cronograma.getTimePayInit());
                Json3.setTimePayEnd(cronograma.getTimePayEnd());


               SalidaJsonDscDto DscMetodo = this.obtenerDscNew(Json3);
               if (DscMetodo.getData() == null){

                    actualizaCronograma = daoMultiInm.actualizaCronogramaSto(cronograma.getIdSorteoCronogramaPk(), "C");

                } ;


                // idCronograma
                log.info("Nro Cronograma Actualizado " + actualizaCronograma);

                log.info(cronograma.getIdSorteoCronogramaPk().toString());


                log.info("Json de salida Dsc" + DscMetodo);


                log.info("Json de salida Dsc" + " " +

                 cronograma.getDatePay() + "-" +
                 cronograma.getModalityCode() + "-" +
                 cronograma.getProductCode() + "-" +
                 cronograma.getTimePayInit() + "-" +
                 cronograma.getTimePayEnd() );

                log.info("Json: " + Json3);


               //

            }

            cuentaCrono = daoMultiInm.cuentaCronograma(consultaParametro.getCronoParLst().get(0).id_sorteo);
            if (cuentaCrono.pResultado == 0) {
                cierraSorteo = daoMultiInm.cierraSorteo(consultaParametro.getCronoParLst().get(0).id_sorteo);
            }
        }catch (Exception e){
            log.error("ServiciosHttp:peopleTraba");
            log.error(e.getMessage());
        }

        return  resp;
    }


    public SalidaJsonDscDto obtenerDscNew(JsonDscDTO jsonEntrada) {
        log.info(String.valueOf(jsonEntrada));
        SalidaJsonDscDto resp = new SalidaJsonDscDto();
        ServiciosHttp serPic = new ServiciosHttp();
        ServicioTrabHttp serPeople = new ServicioTrabHttp();
        ServicioHttpPush serPush = new ServicioHttpPush();

        try {
            TrustStrategy acceptingTrustStrategy = new TrustSelfSignedStrategy();
            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

            String endPoint = daoIntranet.obtenerParametros(Constantes.contextoParametro, Constantes.contextoDscWs, Constantes.parametroDscWs);

            HttpPost post = new HttpPost(endPoint + "getTrasactionSorteo");


            try {
                StringEntity data = new StringEntity(new Gson().toJson(jsonEntrada));
                log.info("json => " + data);
                post.setHeader("Content-Type", "application/json");
                post.setEntity(data);
                log.info(data.toString());

            } catch (Exception e) {
                log.error("fallo envio de respuesta => " + e.getMessage());
            }

            try {
                CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
                HttpResponse response = httpClient.execute(post);
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                log.info("Json de Salida " + responseString);

                Gson gson = new Gson();

                resp = gson.fromJson(responseString, SalidaJsonDscDto.class);
                ObjectMapper mapper = new ObjectMapper();

                SalidaJsonDscDto obj = mapper.readValue(responseString, SalidaJsonDscDto.class);



                resp = new SalidaJsonDscDto();


                for (data  candidatos:obj.getData()) {
                    // condiciones  dao

                    Double monto = (candidatos.getAmount() + candidatos.getAmountCommission());
                    double roundOff = Math.round(monto * 100.0) / 100.0;

//                    String str = Double.toString(roundOff);
//                    str = str.replace(".",",");
//                    double doble = Double.parseDouble(str);

                    //double roundOff = Math.round(doble * 100.0) / 100.0;



                    sorteoCandidatos =  daoMultiInm.consultarParametro(     roundOff,
                                                                            candidatos.getChannelCode(),
                                                                            candidatos.getBankOrigin(),
                                                                            candidatos.getBankDestination(),
                                                                            "V",
                                                                            candidatos.getCustomerOrigin(),
                                                                            candidatos.getAccountOrigin(),
                                                                            candidatos.getReferenc()
                    );



                    log.info("Envio de informacion BD " + sorteoCandidatos);





                    if(sorteoCandidatos.getPagosLst() != null)  {

                        //Consulta Sorteos

                        consultaSorteo =  daoMultiInm.consultarSorteo();

                        Integer numeroSorteo = consultaSorteo.getSorteoLst().get(0).getId_sorteo_pk();

                        //Consulta si el ganador esta en el mismo sorteo de ser asi no puede participar

                        consultaGan = daoMultiInm.consultaGanador(numeroSorteo,candidatos.getCustomerOrigin());

                        RespuestaGanDTO valida = new RespuestaGanDTO();
                        valida.setP_resultado(consultaGan.getP_resultado());

                        EntradaTrab traba = new EntradaTrab();
                        traba.setCedula(Integer.toString(candidatos.getCustomerOrigin()));

                        //Consulta si es trabajador
                        log.info("Consulta si es empleado   " + numeroSorteo  );

                        ResultadoTrab respPeople = serPeople.peopleTraba(traba);





                        // /*&&  respPeople.getDatosTrab() == null*/
                        //&&  respPeople.getDatosTrab().getEmplid() == null

                        if(consultaGan.getP_resultado() == 1 &&  respPeople.getDatosTrab().getEmplid() == null){

                            log.info("Si hay ganador y se envia pago del sorteo Nro.   " + numeroSorteo  );


                            RespuestPCIDto respPic = serPic.pagoPic(sorteoCandidatos.getPagosLst().get(0));



                            if(respPic.getCode() == 1000){


                                jsonEnviado = sorteoCandidatos.toString();
                                jsonRespuesta = respPic.toString();


                                log.info("Se inserta ganador del sorteo  " + numeroSorteo  );

                                sorteoCandidatos =  daoMultiInm.insertaGanador
                                        (candidatos.getAmount(),
                                                candidatos.getChannelCode(),
                                                candidatos.getBankOrigin(),
                                                candidatos.getBankDestination(),
                                                "V",
                                                candidatos.getCustomerOrigin(),
                                                candidatos.getAccountOrigin(),
                                                vVronograma,
                                                candidatos.getReferenc(),
                                                candidatos.getAmountCommission() ,
                                                jsonEnviado,
                                                jsonRespuesta

                                        );

                                log.info("Hubo Ganador del sorteo   "+ numeroSorteo + candidatos.getCustomerOrigin());

                                actualizaCronograma = daoMultiInm.actualizaCronogramaSto(vVronograma, "P");
                                //Envio de Mensaje Push
                                EnvioPushDTO push = new EnvioPushDTO();
                                push.setMensaje("¡Felicidades recibiste un abono de Promociones BDV!" );
                                push.setTitulo("Ganador BDV sorteo: " +numeroSorteo);
                                push.setCedula(Integer.toString(candidatos.getCustomerOrigin()));

                                ResulatdoPushDTO resppush = serPush.PushMensaje(push);

                                log.info("Respuesta Push: " + resppush.getEstatus());

                                break;

                            }

                        }else {
                            actualizaCronograma = daoMultiInm.actualizaCronogramaSto(vVronograma, "C");
                        }

                    }else {
                        actualizaCronograma = daoMultiInm.actualizaCronogramaSto(vVronograma, "C");
                    }

                }
                resp = gson.fromJson(responseString, SalidaJsonDscDto.class);
                log.info("Regsitros Json de salida " + resp);



            } catch (Exception e) {
                log.error("fallo respuesta => " + e.getMessage());
            }

        } catch (Exception e) {
            log.error("Excepcion en la clase y metodo " + ServicioDscStoAction.class.getName() + " obtenerMenu ");
            log.error("Mensaje => " + e.getMessage());
            resp = new SalidaJsonDscDto();
            resp.setCode(99);
            resp.setMessage("fail");



        }
        return resp;
    }



}
