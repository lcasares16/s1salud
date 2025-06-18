package com.banvenez.ast;

import lombok.Data;

@Data



public class actualizarTasaOficial {

//    logger.info("Iniciando la actualizacion de la tasa oficial...");
//
//        try {
//        SqlRowSet rs = jdbc.queryForRowSet("SELECT * FROM tasa_oficial ORDER BY fecha DESC LIMIT 1");
//        rs.next();
//
//        LocalDate fechaActual = rs.getDate("fecha").toLocalDate();
//
//        Document document = SSLHelper.getJsoupConnection(tasaOficialUrl).get();
//
//        BigDecimal eur = new BigDecimal(document.getElementById("euro").select("div > div > div").get(2).text().replace(",", ".")).setScale(4, RoundingMode.DOWN);
//        BigDecimal usd = new BigDecimal(document.getElementById("dolar").select("div > div > div").get(2).text().replace(",", ".")).setScale(4, RoundingMode.DOWN);
//        LocalDate fechaValor = LocalDate.parse(document.getElementsByClass("date-display-single").attr("content").substring(0, 10));
//
//        while (fechaActual.compareTo(fechaValor) < 0) {
//            fechaActual = fechaActual.plusDays(1);
//
//            if (fechaActual.compareTo(fechaValor) == 0) {
//                jdbc.update("INSERT INTO tasa_oficial (fecha, valor_eur, valor_usd) VALUES (?, ?, ?)", fechaActual, eur, usd);
//            } else {
//                jdbc.update("INSERT INTO tasa_oficial (fecha, valor_eur, valor_usd) VALUES (?, ?, ?)", fechaActual, rs.getBigDecimal("valor_eur"), rs.getBigDecimal("valor_usd"));
//            }
//        }
//    } catch (Exception e) {
//        logger.error("Error obteniendo la tasa oficial del BCV", e);
//    } finally {
//        logger.info("Finalizada la actualizacion de la tasa oficial.");
//    }

}
