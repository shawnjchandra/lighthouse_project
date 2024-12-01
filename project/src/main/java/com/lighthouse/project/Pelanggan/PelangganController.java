package com.lighthouse.project.Pelanggan;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.lighthouse.project.Tower.TowerUnitJDBC;
import com.lighthouse.project.Tower.TowerUnitModel;
// import com.lighthouse.project.Tower.TowerUnitRepo;
// import com.lighthouse.project.Tower.UnitModel;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lighthouse.project.Transaksi.TransaksiTowerUnitModel;

import jakarta.servlet.http.HttpSession;

import com.lighthouse.project.Other.PenggunaRepo;
import com.lighthouse.project.Transaksi.TransaksiModel;
import com.lighthouse.project.Transaksi.TransaksiRepo;

@Controller
@RequestMapping("/ptyp")
public class PelangganController {

    @Autowired
    private TransaksiRepo trRepo;

    @Autowired
    private PenggunaRepo penggunaRepo;


    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("tipeuser", "P");
        model.addAttribute("default", false);
        return "index";
    }

    @GetMapping("/riwayat")
    public String riwayat(HttpSession httpSession, Model model) {

        String nik = (String) httpSession.getAttribute("nik");

        if (nik == null) {
            String username = (String) httpSession.getAttribute("username");
            if (username != null) {
                nik = penggunaRepo.getUserNik(username);
                httpSession.setAttribute("nik", nik); // Cache nik
            }
        }

        List<TransaksiTowerUnitModel> list = trRepo.findRiwayatTransaksi(nik);
        model.addAttribute("results", list);

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
        // List<TowerUnitModel> units = towerRepo.findAllUnitJoinTowers();
        List<TowerUnitModel> units = 
          JDBC.findAllUnitJoinTowers();
        // List<UnitModel> units = towerRepo.findAllUnits();
        model.addAttribute("units", units);
        return "pencarian";
    }

    
    @GetMapping("/pemesanan")
    public String pesan(Model model) {

        return "pemesanan";
    }


    //TODO
    //Rencananya ini buat bikin pemesanan lanjutan dari pencarian. Jadi di pencarian pas cardnya di click, 
    //nanti data di pemesanan ngikutin data yang di card.
    // @GetMapping("/pemesanan")
    // public String pesan(@RequestParam int roomId, Model model) {
    //     List<TowerUnitModel> room = towerUnitJDBC.findUnitJoinTowersByUnitId(roomId);
    //     model.addAttribute("roomData", room);
    //     return "pemesanan";
    // }

    @GetMapping("/pembayaran")
    public String pembayaran(Model model) {

        return "pembayaran";
    }

    
    @PostMapping("/submitReview")
    public String submitReview
    (@RequestParam (name="rating", required = false) int rating,
    @RequestParam (name="review", required = false) String review,
    @RequestParam (name="trsk", required = false) int idtrsk,
    HttpSession httpSession) {

        System.out.println("Rating: " + rating + ", Review: " + review + ", ID: " + idtrsk);

        trRepo.saveReview(idtrsk, rating, review);

        return "riwayatTransaksi";
    }

}
