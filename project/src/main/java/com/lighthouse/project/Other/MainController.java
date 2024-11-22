package com.lighthouse.project.Other;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.lighthouse.project.Other.PenggunaRepo;

import groovyjarjarantlr4.v4.parse.ANTLRParser.parserRule_return;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    

    @Autowired
    private PenggunaRepo repo;

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

    @PostMapping("/login-data")
    public String log(Model model){
        String output ="";
        return output;
    }

    @PostMapping("/register-data")
    public String reg(String nik, String nama, String nohp, String emailAddress, String username, String password, String confirmPassword, HttpSession httpSession, Model model){
        boolean isSuccess = repo.register(nik, nama, nohp, emailAddress, username, password, confirmPassword);
        
        if(isSuccess){
            String userType = "Pelanggan";
            httpSession.setAttribute("tipeUser", userType );
            httpSession.setAttribute("username", username);
            
            return "redirect:/ptyp/";
        }

        return "register";
    }

}