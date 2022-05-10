package vp.seminarska.airplanemanagmentapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import vp.seminarska.airplanemanagmentapp.model.Flight;
import vp.seminarska.airplanemanagmentapp.model.User;
import vp.seminarska.airplanemanagmentapp.service.AirPlaneService;
import vp.seminarska.airplanemanagmentapp.service.impl.FlightService;
import vp.seminarska.airplanemanagmentapp.service.impl.UserDetailsServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
@CrossOrigin
public class WebController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private FlightService flightService;
    @Autowired
    private AirPlaneService airPlaneService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String showRooms(){
        return "index";
    }

    @GetMapping ("/login")
    public String login(Model model)
    {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }
    @PostMapping("/register")
    public String processRegister(@Valid User user) {

        userDetailsService.save(user);

        return "register_success";
    }
    @GetMapping("/flights")
    public String showFlights(@RequestParam(required = false) String originSearch,
                              @RequestParam(required = false) String DestinationSearch,
                              Model model) {
        List<Flight> flightList;
        if (StringUtils.isEmpty(originSearch) && StringUtils.isEmpty(DestinationSearch))
        {
            flightList=this.flightService.listAllFlights();
        }
        else if (!StringUtils.isEmpty(originSearch) && StringUtils.isEmpty(DestinationSearch))
        {
            flightList=this.flightService.listFlightsByOrigin(originSearch);
        }
        else if (StringUtils.isEmpty(originSearch) && !StringUtils.isEmpty(DestinationSearch))
        {
            flightList=this.flightService.listFlightsByDestination(DestinationSearch);
        }
        else {
             flightList=this.flightService.listFlightsByOriginAndDestination(originSearch,DestinationSearch);
        }
        model.addAttribute("user", new User());
        model.addAttribute("flights",flightList);
        return "listflights";
    }

    @GetMapping("/addflight")
    public String addFlight(Model model) {
        model.addAttribute("flight", new Flight());
        model.addAttribute("airplanes",this.airPlaneService.listAllAirplanes());
        return "addflight";
    }

    @PostMapping("/addflight")
    public String addFlight(@Valid Flight flight, Model model) {
        model.addAttribute("airplanes",this.airPlaneService.listAllAirplanes());
        model.addAttribute("flight", flight);
        flightService.save(flight);
        return "redirect:flights";
    }
    @PostMapping("/flights/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.flightService.delete(id);
        return "redirect:/flights";
    }
    @GetMapping("/flights/{id}/details")
    public String viewDetails(@PathVariable Long id, Model model)
    {
        model.addAttribute("flight",this.flightService.findById(id));
        return "flightdetails";
    }
    @GetMapping("/search")
    public String showSearch(Model model){
        return "search.html";
    }
}
