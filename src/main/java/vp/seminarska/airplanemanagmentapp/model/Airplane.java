package vp.seminarska.airplanemanagmentapp.model;

import lombok.*;
import vp.seminarska.airplanemanagmentapp.model.enumerators.AirplaneType;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="airplane")
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "capacity")
    private int capacity;

    @Column(name="name")
    private String name;


    @Column(name = "iata")
    private String IATA;

    @Column(name = "icao")
    private String ICAO;

    @OneToMany
    private List<Seat> seats;

    @Column(name = "airplanetype")
    @Enumerated(EnumType.STRING)
    private AirplaneType type;

    @OneToMany(mappedBy = "airplane")
    private List<Flight> flightList;

}
