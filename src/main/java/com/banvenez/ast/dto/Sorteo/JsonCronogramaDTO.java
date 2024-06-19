package com.banvenez.ast.dto.Sorteo;

import lombok.Data;

@Data
public class JsonCronogramaDTO {

    public Integer idSorteoCronogramaPk;
    public String datePay;
    public String modalityCode;
    public String productCode;
    public String timePayInit;
    public String timePayEnd;

}
