package vp.seminarska.airplanemanagmentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vp.seminarska.airplanemanagmentapp.model.Airplane;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane,Long> {

}
