package com.gorkemsavran.carrental.car.controller;

import com.gorkemsavran.carrental.car.controller.request.CarRequest;
import com.gorkemsavran.carrental.car.controller.response.CarResponse;
import com.gorkemsavran.carrental.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    @GetMapping("/{carId}")
    public CarResponse getCar(@PathVariable final Long carId) {
        return CarResponse.fromCar(carService.getCar(carId));
    }

    @GetMapping("/getUserCars")
    public List<CarResponse> getUserCar() {
        return carService.getUserCars().stream().map(CarResponse::fromCar).collect(Collectors.toList());
    }

    @PostMapping("/addCar")
    public CarResponse addCarr(@Valid @RequestBody CarRequest carRequest) {
        return CarResponse.fromCar(carService.addCar(carRequest));
    }
}
