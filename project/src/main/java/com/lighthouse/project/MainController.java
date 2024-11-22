package com.lighthouse.project;

// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(@RequestParam(name = "tipeuser",defaultValue = "none",required = false) String tipe,Model model) {

        if(tipe.equals("none")){
            model.addAttribute("default", true);
        }else {
            model.addAttribute("default", false);

        }
        model.addAttribute("tipeuser", tipe);
        return "index";
    }
    
    // Fitur umum
    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {

        return "register";
    }



}
