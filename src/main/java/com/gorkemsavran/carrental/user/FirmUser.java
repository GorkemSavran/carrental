package com.gorkemsavran.carrental.user;

import com.gorkemsavran.carrental.car.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class FirmUser extends User {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Car> cars = new HashSet<>();

    @Column(nullable = false)
    private String city;

    public FirmUser(final String username, final String password, final String city) {
        super(username, password);
        this.city = city;
    }

    public void addCar(final Car car) {
        cars.add(car);
    }
}
