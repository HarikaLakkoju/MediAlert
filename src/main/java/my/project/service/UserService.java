package my.project.service;

import my.project.Repo.UserRepository;
import my.project.exception.ResourceNotFoundException;
import my.project.model.Medication;
import my.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User addUser(User user) {

        System.out.println("UserController.saveUser");
        User user1 = new User(user.getName(), user.getPhoneNumber());
        List<Medication> lists = new ArrayList<>();
        for (Medication med : user.getMedications()) {
            Medication medication = new Medication(med.getName(), med.getTabletCount(), med.getTimeToTake(), med.getSpecialInstruction());
            medication.setUser(user1);
            lists.add(medication);
        }
        user1.setMedications(lists);
        return userRepository.save(user1);


    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        user.setName(updatedUser.getName());
        user.setPhoneNumber(updatedUser.getPhoneNumber());
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        userRepository.delete(user);
    }

}
