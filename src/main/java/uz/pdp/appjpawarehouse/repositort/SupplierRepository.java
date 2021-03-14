package uz.pdp.appjpawarehouse.repositort;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpawarehouse.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    boolean existsByPoneNumber(String poneNumber);
}
