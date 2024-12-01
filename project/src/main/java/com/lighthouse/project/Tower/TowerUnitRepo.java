package com.lighthouse.project.Tower;

import java.util.List;
import java.util.Optional;

public interface TowerUnitRepo {
    List<UnitModel> findAllUnits();
    List<TowerModel> findAllTowers();
    List<TowerUnitModel> findAllUnitJoinTowers();
    boolean changeUnitData
    (   
        String originalTower,
        int originalFloor,
        int originalUnitNumber,
        String originalUnitType,
        double originalPrice,

        // int editFloor,
        int editUnitNumber,
        String editUnitType,
        double editPrice);

    boolean addUnitData(UnitModel unit, String tower);
    Optional<TowerModel> findTowerById(String id);
    
    //TODO
    //Tambahan buat page pemesanan, alasan ada di PelangganController
    // List<TowerUnitModel> findUnitJoinTowersByUnitId(int id);
}
