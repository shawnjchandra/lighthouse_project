package com.lighthouse.project.Tower;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lighthouse.project.Other.PenggunaModel;

@Repository
public class TowerUnitJDBC implements TowerUnitRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UnitModel> findAllUnits() {
        String sql = "SELECT * FROM unit";
        return jdbcTemplate.query(sql, this::mapRowToUnit);
    }

    private UnitModel mapRowToUnit(ResultSet rSet, int rowNum)throws SQLException{
        return new UnitModel(
            rSet.getInt("nomor"),
            rSet.getInt("lantai"),
            rSet.getString("jenis"),
            rSet.getInt("tarifsewa")

        );
    }

    @Override
    public List<TowerModel> findAllTowers() {
        String sql = "SELECT * FROM tower";
        return jdbcTemplate.query(sql, this::mapRowToTowers);
    }

    private TowerModel mapRowToTowers(ResultSet rSet, int rowNum)throws SQLException{
        return new TowerModel(
            rSet.getInt("idtower"),
            rSet.getString("namatower")
        );
    }

    @Override
    public boolean changeUnitData(
        String originalTower,
        int originalFloor,
        int originalUnitNumber,
        String originalUnitType,
        double originalPrice,

        // int editFloor,
        int editUnitNumber,
        String editUnitType,
        double editPrice
        ) {

        String sql = "UPDATE unit\n"+ 
                    "SET nomor = ? , jenis = ?::tipe, tarifsewa = ?\n"+
                    "WHERE nomor = ? and lantai = ? and jenis = ?::tipe and tarifsewa = ?;";
        int isSuccessful = jdbcTemplate.update(sql, editUnitNumber, editUnitType,editPrice, 
                                originalUnitNumber, originalFloor, originalUnitType, originalPrice);



        return isSuccessful > 0 ? true : false;
    }

    @Override
    public List<TowerUnitModel> findAllUnitJoinTowers() {
        String sql = "select * \n" + //
                        "from unit as u\n" + //
                        "inner JOIN tower as t on  u.idtower = t.idtower;";
        return jdbcTemplate.query(sql, this::mapRowToTowerUnit);
        
    }

    private TowerUnitModel mapRowToTowerUnit(ResultSet rSet, int rowNum)throws SQLException{
        return new TowerUnitModel(
            rSet.getString("namatower"),
            rSet.getInt("nomor"),
            rSet.getInt("lantai"),
            rSet.getString("jenis"),
            rSet.getInt("tarifsewa")

        );
    }

    @Override
    public boolean addUnitData(UnitModel unit, String tower) {
        String sql;int idtower =-1;

        Optional<TowerModel> result = findTowerById(tower);
        if(result.isPresent()){
            idtower = result.get().getIdtower();
        }else{
            return false;
        }

        sql = "INSERT INTO unit (nomor, lantai, jenis, tarifsewa, idtower ) VALUES (?,?,?::tipe,?,?)";
        int nomor = unit.getNomor();
        int lantai = unit.getLantai();
        String jenis = unit.getJenis();
        int tarifsewa = unit.getTarifsewa();
        
        int isSuccess = jdbcTemplate.update(sql, nomor,lantai,jenis,tarifsewa, idtower);

        return isSuccess >0 ? true : false;
    }

    @Override
    public Optional<TowerModel> findTowerById(String id) {
        String sql = "SELECT * FROM tower WHERE namatower LIKE ?";

        List<TowerModel> res = jdbcTemplate.query(sql, this::mapRowToTowers, id);

        return res.size() > 0 ? Optional.of(res.get(0)) : Optional.empty();        
    }

}
