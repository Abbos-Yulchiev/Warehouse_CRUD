package uz.pdp.appjpawarehouse.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class InputDTO {

    private Timestamp date;
    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private String factureNumber;
    private String code;
}
