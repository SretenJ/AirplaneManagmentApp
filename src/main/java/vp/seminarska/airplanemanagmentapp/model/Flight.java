package vp.seminarska.airplanemanagmentapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.beans.Transient;
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

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name="airplane_id")
    private Airplane airplane;

    private int passengers;

    private int crew;

    @Transient
    public Airplane getAirplane()
    {
        return this.airplane;
    }

}
