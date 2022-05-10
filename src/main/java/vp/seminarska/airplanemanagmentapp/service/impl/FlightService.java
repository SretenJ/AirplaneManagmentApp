package vp.seminarska.airplanemanagmentapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import vp.seminarska.airplanemanagmentapp.model.Flight;
import vp.seminarska.airplanemanagmentapp.repository.FlightRepository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

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
    public List<Flight> listFlightsByOrigin(String origin){ return flightRepository.findAllByOrigin(origin);}
    public List<Flight> listFlightsByDestination(String destination){ return flightRepository.findAllByDestination(destination);}
    public List<Flight> listFlightsByOriginAndDestination(String origin, String destination){ return flightRepository.findAllByOriginAndDestination(origin, destination);}

    public Flight delete(Long id) {
        Flight flight = this.flightRepository.findById(id).orElse(null);
        this.flightRepository.delete(flight);
        return flight;
    }

    public Flight  findById(Long id)
    {
        return this.flightRepository.findById(id).orElse(null);
    }

}
