package com.gorkemsavran.carrental.car.controller.response;


import com.gorkemsavran.carrental.car.Car;
import com.gorkemsavran.carrental.car.CarType;
import com.gorkemsavran.carrental.reservation.controller.response.ReservationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarResponse {

    private Long id;

    private Long firmUserId;

    private String model;

    private CarType type;

    private Double dailyPrice;

    private List<ReservationResponse> reservations;

    public static CarResponse fromCar(final Car car) {
        List<ReservationResponse> reservationResponses = car
                .getReservations()
                .stream()
                .map(ReservationResponse::fromReservation)
                .collect(Collectors.toList());
        return new CarResponse(
                car.getId(),
                car.getFirmUser().getId(),
                car.getModel(),
                car.getType(),
                car.getDailyPrice(),
                reservationResponses);
    }
}
