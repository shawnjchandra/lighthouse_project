package com.lighthouse.project.Transaksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lighthouse.project.Tower.TowerUnitModel;
import com.lighthouse.project.Tower.TowerUnitRepo;

@Repository
public class TransaksiJDBC implements TransaksiRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TowerUnitRepo repo;

    @Override
    public List<TransaksiModel> findAllTransaction() {
        String sql = "select * from transaksi";
        return jdbcTemplate.query(sql, this::mapRowToTransaksi);
    }

    private TransaksiModel mapRowToTransaksi(ResultSet rSet, int rowNum) throws SQLException{
        return new TransaksiModel(
            rSet.getString("tgglcheckin"),
            rSet.getString("tgglcheckout"),
            rSet.getString("status"),
            rSet.getInt("rating"),
            rSet.getString("review")

        );
        
    }

    @Override
    public List<TransaksiTowerUnitModel> findAllTTU() {
        // String sql = "";

        List<TransaksiTowerUnitModel> ttu = new ArrayList<>();
    
        //PH buat tahan
        return new ArrayList<>();
    }
    
}
