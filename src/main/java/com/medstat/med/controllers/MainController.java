package com.medstat.med.controllers;

import com.medstat.med.domain.Role;
import com.medstat.med.domain.User;
import com.medstat.med.repos.NoteRepo;
import com.medstat.med.repos.UserRepo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class MainController {

    @Autowired
    NoteRepo noteRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping({"/", "/index"})
    public String red() {
        return "shared/index";
    }

    @GetMapping("/profile_user")
    public String profile_user(@AuthenticationPrincipal User user, Model model) {
        Optional<User> byId = userRepo.findById(user.getId());
        if (byId.isPresent())
            model.addAttribute("notes", byId.get().getNotes());
        else
            throw new IllegalArgumentException("There are not note with so id");
        return "profile_user";
    }


    @GetMapping("/admin_control")
    public String admin_control(@AuthenticationPrincipal User user,
                                Model model) throws IllegalAccessException {

        Optional<User> byId = userRepo.findById(user.getId());
        if (byId.isPresent() && byId.get().getRoles().contains(Role.ADMIN)) {
            model.addAttribute("users", userRepo.findAll());
            return "admin_control";
        }
        throw new IllegalAccessException("You must be admin to see this page.");
    }

    @GetMapping("/editor_control")
    public String editor_control(@AuthenticationPrincipal User user,
                                 Model model) throws IllegalAccessException {
        Optional<User> byId = userRepo.findById(user.getId());
        if (byId.isPresent() &&
                (byId.get().getRoles().contains(Role.ADMIN) ||
                        byId.get().getRoles().contains(Role.EDITOR))) {
            model.addAttribute("users", userRepo.findAll().stream()
                    .filter(u -> u.getRoles().contains(Role.USER))
                    .collect(Collectors.toList()));
            return "editor_control";

        }
        throw new IllegalAccessException("You must be admin or editor to see this page.");
    }

    @GetMapping("/admin_control/{id}")
    public String admin_edit(Model model,
                             @AuthenticationPrincipal User user,
                             @PathVariable(name = "id") Long id) {
        Optional<User> byIdEditor = userRepo.findById(user.getId());

        Optional<User> byIdToEdit = userRepo.findById(id);

        if (byIdEditor.isPresent() && byIdToEdit.isPresent() && byIdEditor.get().getRoles().contains(Role.ADMIN)) {
            model.addAttribute("userToSend", byIdToEdit.get());
            model.addAttribute("roles", new ArrayList<Role>(Arrays.asList(Role.USER, Role.ADMIN, Role.EDITOR)));
        } else
            throw new IllegalArgumentException("there are no such user");
        return "user_edit";
    }

    @PostMapping("/admin_control/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editUser(@NonNull @RequestParam(name = "username") String username,
                           @RequestParam(name = "userId") Long userId,
                           @RequestParam Map<String, String> form) {
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
