package com.lighthouse.project.Other;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.lighthouse.project.Other.PenggunaRepo;

// import groovyjarjarantlr4.v4.parse.ANTLRParser.parserRule_return;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
    

    @Autowired
    private PenggunaRepo repo;

    @Autowired
    private AlamatRepo alamatRepo;

    @GetMapping("/")
    public String index(@RequestParam(name = "tipeuser",defaultValue = "G",required = false) String tipe,
    Model model,
    HttpSession httpSession) {

        if(httpSession.getAttribute("tipeuser") != null){
            return "redirect:/"+httpSession.getAttribute("tipeuser") +"/";
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
        List<KecamatanModel> kecamatan = alamatRepo.findAllKecamatan();

        model.addAttribute("kecamatan", kecamatan);

        return "register";
    }

    @GetMapping("/update-kelurahan")
    @ResponseBody
    public List<KelurahanModel> getKelurahan(
        @RequestParam(name = "namaKecamatan") String namaKecamatan,
        Model model){

            System.out.println(namaKecamatan);

            int idKec = alamatRepo.getIDKecByName(namaKecamatan);

            return alamatRepo.findAllKelurahanByIDKec(idKec);


    }

    @GetMapping("/signout")
    public String signout(HttpSession session) {
    // Invalidate the session to clear user data
        session.invalidate();
    

        return "redirect:/";
    }


    @PostMapping("/login-data")
    public String log(String username, String password, HttpSession httpSession,Model model){
        boolean isSuccess = repo.login(username, password);
        
        
        if(isSuccess){
            String userType = repo.getUserType(username);
            String nik = repo.getUserNik(username); // Fetch nik
            
            userType = userType.equals("Agen") ? "atyp" : "ptyp" ;

            httpSession.setAttribute("tipeuser", userType);
            httpSession.setAttribute("username", username);
            httpSession.setAttribute("nik", nik);

  

            return "redirect:/"+userType+"/";
        }


        return "login";
    }

    @PostMapping("/register-data")
    public String reg(
    String nik, 
    String nama, 
    String nohp, 
    String emailAddress,
    String alamat, 
    String namaKelurahan,
    String username,
    String password,
    String confirmPassword,
    HttpSession httpSession, Model model){
        System.out.println("++++++++++++++++++++++");
        System.out.println(nik);
        System.out.println(nama);
        System.out.println(nohp);
        System.out.println(emailAddress);
        System.out.println(alamat);
        System.out.println(namaKelurahan);
        System.out.println(username);
        System.out.println(password);
        System.out.println(confirmPassword);
        System.out.println("++++++++++++++++++++++");
        
        boolean isSuccess = 
        repo.register(nik, nama, nohp, emailAddress, alamat, namaKelurahan, username, password, confirmPassword);
        
        if(isSuccess){
            String userType = "ptyp";
            // httpSession.invalidate();

            httpSession.setAttribute("tipeuser", userType);
            httpSession.setAttribute("username", username);
            
            return "redirect:/"+httpSession.getAttribute("tipeuser")+"/";
        }

        return "register";
    }

}
