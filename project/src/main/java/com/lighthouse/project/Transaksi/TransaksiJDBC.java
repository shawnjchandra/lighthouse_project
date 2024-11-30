package com.lighthouse.project.Transaksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            rSet.getString("statuspembayaran"),
            rSet.getInt("rating"),
            rSet.getString("review"),
            rSet.getString("nik")
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
            rSet.getInt("idtrsk"),
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
        "WHERE tgglcheckin = ?::date AND tr.tgglcheckin = j.tanggalmulai;";

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
        "WHERE tgglcheckout = ?::date AND tr.tgglcheckout = j.tanggalselesai;";

        return jdbcTemplate.query(sql, this::mapRowToUT,date);    
    }

    @Override
    public List<TransaksiTowerUnitModel> findAllTTUBetweenDates(Map<String, Object> filter) {
        String sql = "select *\n" + //
                        "from transaksi tr\n" + //
                        "join unit u on tr.idunit = u.idunit\n" + //
                        "join tower t on u.idtower = t.idtower\n"+
                        "WHERE tr.tgglcheckin >= ?::date AND tr.tgglcheckout <= ?::date";
        
        String checkIn = String.valueOf(filter.get("startDate"));
        String checkOut = String.valueOf(filter.get("endDate"));
        System.out.println(checkIn +" "+checkOut);
        return jdbcTemplate.query(sql, this::mapRowToTTU,checkIn, checkOut);       
    }

    public List<TransaksiTowerUnitModel> findRiwayatTransaksi(String nik) {
        String sql = "select * from transaksi tr join unit u on tr.idunit = u.idunit join tower t on u.idtower = t.idtower WHERE tr.nik = ?";
        return jdbcTemplate.query(sql, this::mapRowToTTU, nik);
    }

    @Override
    public void saveReview(int idtrsk, int rating, String review) {
        String sql = "UPDATE transaksi \n" + //
                        "SET rating = ?, review = ? \n" + //
                        "WHERE idtrsk = ?";
        jdbcTemplate.update(sql, rating, review, idtrsk);
    }

    

    // TODO:
    // MAKE A DIVIDER SCRIPT FOR JADWALKETERSEDIAAN WHEN PROCESSING ORDER
}
