package com.example.demo2.controller;

import com.example.demo2.dao.OwnerDAO;
import com.example.demo2.entity.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owner")
public class OwnersController {

    private final OwnerDAO ownerDAO;

    public OwnersController(OwnerDAO ownerDAO) {
        this.ownerDAO = ownerDAO;
    }

    @GetMapping()
    public String showOwnersList(Model model) {
        model.addAttribute("owners", ownerDAO.owners(model));
        return "owner/ownersList";
    }

    @GetMapping("/{id}")
    public String showOwner(@PathVariable("id") int id, Model model) {
        model.addAttribute("owner", ownerDAO.show(id));
        return "owner/show";
    }

    @GetMapping("/new")
    public String newOwner(Model model) {
        model.addAttribute("owner", new Owner());
        return "owner/newOwner";
    }

    @PostMapping()
    public String createOwner(@ModelAttribute("owner") Owner owner) {
        ownerDAO.save(owner);
        return "redirect:/owner";
    }

    @DeleteMapping("/{id}")
    public String deleteOwner(@PathVariable("id") int id) {
        ownerDAO.delete(id);
        return "redirect:/owner";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("owner", ownerDAO.show(id));
        return "owner/ownerEdit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("owner") Owner owner, @PathVariable("id") int id) {
        ownerDAO.update(id, owner);
        return "redirect:/owner";
    }
}
