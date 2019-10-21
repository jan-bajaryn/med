package com.medstat.med.service;

import com.medstat.med.domain.Role;
import com.medstat.med.domain.User;
import com.medstat.med.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final UserRepo userRepo;

    @Autowired
    public AdminService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String admin_control(User user, Model model) throws IllegalAccessException {
        Optional<User> byId = userRepo.findById(user.getId());
        if (byId.isPresent() && byId.get().getRoles().contains(Role.ADMIN)) {
            model.addAttribute("users", userRepo.findAll());
            return "admin_control";
        }
        throw new IllegalAccessException("You must be admin to see this page.");

    }

    public String admin_control_spec_user(Model model, User user, Long id) {
        Optional<User> byIdEditor = userRepo.findById(user.getId());

        Optional<User> byIdToEdit = userRepo.findById(id);

        if (byIdEditor.isPresent() && byIdToEdit.isPresent()) {
            model.addAttribute("userToSend", byIdToEdit.get());
            model.addAttribute("roles", new ArrayList<Role>(Arrays.asList(Role.USER, Role.ADMIN, Role.EDITOR)));
        } else
            throw new IllegalArgumentException("there are no such user");
        return "user_edit";

    }

    public String modificate_spec_user(String username, Long userId, Map<String, String> form) {

        Optional<User> byIdToEdit = userRepo.findById(userId);
        if (byIdToEdit.isPresent()) {

            User userToEdit = byIdToEdit.get();
            userToEdit.setUsername(username);

            Set<String> roles = Arrays.stream(Role.values())
                    .map(Role::name)
                    .collect(Collectors.toSet());

            userToEdit.getRoles().clear();

            for (String key : form.keySet()) {
                if (roles.contains(key)) {
                    userToEdit.getRoles().add(Role.valueOf(key));
                }
            }

            userRepo.save(userToEdit);
        } else {
            throw new IllegalArgumentException("There no such user");
        }
        return "redirect:/admin_control";
    }
}
