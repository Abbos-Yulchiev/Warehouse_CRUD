package uz.pdp.appjpawarehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppJpaWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppJpaWarehouseApplication.class, args);
    }

}
