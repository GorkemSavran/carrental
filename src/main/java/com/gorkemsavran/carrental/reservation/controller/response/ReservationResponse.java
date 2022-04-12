package com.gorkemsavran.carrental.reservation.controller.response;

import com.gorkemsavran.carrental.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {

    private Long normalUserId;

    private Long carId;

    private LocalDate start;

    private LocalDate end;

    public static ReservationResponse fromReservation(final Reservation reservation) {
        return new ReservationResponse(
                reservation.getNormalUser().getId(),
                reservation.getCar().getId(),
                reservation.getStart(),
                reservation.getEnd()
        );
    }
}
