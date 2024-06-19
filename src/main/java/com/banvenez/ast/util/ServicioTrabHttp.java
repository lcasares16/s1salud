package com.banvenez.ast.util;

import com.banvenez.ast.dao.IntranetcorpDao;
import com.banvenez.ast.dto.Sorteo.DataPagosDto;
import com.banvenez.ast.dto.Sorteo.EntradaTrab;
import com.banvenez.ast.dto.Sorteo.RespuestPCIDto;
import com.banvenez.ast.dto.Sorteo.ResultadoTrab;
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
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;


@Service
@Slf4j
public class ServicioTrabHttp {

    ConexionesServicios servicios = new ConexionesServicios();
    private IntranetcorpDao daoIntranet = new IntranetcorpDao();

    public ResultadoTrab peopleTraba(Integer jsonEntrada){

        ResultadoTrab resp = new ResultadoTrab();
        try {
            TrustStrategy acceptingTrustStrategy = new TrustSelfSignedStrategy();
            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

            String endPoint = daoIntranet.obtenerParametros(Constantes.contextoParametro, Constantes.contextoPeoplesWs, Constantes.parametroPeopleWs);

            HttpPost post = new HttpPost(endPoint + "DatosActivo");

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
                resp = gson.fromJson(responseString, ResultadoTrab.class);

                ObjectMapper mapper = new ObjectMapper();

                ResultadoTrab obj = mapper.readValue(responseString, ResultadoTrab.class);



            } catch (Exception e) {
                log.error("fallo respuesta => " + e.getMessage());
            }

        }catch (Exception e){
            log.error("ServiciosHttp:peopleTraba");
            log.error(e.getMessage());
        }
        return  resp;
    }

}
