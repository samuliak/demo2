package com.example.demo2.controller;

import com.example.demo2.dao.OwnerDAO;
import com.example.demo2.entity.Owner;
import com.example.demo2.validator.EntitiesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/owner")
public class OwnersController {

    private final OwnerDAO ownerDAO;

    private final EntitiesValidator entitiesValidator;

    @Autowired
    public OwnersController(OwnerDAO ownerDAO, EntitiesValidator entitiesValidator) {
        this.ownerDAO = ownerDAO;
        this.entitiesValidator = entitiesValidator;
    }

    // get all owners in list and show in html table
    @GetMapping()
    public String showOwnersList(Model model) {
        model.addAttribute("owners", ownerDAO.owners());
        return "owner/ownersList";
    }

    // create owner from html form
    @PostMapping()
    public String createOwner(@ModelAttribute("owner") @Valid Owner owner,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "owner/newOwner";
        ownerDAO.save(owner);
        return "redirect:/owner";
    }

    // show owner by ID
    @GetMapping("/{id}")
    public String showOwner(@PathVariable("id") int id, Model model) {
        Owner owner = new Owner();
        if (entitiesValidator.ownerExistById(id)) {
            owner = ownerDAO.show(id);
        }
        model.addAttribute("owner", owner);
        return "owner/show";
    }

    // update owner by ID
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("owner") @Valid Owner owner,
                         BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "owner/ownerEdit";
        ownerDAO.update(id, owner);
        return "redirect:/owner";
    }

    // delete owner by ID
    @DeleteMapping("/{id}")
    public String deleteOwner(@PathVariable("id") int id) {
        ownerDAO.delete(id);
        return "redirect:/owner";
    }

    // show the form for owner create and make post query after submit
    @GetMapping("/new")
    public String newOwner(@ModelAttribute("owner") Owner owner) {
        return "owner/newOwner";
    }

    // show the form for owner edit and make post query after submit
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("owner", ownerDAO.show(id));
        return "owner/ownerEdit";
    }
}
