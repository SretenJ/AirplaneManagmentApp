package vp.seminarska.airplanemanagmentapp.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String showRooms(){
        return "index";
    }


}
