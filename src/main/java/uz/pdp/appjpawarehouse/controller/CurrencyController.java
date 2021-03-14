package uz.pdp.appjpawarehouse.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpawarehouse.entity.Currency;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping(value = "/upload")
    public Result addCurrency(@RequestBody Currency currency){

        Result result = currencyService.addCurrency(currency);
        return result;
    }

    @GetMapping(value = "/get")
    public List<Currency> getCurrencyList(){

        List<Currency> currencyList = currencyService.getCurrencyList();
        return currencyList;
    }

    @PutMapping("/edit/{currencyId}")
    public Result editCurrency(@PathVariable Integer currencyId, @RequestBody Currency currency){

        Result result = currencyService.editCurrency(currencyId, currency);
        return result;
    }

    @DeleteMapping(value = "/delete/{currencyId}")
    public Result deleteCurrency(@PathVariable Integer currencyId){

        Result result = currencyService.deleteCurrency(currencyId);
        return result;
    }

}
