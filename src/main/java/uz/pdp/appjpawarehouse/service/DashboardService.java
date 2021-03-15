package uz.pdp.appjpawarehouse.service;


import org.springframework.stereotype.Service;
import uz.pdp.appjpawarehouse.DateUtil;
import uz.pdp.appjpawarehouse.payload.DailyTotal;
import uz.pdp.appjpawarehouse.repositort.InputProductRepository;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class DashboardService {


   final InputProductRepository inputProductRepository;

    public DashboardService(InputProductRepository inputProductRepository) {
        this.inputProductRepository = inputProductRepository;
    }

    public DailyTotal getDashboard1(){

        Date startOfDay = DateUtil.atStartOfDay(new Date());

        return inputProductRepository.findDailyInput(startOfDay);
    }



}
