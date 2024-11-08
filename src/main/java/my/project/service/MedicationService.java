package my.project.service;

import my.project.Repo.MedicationRepository;
import my.project.Repo.UserRepository;
import my.project.exception.ResourceNotFoundException;
import my.project.model.Medication;
import my.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Medication addMedication(Long userId, Medication medication) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        medication.setUser(user);
        return medicationRepository.save(medication);
    }

    @Transactional
    public Medication updateMedication(Long id, Medication updatedMedication) {
        Medication medication = medicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found"));
        medication.setName(updatedMedication.getName());
        medication.setTabletCount(updatedMedication.getTabletCount());
        medication.setTimeToTake(updatedMedication.getTimeToTake());
        medication.setSpecialInstruction(updatedMedication.getSpecialInstruction());
        return medicationRepository.save(medication);
    }

    @Transactional
    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }


}



