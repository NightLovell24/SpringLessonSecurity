package org.n0rth.springlessonsecurity.controller;

import org.n0rth.springlessonsecurity.entity.UserRole;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AppController {

    @GetMapping("/")
    public String employeeInfo(Model model) {
        model.addAttribute("managerRole", UserRole.MANAGER.name());
        model.addAttribute("HRRole", UserRole.HR.name());
        return "default_view_info";
    }

    @GetMapping("/hr_info")
    public String getHRInfo() {
        return "hr_view_info";
    }

    @GetMapping("/manager_info")
    public String getManagerInfo() {
        return "manager_view_info";
    }
}
