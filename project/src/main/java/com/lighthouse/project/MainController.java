package com.lighthouse.project;

// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    // @ResponseBody
    public String index(Model model) {
        model.addAttribute("name", "John Doe");
        model.addAttribute("isAuth", false);
        model.addAttribute("arrAngka", "1,2");
        model.addAttribute("item", "objek ke - ");
        return "index";
    }

    @GetMapping("/pencarian")
    public String penc(Model model) {

        return "pencarian";
    }

    @GetMapping("/edit-data")
    public String editData(Model model) {

        return "editData";
    }

    @GetMapping("/kelola-jadwal")
    public String kelolaJadwal(Model model) {

        return "kelolaJadwal";
    }

}
