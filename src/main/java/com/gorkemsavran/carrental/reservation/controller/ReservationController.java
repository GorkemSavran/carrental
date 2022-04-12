package com.gorkemsavran.carrental.reservation.controller;

import com.gorkemsavran.carrental.reservation.controller.request.ReservationRequest;
import com.gorkemsavran.carrental.reservation.controller.response.ReservationResponse;
import com.gorkemsavran.carrental.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/makeReservation")
    public ReservationResponse makeReservation(@Valid @RequestBody final ReservationRequest reservationRequest) {
        return ReservationResponse.fromReservation(reservationService.makeReservation(reservationRequest));
    }

    @GetMapping("/getReservation/{reservationId}")
    public ReservationResponse getReservation(@PathVariable final Long reservationId) {
        return ReservationResponse.fromReservation(reservationService.getReservation(reservationId));
    }

    @GetMapping("/getUserReservations")
    public List<ReservationResponse> getUserReservations() {
        return reservationService
                .getUserReservations()
                .stream()
                .map(ReservationResponse::fromReservation)
                .collect(Collectors.toList());
    }
}