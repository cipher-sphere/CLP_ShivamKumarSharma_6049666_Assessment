package com.example.demo.controller;

import com.example.demo.dto.EmpDto;
import com.example.demo.service.EmpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmpController {

    @Autowired
    private EmpService empService;

    // ─── View All Employees ───────────────────────────────────────────────────

    @GetMapping("/viewall")
    public String viewAll(Model model) {
        model.addAttribute("employees", empService.getAllEmployees());
        return "viewall";
    }

    // ─── Edit Employee – Show Form ────────────────────────────────────────────

    @GetMapping("/edit/{eid}")
    public String showEditForm(@PathVariable("eid") Integer eid, Model model) {
        EmpDto empDto = empService.getEmployeeById(eid);
        model.addAttribute("empDto", empDto);
        return "editEmployee";
    }

    // ─── Edit Employee – Save Changes ─────────────────────────────────────────

    @PostMapping("/edit")
    public String saveEmployee(@Valid @ModelAttribute("empDto") EmpDto empDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes,
                               Model model) {

        if (bindingResult.hasErrors()) {
            // Re-render form with validation errors
            return "editEmployee";
        }

        empService.updateEmployee(empDto);
        redirectAttributes.addFlashAttribute("successMessage", "Employee Edited Successfully!");
        return "redirect:/viewall";
    }

    // ─── Delete Employee ──────────────────────────────────────────────────────

    @GetMapping("/delete/{eid}")
    public String deleteEmployee(@PathVariable("eid") Integer eid,
                                 RedirectAttributes redirectAttributes) {
        empService.deleteEmployee(eid);
        redirectAttributes.addFlashAttribute("successMessage", "Employee Deleted Successfully!");
        return "redirect:/viewall";
    }
}
