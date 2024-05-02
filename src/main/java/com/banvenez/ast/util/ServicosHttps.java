package com.banvenez.ast.util;


import com.banvenez.ast.dto.Bcv.MonedaDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import org.jdom2.filter.ElementFilter;
import org.jdom2.input.SAXBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.net.ssl.SSLContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


@Slf4j
@Service
public class ServicosHttps {


    public MonedaDTO servicioBcv()   {

        try {
            TrustStrategy acceptingTrustStrategy = new TrustSelfSignedStrategy();
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
            HttpPost httpPost =
                    new HttpPost("https://casacambioserv-cert.extra.bcv.org.ve/service/autorizacion");
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
                if(elemen.attribute("CODIGO").getValue().equalsIgnoreCase("USD")){
                    moneda.setCodigo(elemen.attribute("CODIGO").getValue());
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
