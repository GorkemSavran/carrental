package com.gorkemsavran.carrental.car.controller.request;

import com.gorkemsavran.carrental.car.CarType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarRequest {

    @NotNull
    private String model;

    @NotNull
    private CarType type;

    @NotNull
    private Double dailyPrice;

}
