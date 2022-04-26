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
import vp.seminarska.airplanemanagmentapp.repository.UserRepository;

@Controller
public class WebController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String showRooms(){
        return "index";
    }

    @RequestMapping("/login.html")
    public String login(WebRequest request)
    {
        return "login.html";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }
    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "register_success";
    }
}
