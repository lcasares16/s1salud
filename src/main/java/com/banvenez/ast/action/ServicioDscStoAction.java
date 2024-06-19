package com.banvenez.ast.action;


import com.banvenez.ast.dao.IntranetcorpDao;
import com.banvenez.ast.dao.SorteoInmDao;
import com.banvenez.ast.dto.Sorteo.*;
import com.banvenez.ast.util.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping(value = "/", headers = "Accept=application/json", method = {RequestMethod.POST, RequestMethod.GET})
public class ServicioDscStoAction {




    private List<SalidaJsonDscDto> parameLstt = new ArrayList<SalidaJsonDscDto>();

    SorteoInmDao daoMultiInm = new SorteoInmDao();

    private DatosPagoPDto sorteoCandidatos = new DatosPagoPDto();
    private SorteoInmPDTO consultaSorteo = new SorteoInmPDTO();

    private String jsonEnviado;

    private String jsonRespuesta;

    private RespuestaGanDTO consultaGan = new RespuestaGanDTO();


    List<DataPagosDto> pagos = new ArrayList<DataPagosDto>();


    @Autowired
    ConexionesServicios servicios;

    @Autowired
    private IntranetcorpDao daoIntranet;

    //@Autowired
    //DiremDao daoDirem;

    @Autowired
    ServicosHttps serviciosHttps;

    @PostMapping(value = "/obtenerDsc")
    public SalidaJsonDscDto obtenerDsc(@RequestBody JsonDscDTO jsonEntrada) {
        log.info(String.valueOf(jsonEntrada));
        SalidaJsonDscDto resp = new SalidaJsonDscDto();
        ServiciosHttp serPic = new ServiciosHttp();
        ServicioTrabHttp serPeople = new ServicioTrabHttp();
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
                    sorteoCandidatos =  daoMultiInm.consultarParametro(candidatos.getAmount(),
                                                                      candidatos.getChannelCode(),
                                                                      candidatos.getBankOrigin(),
                                                                      candidatos.getBankDestination(),
                                                                      "V",
                                                                      candidatos.getCustomerOrigin(),
                                                                      candidatos.getAccountOrigin(),
                                                                      candidatos.getReferenc()
                                                                     );



                    log.info("Envio de informacion BD " + sorteoCandidatos);





                    if(sorteoCandidatos.getPagosLst() != null) {

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
                             ResultadoTrab respPeople = serPeople.peopleTraba(traba);
                             // /*&&  respPeople.getDatosTrab() == null*/

                                if(consultaGan.getP_resultado() == 1 &&  respPeople.getDatosTrab() == null){

                                   RespuestPCIDto respPic = serPic.pagoPic(sorteoCandidatos.getPagosLst().get(0));

                                   if(respPic.getCode() == 1000){
                                       jsonEnviado = sorteoCandidatos.toString();
                                       jsonRespuesta = respPic.toString();


                                       sorteoCandidatos =  daoMultiInm.insertaGanador
                                               (candidatos.getAmount(),
                                                       candidatos.getChannelCode(),
                                                       candidatos.getBankOrigin(),
                                                       candidatos.getBankDestination(),
                                                       "V",
                                                       candidatos.getCustomerOrigin(),
                                                       candidatos.getAccountOrigin(),
                                                       1,
                                                       candidatos.getReferenc(),
                                                       candidatos.getAmountCommission(),
                                                       jsonEnviado,
                                                       jsonRespuesta
                                               );
                                       log.info("Hubo Ganador del sorteo   "+ numeroSorteo + candidatos.getCustomerOrigin());

                                       break;

                                   }

                                }

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
