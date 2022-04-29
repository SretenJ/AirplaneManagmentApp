package vp.seminarska.airplanemanagmentapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vp.seminarska.airplanemanagmentapp.model.Flight;
import vp.seminarska.airplanemanagmentapp.repository.FlightRepository;

import java.util.List;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;
    public List<Flight> listAllFlights()
    {
        return flightRepository.findAll();
    }
    public void save(Flight f)
    {
        flightRepository.save(f);
    }
}
