package com.lighthouse.project.Agen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lighthouse.project.KetersediaanUnit.TransaksiKetersediaanModel;
import com.lighthouse.project.KetersediaanUnit.KetersediaanRepo;
import com.lighthouse.project.KetersediaanUnit.KetersediaanService;
import com.lighthouse.project.Tower.TowerModel;
import com.lighthouse.project.Tower.TowerUnitModel;
import com.lighthouse.project.Tower.TowerUnitRepo;
import com.lighthouse.project.Tower.UnitModel;
import com.lighthouse.project.Transaksi.TransaksiModel;
import com.lighthouse.project.Transaksi.TransaksiRepo;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/atyp")
@Controller
public class AgenController {

    @Autowired
    private TowerUnitRepo towerRepo;
    
    @Autowired
    private KetersediaanRepo ketRepo;

    @Autowired
    private KetersediaanService ketService;

    @GetMapping("/")
    public String index(Model model, HttpSession httpSession) {

        model.addAttribute("tipeuser", "A");
        model.addAttribute("default", false);

        return "index";
    }

    @GetMapping("/edit-data")
    public String editData(Model model, HttpSession httpSession) {
        List<TowerUnitModel> units = towerRepo.findAllUnitJoinTowers();
        List<TowerModel> towers = towerRepo.findAllTowers();

        if(units.size()>0 && towers.size()>0){
            model.addAttribute("results", units);
            model.addAttribute("towers", towers);
        } 
        
        return "editData";
    }
    
    @GetMapping("/kelola-jadwal")
    public String kelolaJadwal(Model model) {
        List<TransaksiKetersediaanModel> results = ketRepo.findAll();

        if(results.size()>0){
            model.addAttribute("results", results);
        }


        return "kelolaJadwal";
    }

    @GetMapping("/pengawasan-cico")
    public String pengawsanCICO(Model model) {
        
        return "pengawasanCICO";
    }
    
    @GetMapping("/pengawasan-unit")
    public String pengawasanUnit(Model model, HttpSession httpSession) {


        List<TransaksiKetersediaanModel> list = (List<TransaksiKetersediaanModel>) httpSession.getAttribute("recentTransaction");             
        System.out.println("Service list size after storing: " + list.size());

        if(list.size()>0){
            model.addAttribute("results", list);
            // model.addAttribute("updated", true);
        }

        return "pengawasanUnit";
    }
    @GetMapping("/pelaporan-utilitas")
    public String pelaporanUtilitas(Model model) {

        return "pelaporanUtilitas";
    }

}
