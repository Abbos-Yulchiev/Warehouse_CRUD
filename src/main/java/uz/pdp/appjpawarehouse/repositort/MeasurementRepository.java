package uz.pdp.appjpawarehouse.repositort;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpawarehouse.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    boolean existsByName(String name);

}
