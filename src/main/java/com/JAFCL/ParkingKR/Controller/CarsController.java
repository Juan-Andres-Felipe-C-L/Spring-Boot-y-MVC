package com.JAFCL.ParkingKR.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.JAFCL.ParkingKR.Model.dto.CarsResponseDTO;
import com.JAFCL.ParkingKR.Model.dto.GlobalResponse;
import com.JAFCL.ParkingKR.Model.dto.RegisterRequestDTO;
import com.JAFCL.ParkingKR.Model.service.CarsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarsController {

    private final CarsService carsService;

    @GetMapping
    public String getCars(Model model) {

        model.addAttribute("cars", carsService.getCars());

        return "cars";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("car", new RegisterRequestDTO());

        return "create-car";
    }

    @PostMapping("/save")
    public String saveCar(@ModelAttribute("car") RegisterRequestDTO request) {

        carsService.createCar(request);

        return "redirect:/cars";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {

        GlobalResponse<CarsResponseDTO> response = carsService.getCarById(id);

        model.addAttribute("car", response.getData());

        return "edit-car";
    }

    @PostMapping("/update/{id}")
    public String updateCar(@PathVariable Long id, @ModelAttribute("car") RegisterRequestDTO request) {

        carsService.upDateCar(id, request);

        return "redirect:/cars";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {

        carsService.deleteCar(id);

        return "redirect:/cars";
    }
}