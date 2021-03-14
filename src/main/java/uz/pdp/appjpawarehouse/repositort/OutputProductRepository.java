package uz.pdp.appjpawarehouse.repositort;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpawarehouse.entity.OutputProduct;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {
}
