package vp.seminarska.airplanemanagmentapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="flight")
public class Flight {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String origin;

    private String destination;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departure_time;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrival_time;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_of_flight;

    @ManyToOne
    private Airplane aircraft_assigned;

    private int passengers;

    private int crew;
}
