package uz.pdp.appjpawarehouse.repositort;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpawarehouse.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

}
