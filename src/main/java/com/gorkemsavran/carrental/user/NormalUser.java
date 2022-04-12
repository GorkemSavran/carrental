package com.gorkemsavran.carrental.user;

import com.gorkemsavran.carrental.reservation.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class NormalUser extends User {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservations = new HashSet<>();

    public NormalUser(final String username, final String password) {
        super(username, password);
    }


    private void checkBalanceToPay(final Double amount) {
        if (amount > getBalance()) throw new RuntimeException("Balance is insufficient!");
    }

    public void addReservation(final Reservation reservation) {
        double amountToPay = reservation.getCar().getDailyPrice() * Duration.between(reservation.getStart().atStartOfDay(), reservation.getEnd().atStartOfDay()).toDays();
        checkBalanceToPay(amountToPay);
        setBalance(getBalance() - amountToPay);
        reservations.add(reservation);
    }
}