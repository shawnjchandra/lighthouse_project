package com.lighthouse.project.Agen;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/atyp")
@Controller
public class AgenController {
    
    
    @GetMapping("/edit-data")
    public String editData(Model model) {

        return "editData";
    }
    
    @GetMapping("/kelola-jadwal")
    public String kelolaJadwal(Model model) {

        return "kelolaJadwal";
    }

    @GetMapping("/pengawasan-cico")
    public String pengawsanCICO(Model model) {
        
        return "pengawasanCICO";
    }
    
    @GetMapping("/pemesanan")
    public String pesan(Model model) {

        return "pemesanan";
    }
    @GetMapping("/pembayaran")
    public String pembayaran(Model model) {

        return "pembayaran";
    }
    @GetMapping("/pengawasan-unit")
    public String pengawasanUnit(Model model) {

        return "pengawasanUnit";
    }
    @GetMapping("/pelaporan-utilitas")
    public String pelaporanUtilitas(Model model) {

        return "pelaporanUtilitas";
    }

}
