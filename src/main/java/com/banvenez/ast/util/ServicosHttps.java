package com.banvenez.ast.util;

import com.banvenez.ast.dto.Bcv.MonedaDTO;
import com.banvenez.ast.dto.Intranet.CorreoDto;
import com.banvenez.ast.dto.Intranet.RespuestaDto;
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
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLContext;

@Slf4j
@Service
public class ServicosHttps {



    public MonedaDTO servicioBcv()   {
        CorreoDto correo =  new CorreoDto();
        correo.setDestino("intranet_bdv@banvenez.com");
        correo.setRemitente("intranet_bdv@banvenez.com");
        try {
            TrustStrategy acceptingTrustStrategy = new TrustSelfSignedStrategy();
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
            HttpPost httpPost =
                    new HttpPost("https://casacambioserv.extra.bcv.org.ve/service/autorizacion");
            httpPost.addHeader("content-type", "text/xml;charset=utf-8");

            StringBuffer buffer = new StringBuffer();
            buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            buffer.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://serviceInterface.bcv.org.ve/\">");
            buffer.append("<soapenv:Header/>");
            buffer.append("<soapenv:Body>");
            buffer.append("<ser:TASASCAMBIO/>");
            buffer.append("</soapenv:Body>");
            buffer.append("</soapenv:Envelope>");
            log.info(buffer.toString());

            StringEntity lEntity = new StringEntity(buffer.toString());
            httpPost.setEntity(lEntity);

            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            String responseString = EntityUtils.toString(entity, "UTF-8");
            responseString = responseString.replaceAll("&lt;", "<");
            responseString = responseString.replaceAll("&gt;", ">");

            log.info("RESPUESTA BCV ==> ");
            log.info(responseString);
            MonedaDTO moneda  = this.parseResponse(responseString);
            return moneda;
        }catch (Exception e){
            log.error("ServicosHttps:servicioBcv "+ e.getMessage());
            MonedaDTO moneda = null;
            return  moneda;
        }

    }

    private MonedaDTO parseResponse(String entity) throws Exception {
        try {
            Elements xmlRetorno = extractXmlElement(entity, "MONEDA");
            MonedaDTO moneda = new MonedaDTO();
            for (Element elemen : xmlRetorno ){
                //  if(elemen.attributes.get("CODIGO").getValue().equalsIgnoreCase("USD")){

                if(elemen.attributes().get("CODIGO").equalsIgnoreCase("USD")){
                    moneda.setCodigo(elemen.attributes().get("CODIGO"));
                    moneda.setVenta(elemen.getElementsByTag("VENTA").text());
                    moneda.setCompra(elemen.getElementsByTag("COMPRA").text());
                    break;
                }
            }
            return moneda;
        } catch (Exception e) {
            log.error("parseResponse:XmlSoap => "+ e.getMessage());
            MonedaDTO moneda = null;
            return  moneda;
        }
    }

    private Elements extractXmlElement(String xmlString, String nodeTagNameElement) {
        org.jsoup.nodes.Document  document= Jsoup.parse(xmlString, "", Parser.xmlParser());
        ((org.jsoup.nodes.Document) document).outputSettings().prettyPrint(false);
        Elements retorno = document.getElementsByTag(nodeTagNameElement);
        return retorno;
    }





}


