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

        String sql = "select *\n" + //
                        "from transaksi tr\n" + //
                        "join unit u on tr.idunit = u.idunit\n" + //
                        "join tower t on u.idtower = t.idtower";
    
        
        return jdbcTemplate.query(sql, this::mapRowToTTU); 
    }

    private TransaksiTowerUnitModel mapRowToTTU(ResultSet rSet, int rowNum) throws SQLException{
        return new TransaksiTowerUnitModel( 
            rSet.getString("namatower"), 
            rSet.getInt("lantai"),
            rSet.getInt("nomor"), 
            rSet.getString("jenis"), 
            rSet.getInt("tarifsewa"),
            rSet.getString("tgglcheckin"),
            rSet.getString("tgglcheckout")
            );
    }

    @Override
    public List<PenggunaTransaksiModel> findAllUserTransaction() {
        String sql = "SELECT \n" + //
                        "    t.namatower, \n" + //
                        "    u.lantai, \n" + //
                        "    u.nomor, \n" + //
                        "    tr.tgglcheckin,\n" + //
                        "    tr.tgglcheckout, \n" + //
                        "    tr.statuspembayaran, \n" + //
                        "    p.nama\n" + //
                        "FROM Transaksi tr\n" + //
                        "JOIN Unit u ON tr.idUnit = u.idUnit\n" + //
                        "JOIN tower t on t.idtower = u.idtower\n" + //
                        "JOIN JadwalKetersediaan j ON j.idUnit = u.idUnit\n" + //
                        "JOIN Pengguna p ON tr.NIK = p.NIK";    
        return jdbcTemplate.query(sql, this::mapRowToUT);
    }

    private PenggunaTransaksiModel mapRowToUT(ResultSet rSet, int rowNum) throws SQLException{
        return new PenggunaTransaksiModel(
            rSet.getString("nama"),
            rSet.getString("namatower"),
            rSet.getInt("lantai"),
            rSet.getInt("nomor"),
            rSet.getString("tgglcheckin"),
            rSet.getString("tgglcheckout"),
            rSet.getString("statuspembayaran")

        );
        
    }

    @Override
    public List<PenggunaTransaksiModel> findUTByCheckIn(String date) {
        String sql = "SELECT \n" + //
        "    t.namatower, \n" + //
        "    u.lantai, \n" + //
        "    u.nomor, \n" + //
        "    tr.tgglcheckin,\n" + //
        "    tr.tgglcheckout, \n" + //
        "    tr.statuspembayaran, \n" + //
        "    p.nama\n" + //
        "FROM Transaksi tr\n" + //
        "JOIN Unit u ON tr.idUnit = u.idUnit\n" + //
        "JOIN tower t on t.idtower = u.idtower\n" + //
        "JOIN JadwalKetersediaan j ON j.idUnit = u.idUnit\n" + //
        "JOIN Pengguna p ON tr.NIK = p.NIK\n"+
        "WHERE tgglcheckin = ?::date;";

        return jdbcTemplate.query(sql, this::mapRowToUT,date);
    }

    @Override
    public List<PenggunaTransaksiModel> findUTByCheckOut(String date) {
        String sql = "SELECT \n" + //
        "    t.namatower, \n" + //
        "    u.lantai, \n" + //
        "    u.nomor, \n" + //
        "    tr.tgglcheckin,\n" + //
        "    tr.tgglcheckout, \n" + //
        "    tr.statuspembayaran, \n" + //
        "    p.nama\n" + //
        "FROM Transaksi tr\n" + //
        "JOIN Unit u ON tr.idUnit = u.idUnit\n" + //
        "JOIN tower t on t.idtower = u.idtower\n" + //
        "JOIN JadwalKetersediaan j ON j.idUnit = u.idUnit\n" + //
        "JOIN Pengguna p ON tr.NIK = p.NIK\n"+
        "WHERE tgglcheckout = ?::date;";

        return jdbcTemplate.query(sql, this::mapRowToUT,date);    
    }
    
}
