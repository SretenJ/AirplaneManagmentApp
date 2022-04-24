package vp.seminarska.airplanemanagmentapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "airport")
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "city")
    private String City;

    @Column(name="name")
    private String name;

    @Column(name = "country")
    private String Country;

    @Column(name = "iata")
    private String IATA;

    @Column(name = "icao")
    private String ICAO;


}
