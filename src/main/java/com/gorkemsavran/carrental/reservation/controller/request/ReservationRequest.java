package com.gorkemsavran.carrental.reservation.controller.request;

import com.gorkemsavran.carrental.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    @NotNull
    private Long carId;

    @NotNull
    @FutureOrPresent
    private LocalDate start;

    @NotNull
    @FutureOrPresent
    private LocalDate end;

    public static ReservationRequest fromReservation(final Reservation reservation) {
        return new ReservationRequest(
                reservation.getCar().getId(),
                reservation.getStart(),
                reservation.getEnd()
        );
    }
}
