package uz.pdp.appjpawarehouse.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uz.pdp.appjpawarehouse.DateUtil;
import uz.pdp.appjpawarehouse.constants.ExpireStatus;
import uz.pdp.appjpawarehouse.entity.InputProduct;
import uz.pdp.appjpawarehouse.repositort.InputProductRepository;

import java.util.Date;
import java.util.List;

@Service
public class ExpireCheckService {


    final InputProductRepository inputProductRepository;

    public ExpireCheckService(InputProductRepository inputProductRepository) {
        this.inputProductRepository = inputProductRepository;
    }



    @Scheduled(cron = "0 9 * * ?")
    void checkWarningStatus(){

        Date date = DateUtil.getDateFromFuture(10);
        List<InputProduct> inputProductList = inputProductRepository.getWarningProducts(date);

        for (InputProduct inputProduct: inputProductList) {

            inputProduct.setExpireStatus(ExpireStatus.WARNING);
            inputProductRepository.save(inputProduct);

        }

    }


}
