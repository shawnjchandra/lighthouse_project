package com.lighthouse.project;

// import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    // @ResponseBody
    public String index(@RequestParam(name = "tipeuser",defaultValue = "none",required = false) String tipe,Model model) {

        if(tipe.equals("none")){
            model.addAttribute("default", true);
        }else {
            model.addAttribute("default", false);

        }
        model.addAttribute("tipeuser", tipe);
        return "index";
    }




    // Pelanggan
    @GetMapping("/pencarian")
    public String penc(Model model) {

        return "pencarian";
    }

    //Butuh Riwayat Transaksi

    @GetMapping("/cico")
    public String cico(Model model) {

        return "CheckInCheckOut";
    }

    @GetMapping("/review")
    public String review(Model model) {

        return "review";
    }


    // Agen

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
    // Tinggal Pelaporan Utilitas dan Pengawasan Unit
    
    
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
