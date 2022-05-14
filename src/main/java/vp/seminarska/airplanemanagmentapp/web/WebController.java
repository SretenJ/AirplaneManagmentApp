package vp.seminarska.airplanemanagmentapp.web;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.util.StringUtils;
import vp.seminarska.airplanemanagmentapp.model.Flight;
import vp.seminarska.airplanemanagmentapp.model.User;
import vp.seminarska.airplanemanagmentapp.service.AirPlaneService;
import vp.seminarska.airplanemanagmentapp.service.impl.FlightService;
import vp.seminarska.airplanemanagmentapp.service.impl.GeocodingService;
import vp.seminarska.airplanemanagmentapp.service.impl.UserDetailsServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

@Controller
@CrossOrigin
public class WebController {
    @Autowired
    ServletContext servletContext;
    private final TemplateEngine templateEngine;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private FlightService flightService;
    @Autowired
    private AirPlaneService airPlaneService;
    @Autowired
    private GeocodingService geocodingService;

    public WebController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

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
    public String viewDetails(@PathVariable Long id, Model model) throws JSONException, SQLException, URISyntaxException {
        Flight flight = this.flightService.findById(id);
        List<String> originCoords = geocodingService.LatLngParse(flight.getOrigin());
        List<String> destCoords = geocodingService.LatLngParse(flight.getDestination());
        System.out.println(originCoords);
        System.out.println(destCoords);
        model.addAttribute("flight",flight);
        model.addAttribute("origin",originCoords);
        model.addAttribute("destination",destCoords);
        return "flightdetails";
    }
    @GetMapping("/search")
    public String showSearch(Model model){
        return "search.html";
    }

    @RequestMapping(path = "flights/{id}/ticket-pdf")
    public ResponseEntity<?> getPDF(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        /* Do Business Logic*/

        Flight flight = flightService.findById(id);

        /* Create HTML using Thymeleaf template Engine */

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("flight", flight);

        String orderHtml = templateEngine.process("htmltopdf", context);

        /* Setup Source and target I/O streams */

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        /*Setup converter properties. */
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");

        /* Call convert method */
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();


        /* Send the response as downloadable PDF */

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);

    }
}
