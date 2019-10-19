package com.medstat.med.service;

import com.medstat.med.domain.Role;
import com.medstat.med.domain.User;
import com.medstat.med.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;
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
}
