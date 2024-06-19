package com.banvenez.ast.action;


import com.banvenez.ast.dao.IntranetcorpDao;
import com.banvenez.ast.dao.SorteoInmDao;
import com.banvenez.ast.dto.Sorteo.DataPagosDto;
import com.banvenez.ast.dto.Sorteo.JsonDscDTO;
import com.banvenez.ast.dto.Sorteo.RespuestPCIDto;
import com.banvenez.ast.dto.Sorteo.SalidaJsonDscDto;
import com.banvenez.ast.util.ConexionesServicios;
import com.banvenez.ast.util.Constantes;
import com.banvenez.ast.util.ServicosHttps;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
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
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLContext;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping(value = "/", headers = "Accept=application/json", method = {RequestMethod.POST, RequestMethod.GET})
public class ServicioPciStoAction {

    private List<RespuestPCIDto> parameLstt = new ArrayList<RespuestPCIDto>();
    SorteoInmDao daoMultiInm = new SorteoInmDao();


    @Autowired
    ConexionesServicios servicios;

    @Autowired
    private IntranetcorpDao daoIntranet;

    //@Autowired
    //DiremDao daoDirem;

    @Autowired
    ServicosHttps serviciosHttps;


    @PostMapping(value = "/obtenerPci")
    public RespuestPCIDto obtenerPci(@RequestBody DataPagosDto jsonEntrada) {
        log.info(String.valueOf(jsonEntrada));
        RespuestPCIDto resp = new RespuestPCIDto();
        try {
            TrustStrategy acceptingTrustStrategy = new TrustSelfSignedStrategy();
            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

            String endPoint = daoIntranet.obtenerParametros(Constantes.contextoParametro, Constantes.contextoPciWs, Constantes.parametroPciWs);

            HttpPost post = new HttpPost(endPoint + "channeltransfersFSR4");

/// dcs
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
                resp = gson.fromJson(responseString, RespuestPCIDto.class);

                ObjectMapper mapper = new ObjectMapper();

                RespuestPCIDto obj = mapper.readValue(responseString, RespuestPCIDto.class);

                // SalidaJsonDscDto obj = new SalidaJsonDscDto();

//                System.out.println(obj.getCode());
//                System.out.println(obj.getData().get(0).getAmount());
//                System.out.println(obj.getData().get(1).getChannelCode());
//                System.out.println(obj.getData().get(2).getBankOrigin());
//                System.out.println(obj.getData().get(3).getBankDestination());
//                System.out.println(obj.getData().get(4).getCustomerOrigin());
//                System.out.println(obj.getData().get(5).getAccountOrigin());
//                System.out.println(obj.getData().get(6).getReferenc());

            } catch (Exception e) {
                log.error("fallo respuesta => " + e.getMessage());
            }


        } catch (Exception e) {
            log.error("Excepcion en la clase y metodo " + ServicioPciStoAction.class.getName() + " obtenerMenu ");
            log.error("Mensaje => " + e.getMessage());
            resp = new RespuestPCIDto();
            resp.setCode(99);
            resp.setMessage("fail");



        }
        return resp;
    }

}
