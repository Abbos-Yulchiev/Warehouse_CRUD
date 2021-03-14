package uz.pdp.appjpawarehouse.payload;

import lombok.Data;

@Data
public class UserDTO {

    private String firstname;
    private String lastName;
    private String phoneNumber;
    private String code;
    private String password;
    private Boolean active;
    private Integer warehousesID;
}
