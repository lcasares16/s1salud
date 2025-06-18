package com.banvenez.ast;

import com.banvenez.ast.dto.Sorteo.ResultadoTrab;
import com.banvenez.ast.dto.administracion.RespuesatTasaBcvDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLContext;

@Service
@Slf4j
public class ServicioHttpBcv {

    // ConexionesServicios servicios = new ConexionesServicios();








    public RespuesatTasaBcvDto getDollarRate() {
        RespuesatTasaBcvDto resp = new RespuesatTasaBcvDto();

        try {
            TrustStrategy acceptingTrustStrategy = new TrustSelfSignedStrategy();
            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

            String endPoint = "https://pydolarve.org/api/v1";

            HttpGet get = new HttpGet(endPoint + "/dollar?monitor=bcv");

            try {
                CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
                HttpResponse response = httpClient.execute(get);
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                log.info("Inicio Servicio Tasa" + responseString);

                Gson gson = new Gson();
                resp = gson.fromJson(responseString, RespuesatTasaBcvDto.class);

                log.info("Fin Servicio Tasa" + responseString);
                ObjectMapper mapper = new ObjectMapper();
                RespuesatTasaBcvDto obj = mapper.readValue(responseString, RespuesatTasaBcvDto.class);



            } catch (Exception e) {
                log.error("fallo respuesta => " + e.getMessage());
            }

        } catch (Exception e) {
            log.error("Servicio Bcv");
            log.error(e.getMessage());
        }
        return resp;
    }



}