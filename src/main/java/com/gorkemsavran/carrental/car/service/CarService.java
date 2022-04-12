package com.gorkemsavran.carrental.car.service;

import com.gorkemsavran.carrental.car.Car;
import com.gorkemsavran.carrental.car.CarRepository;
import com.gorkemsavran.carrental.car.controller.request.CarRequest;
import com.gorkemsavran.carrental.user.FirmUser;
import com.gorkemsavran.carrental.user.UserRepository;
import com.gorkemsavran.carrental.user.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CustomUserDetailsService userDetailsService;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Transactional
    public Car getCar(final Long carId) {
        return carRepository.findById(carId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Collection<Car> getUserCars() {
        FirmUser firmUser = (FirmUser) userDetailsService.getAuthenticatedUser();
        return firmUser.getCars();
    }

    @Transactional
    public Car addCar(final CarRequest carRequest) {
        FirmUser firmUser = (FirmUser) userDetailsService.getAuthenticatedUser();
        Car car = new Car(firmUser, carRequest.getModel(), carRequest.getType(), carRequest.getDailyPrice());
        firmUser.addCar(car);
        car = carRepository.save(car);
        userRepository.save(firmUser);
        return car;
    }
}
