package my.project.Repo;

import my.project.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication,Long> {
    List<Medication> findByUserId(Long userId);
    List<Medication> findByTabletCountLessThan(int count);
}
