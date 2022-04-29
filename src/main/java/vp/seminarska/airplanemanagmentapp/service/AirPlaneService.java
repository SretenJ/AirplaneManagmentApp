package vp.seminarska.airplanemanagmentapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vp.seminarska.airplanemanagmentapp.model.Airplane;
import vp.seminarska.airplanemanagmentapp.model.Flight;
import vp.seminarska.airplanemanagmentapp.repository.AirplaneRepository;

import java.util.List;

@Service
public class AirPlaneService{
    @Autowired
    AirplaneRepository airplaneRepository;
    public List<Airplane> listAllAirplanes()
    {
        return airplaneRepository.findAll();
    }
}
