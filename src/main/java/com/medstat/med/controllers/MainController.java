package com.medstat.med.controllers;

import com.medstat.med.domain.User;
import com.medstat.med.service.AdminService;
import com.medstat.med.service.EditorService;
import com.medstat.med.service.UserService;
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

import java.util.Map;

@Controller
@Slf4j
public class MainController {

    private final AdminService adminService;
    private final EditorService editorService;
    private final UserService userService;

    @Autowired
    public MainController(AdminService adminService, EditorService editorService, UserService userService) {
        this.adminService = adminService;
        this.editorService = editorService;
        this.userService = userService;
    }

    @GetMapping({"/", "/index"})
    public String red() {
        return "shared/index";
    }

    @GetMapping("/profile_user")
    public String profile_user(@AuthenticationPrincipal User user, Model model) {
        return userService.getProfileUser(user, model);
    }


    @GetMapping("/admin_control")
    public String admin_control(@AuthenticationPrincipal User user,
                                Model model) throws IllegalAccessException {
        return adminService.admin_control(user, model);
    }

    @GetMapping("/editor_control")
    @PreAuthorize("hasAuthority('EDITOR')")
    public String editor_control(@AuthenticationPrincipal User user,
                                 Model model) throws IllegalAccessException {
        return editorService.toEditorControl(user, model);
    }

    @GetMapping("/editor_control/{id}")
    @PreAuthorize("hasAuthority('EDITOR')")
    public String editor_edit(@AuthenticationPrincipal User user,
                              Model model,
                              @PathVariable(name = "id") String id) throws IllegalAccessException {
        return editorService.editor_control_spec_user(model, user, id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin_control/{id}")
    public String admin_edit(Model model,
                             @AuthenticationPrincipal User user,
                             @PathVariable(name = "id") String id) {

        return adminService.admin_control_spec_user(model, user, id);
    }

    @PostMapping("/admin_control/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editUser(@NonNull @RequestParam(name = "username") String username,
                           @RequestParam(name = "userId") String userId,
                           @RequestParam Map<String, String> form) {
        return adminService.modificate_spec_user(username, userId, form);
    }

    @PostMapping("/edit_control/save")
    @PreAuthorize("hasAuthority('EDITOR')")
    public String editUserByEditor(@NonNull @RequestParam(name = "username") String username,
                                   @RequestParam(name = "userId") String userId,
                                   @RequestParam Map<String, String> form) {
        return editorService.modificate_spec_user(username, userId, form);
    }


}
