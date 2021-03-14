package uz.pdp.appjpawarehouse.repositort;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpawarehouse.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    boolean existsByName(String name);
}
