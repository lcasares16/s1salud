package com.banvenez.ast.dto.Sorteo;

import lombok.Data;

import java.sql.Blob;

@Data
public class data {
    public String channelCode;
    public String timePay;
    public String referenc;
    public double amount;
    public String bankOrigin;
    public String bankDestination;
    public Integer customerOrigin;
    public Integer customerDestination;
    public String instrumentOrigin;
    public String instrumentDestination;
    public String accountOrigin;
    public String accountDestination;
    public double amountCommission;

}
