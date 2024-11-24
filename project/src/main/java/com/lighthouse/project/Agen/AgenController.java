package com.lighthouse.project.Agen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.lighthouse.project.Transaksi.PenggunaTransaksiModel;
import com.lighthouse.project.Transaksi.TransaksiModel;
import com.lighthouse.project.Transaksi.TransaksiRepo;
import com.lighthouse.project.Transaksi.TransaksiTowerUnitModel;
import com.lighthouse.project.Transaksi.UtilitasModel;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/atyp")
@Controller
public class AgenController {

    @Autowired
    private TowerUnitRepo towerRepo;
    
    @Autowired
    private KetersediaanRepo ketRepo;

    @Autowired
    private TransaksiRepo trRepo;

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

    @PostMapping("/filter-apartments")
    public String filteKetersediaan(
        @RequestParam(name="apt", required = false) String apt,
        @RequestParam(name="start-date", required = false) String startDate,
        @RequestParam(name="end-date",required = false) String endDate,
        @RequestParam(name="status",required = true, defaultValue = "") String status,
        Model model, HttpSession httpSession
        ){

            
            Map<String,Object> filters = new HashMap<>();
            System.out.println("APARTMENT : "+apt);
            System.out.println();
            if (apt != null) {
                filters.put("apt", apt.trim());
                // httpSession.setAttribute("apt", apt);
            }
            if (status != null) {
                status = status.replaceAll(",", "");
                // System.out.println("STATUS"+);;
                // System.out.println("FILTER "+String.valueOf(filters.get("status")).replaceAll("\\s", "").replaceAll(",", ""));
                
                filters.put("status",status); 
                // httpSession.setAttribute("status", status);
                
            }

            if (startDate != null && endDate != null) {
                filters.put("startDate", startDate.trim());
                filters.put("endDate", endDate.trim());
                // httpSession.setAttribute("startDate", startDate);
                // httpSession.setAttribute("status", status);
            }

            
            List<TransaksiKetersediaanModel> list = ketRepo.findWithFilters(filters);


            if(list.size()>0){
                model.addAttribute("results", list);
                model.addAttribute("filters", filters);
                httpSession.setAttribute("filters", filters);
            }


            return "kelolaJadwal";
    }

    @GetMapping("/pengawasan-cico")
    public String pengawsanCICO(Model model) {
        List<PenggunaTransaksiModel> list = trRepo.findAllUserTransaction();

        if(list.size()>0){
            model.addAttribute("default", true);
        }
        
        return "pengawasanCICO";
    }
    
    @PostMapping("/search-date")
    public String cicoDate(@RequestParam String date, Model model){
        List<PenggunaTransaksiModel> ci = trRepo.findUTByCheckIn(date);
        List<PenggunaTransaksiModel> co = trRepo.findUTByCheckOut(date);
        
        model.addAttribute("default", false);
        if(ci.size()>0){
            model.addAttribute("ci", ci);
        }
        if(co.size()>0){
            model.addAttribute("co", co);

        }
        
        return "pengawasanCICO";
    }
    
    @GetMapping("/pengawasan-unit")
    public String pengawasanUnit(Model model, HttpSession httpSession) {


        // List<TransaksiKetersediaanModel> list = (List<TransaksiKetersediaanModel>) httpSession.getAttribute("recentTransaction");             

        List<TransaksiKetersediaanModel> list = ketRepo.findAll();

        System.out.println("pengawasanUnit "+ list);
        if(list.size()>0){
            model.addAttribute("results", list);
            model.addAttribute("default", true);
            
        }
        return "pengawasanUnit";
    }
    
    @PostMapping("/between-dates")
    public String betweenDates(@RequestParam String checkIn, @RequestParam String checkOut, Model model){
        List<TransaksiKetersediaanModel> list = ketRepo.findUsedBetweenDates(checkIn, checkOut);
        
        System.out.println("between "+ list);
        if(list.size()>0){

            model.addAttribute("results", list);
            model.addAttribute("default", false);
        }
        
        
        return "pengawasanUnit";
    }



    @GetMapping("/pelaporan-utilitas")
    public String pelaporanUtilitas(Model model) {
        // Get all TransaksiTowerUnitModel records
    List<TransaksiTowerUnitModel> list = trRepo.findAllTTU();

    // Initialize maps to track frequencies, unit pricing, and mapping
    Map<String, Integer> frekuensi = new HashMap<>();
    Map<String, Integer> hargaSatuan = new HashMap<>();
    Map<String, TransaksiTowerUnitModel> ttuMapping = new HashMap<>();

    // Populate the maps
    for (TransaksiTowerUnitModel ttu : list) {
        String unit = ttu.getNamatower() + "-" + ttu.getLantai() + "-" + ttu.getNomor();
        if (!frekuensi.containsKey(unit)) {
            frekuensi.put(unit, 1);
            hargaSatuan.put(unit, ttu.getTarifsewa());
            ttuMapping.put(unit, ttu);
        } else {
            frekuensi.put(unit, frekuensi.get(unit) + 1);
        }
    }

    // Create the results list
    List<UtilitasModel> results = new ArrayList<>();
    for (String key : frekuensi.keySet()) {
        int count = frekuensi.get(key);
        int tarif = hargaSatuan.get(key);
        int total = count * tarif;

        // Add new UtilitasModel to the results list
        results.add(new UtilitasModel(ttuMapping.get(key), count, total));
    }

    int totalSum = results.stream().mapToInt(UtilitasModel::getTotal).sum();
    // Add results to the model for Thymeleaf rendering
    if (!results.isEmpty()) {
        System.out.println(totalSum);
        model.addAttribute("results", results);
        model.addAttribute("total", totalSum);
    }
        
        return "pelaporanUtilitas";
    }

}
