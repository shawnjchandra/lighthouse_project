package com.lighthouse.project.Pelanggan;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.lighthouse.project.Tower.TowerModel;

import com.lighthouse.project.Tower.TowerUnitJDBC;
import com.lighthouse.project.Tower.TowerUnitModel;
// import com.lighthouse.project.Tower.TowerUnitRepo;
// import com.lighthouse.project.Tower.UnitModel;
import com.lighthouse.project.Tower.TowerUnitRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

import com.lighthouse.project.KetersediaanUnit.TransaksiKetersediaanModel;
import com.lighthouse.project.Other.PenggunaModel;
import com.lighthouse.project.Other.PenggunaRepo;
import com.lighthouse.project.Transaksi.*;

@Controller
@RequestMapping("/ptyp")
public class PelangganController {

    @Autowired
    private TransaksiRepo trRepo;

    @Autowired
    private PenggunaRepo penggunaRepo;

    @Autowired
    private TowerUnitRepo towerUnitRepo;


    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("tipeuser", "P");
        model.addAttribute("default", false);
        return "index";
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
        // List<TowerUnitModel> units = towerUnitRepo.findAllUnitJoinTowers();
        // List<TransaksiTowerUnitModel> units = trRepo.findAllTTU();
        List<TTUWithRevRat> units = trRepo.findAllTTUREVRAT();
        Map<String,int[]> ratingMean = new HashMap<>();
        
        for(TTUWithRevRat unit : units){
            String kode = unit.getNamatower()+""+unit.getLantai()+""+unit.getNomor();

            boolean bypass = true;
            if(unit.getRating()>0) bypass=false; 

            

            if(!ratingMean.containsKey(kode) && !bypass ){
                int[] counterAndMean = {0,0};

                counterAndMean[0]++;
                counterAndMean[1] += unit.getRating();
                ratingMean.put(kode, counterAndMean);
            }else if(ratingMean.containsKey(kode) && !bypass ){
                int[] counterAndMean = ratingMean.get(kode);
                
                counterAndMean[0]++;
                counterAndMean[1] += unit.getRating();
                ratingMean.put(kode, counterAndMean);

            }

        }
        for(TTUWithRevRat unit : units){
            if(ratingMean.isEmpty()) break;

            String kode = unit.getNamatower()+""+unit.getLantai()+""+unit.getNomor();
            if(ratingMean.containsKey(kode)){
                int[] counterAndMean = ratingMean.get(kode);
                int counter = counterAndMean[0];
                int ratingTotal = counterAndMean[1];
                int mean = ratingTotal/counter;
                
                unit.setRating(mean);
            }

        }


