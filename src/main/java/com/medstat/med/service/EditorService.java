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
public class EditorService {

    private final UserRepo userRepo;

    @Autowired
    public EditorService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public String toEditorControl(User user, Model model) throws IllegalAccessException {


        Optional<User> byId = userRepo.findById(user.getId());
        if (byId.isPresent()) {
            model.addAttribute("users", ((List<User>) userRepo.findAll()).stream()
                    .filter(u -> u.getRoles().contains(Role.USER) &&
                            !u.getRoles().contains(Role.ADMIN) &&
                            !u.getRoles().contains(Role.EDITOR))
                    .collect(Collectors.toList()));
            return "editor_control";

        }
        throw new IllegalAccessException();

    }

    public String editor_control_spec_user(Model model, User user, String id) {
        Optional<User> byIdEditor = userRepo.findById(user.getId());

        Optional<User> byIdToEdit = userRepo.findById(id);

        if (byIdEditor.isPresent() && byIdToEdit.isPresent()) {
            model.addAttribute("userToSend", byIdToEdit.get());
            model.addAttribute("roles", new ArrayList<Role>(Collections.singletonList(Role.USER)));
        } else
            throw new IllegalArgumentException("there are no such user");
        return "user_edit";
    }

    public String modificate_spec_user(String username, String userId, Map<String, String> form) {

        Optional<User> byIdToEdit = userRepo.findById(userId);
        if (byIdToEdit.isPresent()) {

            User userToEdit = byIdToEdit.get();
            userToEdit.setUsername(username);

            Set<String> roles = Arrays.stream(Role.values())
                    .map(Role::name)
                    .collect(Collectors.toSet());

            userToEdit.getRoles().clear();

            for (String key : form.keySet()) {
                if (roles.contains(key) && Role.valueOf(key) != Role.ADMIN) {
                    userToEdit.getRoles().add(Role.valueOf(key));
                }
            }

            userRepo.save(userToEdit);
        } else {
            throw new IllegalArgumentException("There no such user");
        }
        return "redirect:/editor_control";
    }
}
