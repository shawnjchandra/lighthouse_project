package com.lighthouse.project.Pelanggan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ptyp")
public class PelangganController {

    @GetMapping("/riwayat")
    public String riwayat(Model model) {

        return "riwayatTransaksi";
    }
    @GetMapping("/cico")
    public String cico(Model model) {

        return "CheckInCheckOut";
    }

    @GetMapping("/review")
    public String review(Model model) {

        return "review";
    }
    @GetMapping("/pencarian")
    public String penc(Model model) {

        return "pencarian";
    }
    
}
