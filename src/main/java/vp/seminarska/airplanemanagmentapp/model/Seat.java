package vp.seminarska.airplanemanagmentapp.model;

import lombok.Getter;
import lombok.Setter;
import vp.seminarska.airplanemanagmentapp.model.enumerators.SeatType;

import javax.persistence.*;

@Entity(name = "seat")
@Getter
@Setter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SeatType type;

    @ManyToOne
    private Flight onFlight;
}
