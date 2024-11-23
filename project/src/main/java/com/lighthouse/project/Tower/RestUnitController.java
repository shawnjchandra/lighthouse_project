package com.lighthouse.project.Tower;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/atyp")
@RestController
public class RestUnitController {

    @Autowired
    private TowerUnitRepo repo;
    

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
        boolean isSuccessful = repo.changeUnitData(
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
    
        boolean isSuccessful = repo.addUnitData(new UnitModel(nomor, lantai, jenis, tarifsewa),tower);

        return validate(isSuccessful);
    }

    private ResponseEntity<Map<String,String>> validate(boolean isSuccessful){
        if (isSuccessful) {
            Map<String,String> response = new HashMap<>();
            response.put("message","Unit Changed" );
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("status", "ERROR");
            errorResponse.put("message", SQLException.class.toString());
            return ResponseEntity.ok(errorResponse);    
        }
    }

    
}
