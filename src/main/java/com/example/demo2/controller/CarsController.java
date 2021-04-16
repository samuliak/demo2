package com.example.demo2.controller;

import com.example.demo2.dao.CarsDAO;
import com.example.demo2.entity.Car;
import com.example.demo2.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/car")
public class CarsController {

    private final CarsDAO carsDAO;

    @Autowired
    public CarsController(CarsDAO carsDAO) {
        this.carsDAO = carsDAO;
    }

    // get all cars in list and show in html table
    @GetMapping()
    public String showOwnersList(Model model) {
        model.addAttribute("cars", carsDAO.cars());
        return "car/carsList";
    }

    // Create car from html form
    @PostMapping()
    public String createCar(@ModelAttribute("car") Car car) {
        carsDAO.save(car);
        return "redirect:/car";
    }

    // show car by ID
    @GetMapping("/{id}")
    public String showCar(@PathVariable("id") int id, Model model) {
        model.addAttribute("car", carsDAO.show(id));
        return "car/show";
    }

    // update car by ID
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("car") Car car, @PathVariable("id") int id) {
        carsDAO.update(id, car);
        return "redirect:/car";
    }

    // delete car by ID
    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable("id") int id) {
        carsDAO.delete(id);
        return "redirect:/car";
    }

    // show the form for car create and make post query after submit
    @GetMapping("/new")
    public String newCar(Model model) {
        model.addAttribute("car", new Car());
        return "car/newCar";
    }

    // show the form for Car edit and make post query after submit
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("car", carsDAO.show(id));
        return "car/carEdit";
    }
}
