package uz.pdp.appjpawarehouse.payload;

import lombok.Data;
import uz.pdp.appjpawarehouse.entity.Output;

@Data
public class OutputProductDTO {

    private Integer productId;
    private Double amount;
    private Double price;
    private Integer outputId;
}
