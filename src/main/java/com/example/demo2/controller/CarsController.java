package com.example.demo2.controller;

import com.example.demo2.dao.CarsDAO;
import com.example.demo2.entity.Car;
import com.example.demo2.validator.EntitiesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/car")
public class CarsController {

    private final CarsDAO carsDAO;

    private final EntitiesValidator entitiesValidator;

    @Autowired
    public CarsController(CarsDAO carsDAO, EntitiesValidator entitiesValidator) {
        this.carsDAO = carsDAO;
        this.entitiesValidator = entitiesValidator;
    }

    // get all cars in list and show in html table
    @GetMapping
    public String showOwnersList(Model model) {
        model.addAttribute("cars", carsDAO.cars());
        return "car/carsList";
    }

    // Create car from html form
    @PostMapping()
    public String createCar(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "car/newCar";
        if (entitiesValidator.ownerExistById(car.getOwnerId())) {
            carsDAO.save(car);
            return "redirect:/car";
        } else {
            bindingResult.rejectValue("ownerId", "ownerId", "OwnerId should be correct");
            return "car/newCar";
        }
    }

    // show car by ID
    @GetMapping("/{id}")
    public String showCar(@PathVariable("id") int id, Model model) {
        Car car = new Car();
        if (entitiesValidator.carExistById(id))
            car = carsDAO.show(id);

        model.addAttribute("car", car);
        return "car/show";
    }

    // update car by ID
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "car/carEdit";
        } else {
            if (entitiesValidator.carExistById(id) && entitiesValidator.ownerExistById(car.getOwnerId())) {
                carsDAO.update(id, car);
                return "redirect:/car";
            } else {
                bindingResult.rejectValue("ownerId", "ownerId", "OwnerId should be correct");
                return "car/carEdit";
            }
        }

    }


    // delete car by ID
    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable("id") int id) {
        carsDAO.delete(id);
        return "redirect:/car";
    }

    // show the form for car create and make post query after submit
    @GetMapping("/new")
    public String newCar(@ModelAttribute("car") Car car) {
        return "car/newCar";
    }

    // show the form for Car edit and make post query after submit
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("car", carsDAO.show(id));
        return "car/carEdit";
    }
}
