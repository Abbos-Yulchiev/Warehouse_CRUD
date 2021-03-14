package uz.pdp.appjpawarehouse.repositort;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpawarehouse.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);


}