        model.addAttribute("units", units);
        return "pencarian";
    }


    @PostMapping("/filter-pencarian")
    public String filter(
        @RequestParam(name="tanggal",required = false) String tanggal,
        @RequestParam(name="tarifsewa",required = false, defaultValue = "10000000") String tarifsewa,
        @RequestParam(name="ratingMin",required = false, defaultValue = "0") String ratingMin,
        @RequestParam(name="review",required = false) String review,
        Model model, HttpSession httpSession
    ){  
        Map<String,Object> filterPencarian = new HashMap<>();

        if (tanggal != null && !tanggal.equals("")) {
                filterPencarian.put("tanggal", tanggal);
                
            }else{
                tanggal = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

                System.out.println("converter tanggal "+tanggal);
                filterPencarian.put("tanggal", tanggal);
        }

        if (tarifsewa  !=  null) {;
            filterPencarian.put("tarifsewa",tarifsewa); 
            
        }

        System.out.println(tarifsewa);
    
        if(ratingMin != null){
            filterPencarian.put("ratingMin", ratingMin);

        }
        if (review != null && !review.equals("")) {
            filterPencarian.put("review", review);

        }
         
        List<TTUWithRevRat> list = trRepo.findWithFiltersSearch(filterPencarian);
        System.out.println(list);


            if(list.size()>0){
                model.addAttribute("units", list);
                model.addAttribute("filterPencarian", filterPencarian);
                httpSession.setAttribute("filterPencarian", filterPencarian);
            }


        return "pencarian";
    }

    @PostMapping("/select-room")
    public String selectRoom(
        @RequestParam(name="nomor",required = false) String nomor,
        @RequestParam(name="namatower",required = false) String namatower,
        @RequestParam(name="lantai",required = false) String lantai,
        @RequestParam(name="jenis",required = false) String jenis,
        HttpSession httpSession,
        Model model){

            System.out.println(nomor);
            System.out.println(namatower);
            System.out.println(lantai);
            System.out.println(jenis);
                
            Map<String,Object> roomData = new HashMap<>();
            if(nomor!=null){
                roomData.put("nomor", nomor);
            }
            if(namatower!=null){
                roomData.put("namatower", namatower);
            }
            if(lantai!=null){
                roomData.put("lantai", lantai);
            }
            if(jenis!=null){
                roomData.put("jenis", jenis);
            }

            int intLantai = Integer.valueOf(lantai);
            int intNomor = Integer.valueOf(nomor);

            List<TowerUnitModel> listOfUnit = towerUnitRepo.findUnitTowersByParams(namatower, intLantai, intNomor);
            TowerUnitModel unit = listOfUnit.get(0);
            
            model.addAttribute("unit", unit);
            model.addAttribute("roomData", roomData);

            httpSession.setAttribute("dataUnit", unit);
            
            
            return "pemesanan";
          
        

    }

    @GetMapping("/pemesanan")
    public String pesan(
        @RequestParam(name="roomData") HashMap<String, Object> roomData,
     
        Model model) {


            // String namatower = "", jenis = "";
            // int nomor=0, lantai =0; 

            // if(roomData.containsKey("namatower")){
            //     namatower = String.valueOf(roomData.get("namatower"));   
            // }
            // if(roomData.containsKey("jenis")){
            //     jenis = String.valueOf(roomData.get("jenis"));   
            // }
            // if(roomData.containsKey("nomor")){
            //     nomor = Integer.valueOf(String.valueOf(roomData.get("nomor")));   
            // }
            // if(roomData.containsKey("lantai")){
            //     lantai = Integer.valueOf(String.valueOf(roomData.get("lantai")));   
            // }

            // model.addAttribute("roomData", roomData);
            // model.addAttribute("unit", unit);

        // model.addAttribute("", roomData)

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

    

    @PostMapping("/booking")
    public String booking(
        @RequestParam(name="checkIn") String checkIn,
        @RequestParam(name="checkOut") String checkOut,
        Model model,HttpSession httpSession
    ){
        System.out.println("Booking checkin : "+ checkIn);
        System.out.println("Booking checkOut : "+ checkOut);
        String username = (String)httpSession.getAttribute("username");

        Optional<PenggunaModel> user = penggunaRepo.getUserByUsername(username);
        PenggunaModel pelanggan = user.get();
        
        // System.out.println(pelanggan);

        model.addAttribute("pelanggan", pelanggan);
        System.out.println("setelah post mapping : "+ pelanggan);
        httpSession.setAttribute("dataCheckIn", checkIn);
        httpSession.setAttribute("dataCheckOut", checkOut);
        httpSession.setAttribute("pelanggan", pelanggan);
        


        return "pembayaran";
    }

    // @GetMapping("/pembayaran")
    // public String pembayaran(
    //     Model model,HttpSession httpSession
    //     ) {
       
    //         // System.out.println("masuknya ke sini");
    //         // String username = (String)httpSession.getAttribute("username");

    //         // System.out.println(httpSession.getAttribute("dataCheckIn"));
    //         // System.out.println(httpSession.getAttribute("dataCheckOut"));


    //         // Optional<PenggunaModel> user = penggunaRepo.getUserByUsername(username);
    //         // PenggunaModel pelanggan = user.get();
            
    //         // System.out.println(pelanggan);
    
    //         // model.addAttribute("pelanggan", pelanggan);
            
    //         // // httpSession.setAttribute("dataCheckIn", checkIn);
    //         // // httpSession.setAttribute("dataCheckOut", checkOut);
    //         // httpSession.setAttribute("pelanggan", pelanggan);
    //     return "pembayaran";
    // }

    @PostMapping("/acc")
    public String accPembayaran(
        @RequestParam(name="paymentOption") String statuspembayaran,
        HttpSession httpSession, Model model){
            TowerUnitModel unit = (TowerUnitModel) httpSession.getAttribute("dataUnit");
            PenggunaModel pelanggan = (PenggunaModel) httpSession.getAttribute("pelanggan");

            String checkIn = String.valueOf(httpSession.getAttribute("dataCheckIn"));
            String checkOut = String.valueOf(httpSession.getAttribute("dataCheckOut"));

            httpSession.setAttribute("statuspembayaran", statuspembayaran);

            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            boolean isSuccessful = trRepo.addTransaction(checkIn, checkOut, pelanggan, unit, statuspembayaran);
            
            if(isSuccessful){
                return "redirect:/ptyp/riwayat";
            }else{
                return "pembayaran";
            }
            
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

        List<TTUWithRevRat> list = trRepo.findTTURevRatsWithNIK(nik);

        for(TTUWithRevRat unit : list){
            System.out.println(unit.getReview());
        }


        model.addAttribute("results", list);


        return "riwayatTransaksi";
    }


    @PostMapping("/submitReview")
    public String submitReview
    (
        @RequestParam (name="rating", required = false) int rating,
        @RequestParam (name="review", required = false) String review,
        @RequestParam (name="idtrsk", required = false) int idtrsk, 
    
    
        // @RequestParam (name="trsk", required = false) int idtrsk,

    HttpSession httpSession) {

        // System.out.println("Rating: " + rating + ", Review: " + review + ", ID: " + idtrsk);

        trRepo.saveReview(idtrsk, rating, review);

        return "redirect:/ptyp/riwayat";
    }

}
