package com.lighthouse.project.Transaksi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lighthouse.project.Other.PenggunaModel;
import com.lighthouse.project.Tower.TowerUnitJDBC;
import com.lighthouse.project.Tower.TowerUnitModel;
import com.lighthouse.project.Tower.TowerUnitRepo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

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

    @Override
    public boolean addTransaction(String checkIn, String checkout, PenggunaModel pelanggan, TowerUnitModel unit, String statuspembayaran) {
        String sql;

        sql = "select idunit\n" + //
                        "from unit\n" + //
                        "where nomor = ? and lantai = ?";
        int nomor = unit.getNomor();
        int lantai = unit.getLantai();

        List<UnitWithId> res = jdbcTemplate.query(sql, this::mapRowToUnitWithId,nomor,lantai);
        int idunit = res.get(0).getIdunit();

        sql = "insert into transaksi (tgglcheckin, tgglcheckout, statuspembayaran, nik,idunit)\n" + //
                        "VALUES (?::date,?::date,?::stats_bayar,?,?)";
        String nik = pelanggan.getNik();
        
        int add = jdbcTemplate.update(sql, checkIn,checkout,statuspembayaran,nik,idunit);
        
        if(add>0){
            return true;

        }
        return false;
    }

    private UnitWithId mapRowToUnitWithId(ResultSet rSet, int rowNum)throws SQLException{
        return new UnitWithId(
            rSet.getInt("idunit")
        );
    }

    @Override
    public TransaksiID getIdTransaksi(String tgglcheckin, String tgglcheckout, String statuspembayaran, String nik) {
        // String tgglcheckin = transaksi.getTgglcheckin();
        // String tgglcheckout = transaksi.getTgglcheckout();
        // String statuspembayaran = transaksi.getStatuspembayaran();
        // String nik = transaksi.getNik();
        
        String sql = "SELECT * FROM transaksi\n" + //
                        "WHERE tgglcheckin = ?::date and tgglcheckout = ?::date and statuspembayaran = ?::stats_bayar\n" + //
                        "AND NIK LIKE ?";

        List<TransaksiID> res = jdbcTemplate.query(sql, this::mapRowToTransaksiID,tgglcheckin, tgglcheckout, statuspembayaran, nik);
        return res.size()>0? res.get(0) : null;
    }

    private TransaksiID mapRowToTransaksiID(ResultSet rSet, int rowNum) throws SQLException{
        return new TransaksiID(
            rSet.getInt("idtrsk")
        );
    }

    @Override
    public List<TTUWithRevRat> findAllTTUREVRAT() {
        String sql = "select *\n" + //
                        "from transaksi tr\n" + //
                        "join unit u on tr.idunit = u.idunit\n" + //
                        "join tower t on u.idtower = t.idtower";
    
        
        return jdbcTemplate.query(sql, this::mapRowToTTUREVRAT) ; 
    }

    private TTUWithRevRat mapRowToTTUREVRAT(ResultSet rSet, int rowNum) throws SQLException{
        return new TTUWithRevRat( 
            rSet.getInt("idtrsk"),
            rSet.getString("namatower"), 
            rSet.getInt("lantai"),
            rSet.getInt("nomor"), 
            rSet.getString("jenis"), 
            rSet.getInt("tarifsewa"),
            rSet.getString("tgglcheckin"),
            rSet.getString("tgglcheckout"),
            
            rSet.getInt("rating"),
            rSet.getString("review")
            );
    }

    @Override
    public List<TTUWithRevRat> findWithFiltersSearch(Map<String, Object> filterPencarian) {
        String sql = "SELECT DISTINCT tr.tgglcheckin, tr.tgglcheckout, tr.idtrsk, u.idunit, t.namatower, u.lantai, u.nomor, u.jenis, u.tarifsewa, tr.rating, tr.review, j.tanggalmulai, j.tanggalselesai \n" + //
                        "\tFROM transaksi tr       \n" + //
                        "\tJOIN Unit u on u.idunit = tr.idunit      \n" + //
                        "\tJOIN tower t on t.idtower = u.idtower\n" + //
                        "\tJOIN jadwalketersediaan j on j.idunit = u.idunit\n" + //
                        "\twhere u.idunit in (\n" + //
                        "\t\tselect idunit\n" + //
                        "\t\tfrom jadwalketersediaan\n" + //
                        "\t\tGROUP BY idunit\n" + //
                        "\t\t)";

        String filter="";
        List<Object> params = new ArrayList<>();
        
        System.out.println("contain tanggal "+filterPencarian.containsKey("tanggal"));
        System.out.println("contain tarifsewa "+filterPencarian.containsKey("tarifsewa"));
        System.out.println("contain ratingMin "+filterPencarian.containsKey("ratingMin"));
        System.out.println("contain review "+filterPencarian.containsKey("review"));

        if(filterPencarian.containsKey("tanggal")){
            // Tower
            filter += " AND tanggalselesai >= ?::date AND tanggalmulai <= ?::date";
            params.add(String.valueOf(filterPencarian.get("tanggal")));
            params.add(String.valueOf(filterPencarian.get("tanggal")));
        }else{
            params.remove(String.valueOf(filterPencarian.get("tanggal")));
        }
        

        if(filterPencarian.containsKey("tarifsewa")  && Integer.valueOf(String.valueOf(filterPencarian.get("tarifsewa"))) != 0 ){

            // System.out.println();
            filter += " AND u.tarifsewa <= ? \n";
            int tarifsewa = Integer.valueOf(String.valueOf(filterPencarian.get("tarifsewa")));

            params.add(tarifsewa);
        }

        if(filterPencarian.containsKey("ratingMin")  ){

            // System.out.println();
            filter += " AND tr.rating >= ? \n";
            int ratingMin = Integer.valueOf(String.valueOf(filterPencarian.get("ratingMin")));

            params.add(ratingMin);
        }

        if(filterPencarian.containsKey("review")){
            // Tower
            filter += " AND tr.review ILIKE ? \n";
            System.out.println("review: "+String.valueOf("%"+filterPencarian.get("review")+"%"));
            params.add(String.valueOf("%"+filterPencarian.get("review")+"%"));
        }else{
            params.remove(String.valueOf("%"+filterPencarian.get("review")+"%"));
        }


        sql += filter;
        System.out.println(sql);

        System.out.println("params size " + params.size());
         for (Object param : params) {
            System.out.print(param + " ");
        }
         System.out.println();
            // Pass the parameters to the query based on the size of params
         Object[] paramArray = params.toArray();
            

        return jdbcTemplate.query(sql, this::mapRowToTTUREVRAT, paramArray);
    }

    @Override
    public List<TTUWithRevRat> findTTURevRatsWithNIK(String nik) {
        String sql = "select *\n" + //
                        "from transaksi tr\n" + //
                        "join unit u on tr.idunit = u.idunit\n" + //
                        "join tower t on u.idtower = t.idtower\n"+
                        "WHERE tr.nik = ?";
        return jdbcTemplate.query(sql, this::mapRowToTTUREVRAT, nik);
    }

    

    // TODO:
    // MAKE A DIVIDER SCRIPT FOR JADWALKETERSEDIAAN WHEN PROCESSING ORDER
}

@Data
@Getter
@Setter
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@AllArgsConstructor
class UnitWithId {
    int idunit;
}
