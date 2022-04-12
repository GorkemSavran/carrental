package com.gorkemsavran.carrental.reservation;

import com.gorkemsavran.carrental.BaseEntity;
import com.gorkemsavran.carrental.car.Car;
import com.gorkemsavran.carrental.user.NormalUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reservation extends BaseEntity {

    @ManyToOne
    private NormalUser normalUser;

    @ManyToOne
    private Car car;

    @FutureOrPresent
    private LocalDate start;

    @FutureOrPresent
    private LocalDate end;

    public Reservation(final NormalUser normalUser,
                       final Car car,
                       final LocalDate start,
                       final LocalDate end) {
        this.normalUser = normalUser;
        this.car = car;
        this.start = start;
        this.end = end;
        if (!start.isBefore(end))
            throw new UnsupportedOperationException("Reservation start date must be before end date!");
    }

    public boolean doesConflict(final Reservation reservation) {
        if (reservation.start.isBefore(start) && reservation.end.isBefore(start)) return false;
        if (reservation.start.isAfter(end) && reservation.end.isAfter(end)) return false;
        return true;
    }
}
