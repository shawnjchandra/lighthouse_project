package com.lighthouse.project.Tower;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lighthouse.project.KetersediaanUnit.KetersediaanRepo;
import com.lighthouse.project.KetersediaanUnit.KetersediaanService;
import com.lighthouse.project.KetersediaanUnit.TransaksiKetersediaanModel;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/atyp")
@RestController
public class RestUnitController {

    @Autowired
    private TowerUnitRepo towerRepo;
    
    @Autowired
    private KetersediaanRepo ketRepo;

    @Autowired
    private KetersediaanService ketService;

    @PostMapping("/save-unit")
    public ResponseEntity<Map<String,String>> saveChanges(@RequestBody Map<String, Object> data) {
        String originalTower = (String) data.get("originalTower");
        int originalFloor =  Integer.valueOf((String)data.get("originalFloor"));
        int originalUnitNumber = Integer.valueOf((String)data.get("originalUnitNumber"));
        String originalUnitType = (String) data.get("originalUnitType");
        double originalPrice = Double.valueOf((String)data.get("originalPrice"));

        int editUnitNumber = Integer.valueOf((String)data.get("editUnitNumber"));
        String editUnitType = (String) data.get("editUnitType");
        double editPrice = Double.valueOf((String)data.get("editPrice"));

        // Perform the database update
        boolean isSuccessful = towerRepo.changeUnitData(
                        originalTower, originalFloor, originalUnitNumber, originalUnitType,originalPrice,
                        editUnitNumber, editUnitType, editPrice);

        // Return a response based on whether the update was successful
        return validate(isSuccessful);
    }

    @PostMapping("/add-unit-to-form")
    public ResponseEntity<Map<String,String>> addUnit(@RequestBody Map<String,Object> data){
        String tower = (String)data.get("tower");
        int nomor = Integer.valueOf((String) data.get("nomor"));
        int lantai = Integer.valueOf((String)data.get("lantai"));
        String jenis = (String)data.get("jenis");
        int tarifsewa = Integer.valueOf((String)data.get("tarifsewa"));
    
        boolean isSuccessful = towerRepo.addUnitData(new UnitModel(nomor, lantai, jenis, tarifsewa),tower);

        return validate(isSuccessful);
    }

    @PostMapping("/search-between-dates")
    public ResponseEntity<Map<String, String>> searchBetweenDates(@RequestBody Map<String,Object> data, HttpSession httpSession){
    
        System.out.println("1");
        
        String checkIn = data.get("checkIn").toString();
        
        String checkOut = data.get("checkOut").toString();

         
        
        List<TransaksiKetersediaanModel> list = ketRepo.findUsedBetweenDates(checkIn, checkOut);
        
        ketService.storeSearch(list);
        
        
        httpSession.setAttribute("recentTransaction", list);
        System.out.println(list);
        
      
            boolean isSuccessful = !list.isEmpty();
            Map<String,String> resp = Map.of("status", isSuccessful? "success" : "failed");
            return ResponseEntity.ok(resp);
  
    }

    private ResponseEntity<Map<String,String>> validate(boolean isSuccessful){
        if (isSuccessful) {
            Map<String,String> response = new HashMap<>();
            response.put("message","Succesful" );
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", SQLException.class.toString());
            return ResponseEntity.ok(errorResponse);    
        }
    }

    
}
