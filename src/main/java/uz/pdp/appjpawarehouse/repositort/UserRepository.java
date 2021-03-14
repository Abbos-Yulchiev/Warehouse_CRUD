package uz.pdp.appjpawarehouse.repositort;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appjpawarehouse.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

    @Query(value = "select max(id) from users", nativeQuery = true)
    Integer maxUserId();
}
