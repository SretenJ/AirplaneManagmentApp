package vp.seminarska.airplanemanagmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import vp.seminarska.airplanemanagmentapp.model.Flight;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findAllByOrigin(String origin);
    List<Flight> findAllByDestination(String destination);
    List<Flight> findAllByOriginAndDestination(String origin, String destination);
  //  List<Flight> findAllByOriginAndDeparture_timeBefore(String origin, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departure_time);
//    List<Flight> findAllByDestinatioAndDeparture_timeBefore(String destination, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date departure_time);
}
