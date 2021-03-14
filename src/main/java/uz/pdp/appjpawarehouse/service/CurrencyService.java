package uz.pdp.appjpawarehouse.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appjpawarehouse.entity.Currency;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.repositort.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Result addCurrency(Currency currency) {

        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName)
            return new Result(currency.getName() + " is already exist!", false);

        Currency newCurrency = new Currency();

        newCurrency.setName(currency.getName());
        Currency save = currencyRepository.save(newCurrency);
        return new Result("Currency successfully added.", true, save.getId());
    }

    public List<Currency> getCurrencyList() {

        List<Currency> currencyList = currencyRepository.findAll();
        return currencyList;
    }

    public Result editCurrency(@PathVariable Integer currencyId, @RequestBody Currency currency) {

        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        if (!optionalCurrency.isPresent())
            return new Result("Invalid Currency ID", false);

        boolean existsByName = currencyRepository.existsByName(currency.getName());
        if (existsByName)
            return new Result(currency.getName() + " is already exist!", false);

        Currency editedCurrency = optionalCurrency.get();
        editedCurrency.setName(currency.getName());
        Currency save = currencyRepository.save(editedCurrency);
        return new Result("Currency successfully edited.", true, save.getId());
    }

    public Result deleteCurrency(@PathVariable Integer currencyId){

        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        if (!optionalCurrency.isPresent())
            return new Result("Invalid Currency Id",false);

        currencyRepository.deleteById(currencyId);
        return new Result("Currency deleted!", true);
    }
}
