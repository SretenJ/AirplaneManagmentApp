package vp.seminarska.airplanemanagmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vp.seminarska.airplanemanagmentapp.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

}
