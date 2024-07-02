package com.GestTicketing.springboot_app.service;

import com.GestTicketing.springboot_app.entity.Admin;
import com.GestTicketing.springboot_app.entity.Apprenant;
import com.GestTicketing.springboot_app.entity.Formateur;
import com.GestTicketing.springboot_app.entity.User;
import com.GestTicketing.springboot_app.repository.AdminRepository;
import com.GestTicketing.springboot_app.repository.ApprenantRepository;
import com.GestTicketing.springboot_app.repository.FormateurRepository;
import com.GestTicketing.springboot_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ApprenantRepository apprenantRepository;
    private final FormateurRepository formateurRepository;
    private final AdminRepository adminRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


    public Apprenant saveApprenant(Apprenant apprenant) {
        return apprenantRepository.save(apprenant);
    }

    public Formateur saveFormateur(Formateur formateur) {
        return formateurRepository.save(formateur);
    }

    public Admin saveAdmin(Admin admin){
        return adminRepository.save(admin);
    }
}