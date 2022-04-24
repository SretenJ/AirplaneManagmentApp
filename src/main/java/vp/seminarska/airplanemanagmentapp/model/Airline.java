package vp.seminarska.airplanemanagmentapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.sql.DataSourceDefinitions;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "airline")
@Entity
public class Airline {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "iata")
    private String IATA;

    @Column(name = "icao")
    private String ICAO;

    @Column(name = "country")
    private String Country;
}
