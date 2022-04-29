package vp.seminarska.airplanemanagmentapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import vp.seminarska.airplanemanagmentapp.model.User;
import vp.seminarska.airplanemanagmentapp.service.impl.UserDetailsServiceImpl;

import javax.validation.Valid;

@Controller
public class WebController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String showRooms(){
        return "index";
    }

    @GetMapping ("/login")
    public String login(Model model)
    {
        return "login";
    }
//    @PostMapping("/login")
//    public String login(@Valid User user)
//    {
//        if (userDetailsService.login(user))
//            return "redirect:index";
//        else
//            return "loginerorr";
//    }

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
    public String showFlights(Model model) {
        model.addAttribute("user", new User());
        return "listflights.html";
    }
}
