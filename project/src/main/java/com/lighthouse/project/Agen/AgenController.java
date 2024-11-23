package com.lighthouse.project.Agen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lighthouse.project.Tower.TowerModel;
import com.lighthouse.project.Tower.TowerUnitModel;
import com.lighthouse.project.Tower.TowerUnitRepo;
import com.lighthouse.project.Tower.UnitModel;

@RequestMapping("/atyp")
@Controller
public class AgenController {

    @Autowired
    private TowerUnitRepo repo;

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("tipeuser", "A");
        model.addAttribute("default", false);

        return "index";
    }

    @GetMapping("/edit-data")
    public String editData(Model model) {
        List<TowerUnitModel> units = repo.findAllUnitJoinTowers();
        List<TowerModel> towers = repo.findAllTowers();

        if(units.size()>0 && towers.size()>0){
            model.addAttribute("results", units);
            model.addAttribute("towers", towers);
        } 
        
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
