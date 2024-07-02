package com.GestTicketing.springboot_app.controller;

import com.GestTicketing.springboot_app.entity.Admin;
import com.GestTicketing.springboot_app.entity.Apprenant;
import com.GestTicketing.springboot_app.entity.Formateur;
import com.GestTicketing.springboot_app.entity.User;
import com.GestTicketing.springboot_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder; // Inject Passwo
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/apprenants")
    public ResponseEntity<Apprenant> createApprenant(@RequestBody Apprenant apprenant) {
        // Encode le mot de passe avant de sauvegarder l'apprenant
        apprenant.setPassword(passwordEncoder.encode(apprenant.getPassword()));
        Apprenant savedApprenant = userService.saveApprenant(apprenant);
        return ResponseEntity.ok(savedApprenant);
    }

    @PostMapping("/formateurs")
    public ResponseEntity<Formateur> createFormateur(@RequestBody Formateur formateur) {
        // Encode le mot de passe avant de sauvegarder le formateur
        formateur.setPassword(passwordEncoder.encode(formateur.getPassword()));
        Formateur savedFormateur = userService.saveFormateur(formateur);
        return ResponseEntity.ok(savedFormateur);
    }

    @PostMapping("/admins")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        // Encode le mot de passe avant de sauvegarder l'admin
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        Admin savedAdmin = userService.saveAdmin(admin);
        return ResponseEntity.ok(savedAdmin);
    }

}