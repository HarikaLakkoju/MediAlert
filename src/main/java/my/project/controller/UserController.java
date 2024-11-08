package my.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import my.project.model.User;
import my.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController{
    @Autowired
    private UserService userService;
@Operation(summary = "Get All Users" ,description = "Fetch All Users From The Database")
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @Operation(summary = "Get All Users", description = "Fetch All Users")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
    @Operation(summary = "Save User", description = "Save User to the DataBase (The number You Are Going To Save Must Be Verified By Twilio")
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }
    @Operation(summary = "Update User By Id", description = "You Can Update User Data Only")
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }
    @Operation(summary = "Delete User By Id", description = "Delete User")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}

