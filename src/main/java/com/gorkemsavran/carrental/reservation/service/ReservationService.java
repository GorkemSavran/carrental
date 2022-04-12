package com.gorkemsavran.carrental.reservation.service;

import com.gorkemsavran.carrental.car.Car;
import com.gorkemsavran.carrental.car.CarRepository;
import com.gorkemsavran.carrental.reservation.Reservation;
import com.gorkemsavran.carrental.reservation.ReservationRepository;
import com.gorkemsavran.carrental.reservation.controller.request.ReservationRequest;
import com.gorkemsavran.carrental.user.NormalUser;
import com.gorkemsavran.carrental.user.UserRepository;
import com.gorkemsavran.carrental.user.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;

@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final CustomUserDetailsService userDetailsService;

    @Transactional
    public Reservation makeReservation(final ReservationRequest reservationRequest) {
        Car car = carRepository.findById(reservationRequest.getCarId()).orElseThrow(EntityNotFoundException::new);
        NormalUser user = (NormalUser) userDetailsService.getAuthenticatedUser();
        Reservation reservation = new Reservation(user, car, reservationRequest.getStart(), reservationRequest.getEnd());
        car.addReservation(reservation);
        user.addReservation(reservation);
        reservation = reservationRepository.save(reservation);
        carRepository.save(car);
        userRepository.save(user);
        return reservation;
    }

    @Transactional
    public Reservation getReservation(final Long reservationId) {
        NormalUser user = (NormalUser) userDetailsService.getAuthenticatedUser();

        return user.getReservations().stream()
                .filter(reservation -> reservation.getId().equals(reservationId))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Collection<Reservation> getUserReservations() {
        NormalUser user = (NormalUser) userDetailsService.getAuthenticatedUser();

        return user.getReservations();
    }

}
