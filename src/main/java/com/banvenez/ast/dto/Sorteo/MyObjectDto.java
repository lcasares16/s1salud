package com.banvenez.ast.dto.Sorteo;

import lombok.Data;

@Data
public class MyObjectDto {
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
}
