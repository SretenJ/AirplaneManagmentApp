package vp.seminarska.airplanemanagmentapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vp.seminarska.airplanemanagmentapp.model.Flight;
import vp.seminarska.airplanemanagmentapp.repository.FlightRepository;

import java.util.Date;
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
    public List<Flight> listFlightsByOrigin(String origin){ return flightRepository.findAllByOrigin(origin);}
    public List<Flight> listFlightsByDestination(String destination){ return flightRepository.findAllByDestination(destination);}
   // public List<Flight> listFlightsByDestAndTime(String destination, Date date){ return flightRepository.findAllByDestinatioAndDeparture_timeBefore(destination, date);}
   // public List<Flight> listFlightsByOriginAndTime(String origin, Date date){ return flightRepository.findAllByOriginAndDeparture_timeBefore(origin, date);}
    public List<Flight> listFlightsByOriginAndDestination(String origin, String destination){ return flightRepository.findAllByOriginAndDestination(origin, destination);}

    public Flight delete(Long id) {
        Flight flight = this.flightRepository.findById(id).orElse(null);
        this.flightRepository.delete(flight);
        return flight;
    }
}
