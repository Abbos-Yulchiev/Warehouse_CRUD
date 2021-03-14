package uz.pdp.appjpawarehouse.repositort;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpawarehouse.entity.InputProduct;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {
}
