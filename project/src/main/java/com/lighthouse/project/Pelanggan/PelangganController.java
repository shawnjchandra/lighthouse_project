package com.lighthouse.project.Pelanggan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ptyp")
public class PelangganController {

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("tipeuser", "P");
        model.addAttribute("default", false);
        return "index";
    }

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

    
    @GetMapping("/pemesanan")
    public String pesan(Model model) {

        return "pemesanan";
    }
    @GetMapping("/pembayaran")
    public String pembayaran(Model model) {

        return "pembayaran";
    }
    
}
