package com.gorkemsavran.carrental.car;

import com.gorkemsavran.carrental.BaseEntity;
import com.gorkemsavran.carrental.reservation.Reservation;
import com.gorkemsavran.carrental.user.FirmUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Car extends BaseEntity {

    @ManyToOne
    private FirmUser firmUser;

    private String model;

    @Enumerated(value = EnumType.STRING)
    private CarType type;

    private Double dailyPrice;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations = new HashSet<>();

    public Car(final FirmUser firmUser, final String model, final CarType type, final Double dailyPrice) {
        this.firmUser = firmUser;
        this.model = model;
        this.type = type;
        this.dailyPrice = dailyPrice;
    }

    private void checkConflict(final Reservation newReservation) {
        boolean conflictExist = reservations.stream().anyMatch(reservation -> reservation.doesConflict(newReservation));
        if (conflictExist) throw new RuntimeException("Your request have a conflict with other reservation!");
    }

    public void addReservation(final Reservation reservation) {
        checkConflict(reservation);
        reservations.add(reservation);
    }
}
