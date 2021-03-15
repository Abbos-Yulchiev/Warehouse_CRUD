package uz.pdp.appjpawarehouse.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appjpawarehouse.payload.DailyTotal;
import uz.pdp.appjpawarehouse.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {


    final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/input")
    DailyTotal getDashboard(){
        return dashboardService.getDashboard1();
    }
}
