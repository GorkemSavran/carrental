package com.gorkemsavran.carrental.reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Embeddable
public class ReservationKey implements Serializable {

    @Column(name = "normal_user_id")
    private Long normalUserId;

    @Column(name = "car_id")
    private Long carId;


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final ReservationKey that = (ReservationKey) o;

        if (!Objects.equals(normalUserId, that.normalUserId)) return false;
        return Objects.equals(carId, that.carId);
    }

    @Override
    public int hashCode() {
        int result = normalUserId != null ? normalUserId.hashCode() : 0;
        result = 31 * result + (carId != null ? carId.hashCode() : 0);
        return result;
    }
}
