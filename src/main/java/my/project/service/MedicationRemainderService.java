package my.project.service;

import my.project.Repo.MedicationRepository;
import my.project.model.Medication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class MedicationRemainderService {

    @Autowired
    private MedicationRepository medRepo;
    @Autowired
    private SmsService smsService;
    private static final Logger logger = LoggerFactory.getLogger(MedicationRemainderService.class);

    // Cron expression to run every minute
    @Scheduled(cron = "0 * * * * ?")
    public void sendMedicationReminders() {
        System.out.println("MedicationRemainderService.sendMedicationReminders");
        // Fetch all medication records from the repository
        List<Medication> allMedications = medRepo.findAll();
        System.out.println(allMedications);
        LocalTime currentTime = LocalTime.now();
        System.out.println(currentTime);
        System.out.println("Current time: " + currentTime);

        for (Medication med : allMedications) {
            // Parse the scheduled medication time
            LocalTime timeToTake = LocalTime.parse(med.getTimeToTake());
            LocalTime reminderTime = timeToTake.minusMinutes(10);

            // Check if the current time matches the reminder time
            if (currentTime.getHour() == reminderTime.getHour() && currentTime.getMinute() == reminderTime.getMinute()) {
                // Decrease the tablet count and prepare the reminder message
                int tabletCount = med.getTabletCount();
                med.setTabletCount(tabletCount - 1);
                System.out.println("entered");
                String message;
                if (tabletCount > 2) {
                    message = "Reminder: Please take your prescribed medication, " + med.getName() +
                            "  , " + med.getSpecialInstruction();
                } else if (tabletCount > 0) {
                    message = "Attention, " + med.getUser().getName() + ": Please take your medication, " +
                            med.getName() + ",  Note: The stock for this medication is low.";
                } else {
                    message = "Notice: A refill is needed for your medication, " + med.getName() + ".";
                }


                // Send SMS through SmsService and log the result
                boolean smsSent = smsService.sendSms(med.getUser().getPhoneNumber(), message);
                if (smsSent) {
                    logger.info("SMS sent to {} for medication: {}", med.getUser().getName(), med.getName());
                } else {
                    logger.error("Failed to send SMS to {} for medication: {}", med.getUser().getName(), med.getName());
                }
            }
        }
    }
    @Scheduled(cron = "0 0 17 * * ?")
    public void checkLowStockMedications() {
        // Find medications with tablet count < 2
        List<Medication> lowStockMedications = medRepo.findByTabletCountLessThan(2);
        System.out.println(lowStockMedications);
        if (!lowStockMedications.isEmpty()) {
            String medicationNames = lowStockMedications.stream()
                    .map(Medication::getName)
                    .collect(Collectors.joining(", "));

            String message = "Reminder: You are low on the following medications: " +
                    medicationNames + ". Please buy them or consult your doctor.";

            // Assume each medication belongs to a user and has access to their phone number
            lowStockMedications.stream()
                    .map(Medication::getUser)
                    .distinct()
                    .forEach(user -> smsService.sendSms(user.getPhoneNumber(), message));
        }
    }
}