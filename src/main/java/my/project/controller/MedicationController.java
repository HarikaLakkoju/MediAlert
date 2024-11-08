package my.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import my.project.model.Medication;
import my.project.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{userId}/medications")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;
    @Operation(summary = "Save Medication For UserId", description = "Save Medication For UserId")
    @PostMapping
    public ResponseEntity<Medication> addMedication(@PathVariable Long userId, @RequestBody Medication medication) {
        return new ResponseEntity<>(medicationService.addMedication(userId, medication), HttpStatus.CREATED);
    }
    @Operation(summary = "Update Medication", description = "Update Medicine of MedcineId For UserId")
    @PutMapping("/{id}")
    public ResponseEntity<Medication> updateMedication(@PathVariable Long id, @RequestBody Medication updatedMedication) {
        return ResponseEntity.ok(medicationService.updateMedication(id, updatedMedication));
    }
    @Operation(summary = "Delete Medication", description = "Delete Medicine of the User")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
        return ResponseEntity.noContent().build();
    }
}