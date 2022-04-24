package vp.seminarska.airplanemanagmentapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="flight")
public class Flight {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String origin;

    private String destination;

    private LocalDateTime departure_time;

    private LocalDateTime arrival_time;

    private LocalDate date_of_flight;

    @OneToOne
    private Airplane aircraft_assigned;

    private int passengers;

    private int crew;
}
