package com.lighthouse.project.Tower;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface TowerUnitRepo {
    List<UnitModel> findAllUnits();
    List<TowerModel> findAllTowers();
    List<TowerUnitModel> findAllUnitJoinTowers();

   
    List<TowerUnitModel> findUnitTowersByParams(String namatower, int lantai, int nomor);
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
    List<TowerUnitModel> findUnitJoinTowersByUnitId(int id);
}
