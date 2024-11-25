package com.lighthouse.project.KetersediaanUnit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KetersediaanJDBC implements KetersediaanRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TransaksiKetersediaanModel> findAll() {
        String sql = "SELECT \n" + //
                        "    t.namatower, \n" + //
                        "    u.lantai, \n" + //
                        "    u.nomor, \n" + //
                        "    j.tanggalmulai, \n" + //
                        "    j.tanggalselesai, \n" + //
                        "    j.statustersedia, \n" + //
                        "    u.tarifsewa, \n" + //
                        "    p.nama\n" + //
                        "FROM Transaksi tr\n" + //
                        "JOIN Unit u ON tr.idUnit = u.idUnit\n" + //
                        "JOIN tower t on t.idtower = u.idtower\n" + //
                        "JOIN JadwalKetersediaan j ON j.idUnit = u.idUnit\n" + //
                        "JOIN Pengguna p ON tr.NIK = p.NIK\n"+
                        "WHERE J.statustersedia = 'NA'";
        return jdbcTemplate.query(sql, this::mapRowToTKM);
    }

    private TransaksiKetersediaanModel mapRowToTKM(ResultSet rSet , int rowNum) throws SQLException {
        return new TransaksiKetersediaanModel(
            rSet.getString("namatower"),
            rSet.getString("nama"),
            rSet.getInt("lantai"),
            rSet.getInt("nomor"),
            rSet.getString("tanggalmulai"),
            rSet.getString("tanggalselesai"),
            rSet.getString("statustersedia"),
            rSet.getInt("tarifsewa")

        );
    }

    @Override
    public List<TransaksiKetersediaanModel> findUsedBetweenDates(String tanggalmulai, String tanggalselesai) {
        String sql = "SELECT \n" + //
                        "    t.namatower, \n" + //
                        "    u.lantai, \n" + //
                        "    u.nomor, \n" + //
                        "    j.tanggalmulai, \n" + //
                        "    j.tanggalselesai, \n" + //
                        "    j.statustersedia, \n" + //
                        "    u.tarifsewa, \n" + //
                        "    p.nama\n" + //
                        "FROM Transaksi tr\n" + //
                        "JOIN Unit u ON tr.idUnit = u.idUnit\n" + //
                        "JOIN tower t on t.idtower = u.idtower\n" + //
                        "JOIN JadwalKetersediaan j ON j.idUnit = u.idUnit\n" + //
                        "JOIN Pengguna p ON tr.NIK = p.NIK\n"+
                        "WHERE J.statustersedia = 'NA'\n"+
                        "AND tanggalmulai >= ?::date and tanggalselesai <= ?::date";
        return jdbcTemplate.query(sql, this::mapRowToTKM, tanggalmulai,tanggalselesai);
    }

    @Override
    public List<TransaksiKetersediaanModel> findWithFilters(Map<String,Object> filters) {
        String sql = "SELECT \n" + //
                        "    t.namatower, \n" + //
                        "    u.lantai, \n" + //
                        "    u.nomor, \n" + //
                        "    j.tanggalmulai, \n" + //
                        "    j.tanggalselesai, \n" + //
                        "    j.statustersedia, \n" + //
                        "    u.tarifsewa, \n" + //
                        "    p.nama\n" + //
                        "FROM Transaksi tr\n" + //
                        "JOIN Unit u ON tr.idUnit = u.idUnit\n" + //
                        "JOIN tower t on t.idtower = u.idtower\n" + //
                        "JOIN JadwalKetersediaan j ON j.idUnit = u.idUnit\n" + //
                        "JOIN Pengguna p ON tr.NIK = p.NIK\n";
        String filter="";
        List<String> params = new ArrayList<>();
        
        if(filters.containsKey("apt")){
            // Tower
            filter += " AND t.namatower LIKE ? \n";
            params.add(String.valueOf("%"+filters.get("apt")+"%"));
        }else{
            params.remove(String.valueOf("%"+filters.get("apt")+"%"));
        }
        
        if(filters.containsKey("startDate")&&filters.containsKey("endDate") 
        && String.valueOf(filters.get("endDate")) != "" && String.valueOf(filters.get("startDate"))!= ""){
            filter += " AND j.tanggalmulai >= ?::date and j.tanggalselesai <= ?::date\n";
            params.add(String.valueOf(filters.get("startDate")));
            params.add(String.valueOf(filters.get("endDate")));
        }

        if(filters.containsKey("status") && !String.valueOf(filters.get("status")).equals("") && filters.get("status") != null ){

            // System.out.println();
            filter += " AND j.statustersedia = ?::stats \n";
            String trimmed = String.valueOf(filters.get("status"));

            params.add(trimmed);
        }
        sql += filter;
     

        System.out.println("params size " + params.size());
         for (Object param : params) {
            System.out.print(param + " ");
        }
         System.out.println();
            // Pass the parameters to the query based on the size of params
         Object[] paramArray = params.toArray();
            

        return jdbcTemplate.query(sql, this::mapRowToTKM, paramArray);
                    
        

        


       
        
    }

    @Override
    public int findByKode(Map<String, String> filters) {
        String sql = "UPDATE jadwalketersediaan\n" + //
                        "SET statustersedia = ?::stats\n" + //
                        "WHERE\n" + //
                        "\tidunit = (\n" + //
                        "\tSELECT tr.idunit\n" + //
                        "\tFROM transaksi tr\n" + //
                        "\tJOIN Unit u on u.idunit = tr.idunit\n" + //
                        "\tJOIN tower t on t.idtower = u.idtower\n" + //
                        "\tJOIN JadwalKetersediaan j ON j.idUnit = u.idUnit\n"+
                        "\tWHERE t.namatower LIKE ?\n"+ 
                        "\tAND u.lantai = ?\n"+ 
                        "AND u.nomor =?\n" + //
                        "\tAND j.tanggalmulai = ?::date AND j.tanggalselesai = ?::date) \n" +
                        "\tAND (statustersedia ='A' OR statustersedia = 'M')\n" ;
        /*
            TODO :
            DATE START - DATE END SPESIFIK UNTUK YANG DIUBAH
        */ 
        String tower="",startDate="",endDate="",status="";
        int lantai=0,nomor=0;

        if(filters.containsKey("tower")){
            tower = String.valueOf(filters.get("tower"));
        }
        if(filters.containsKey("lantai")){
            lantai = Integer.valueOf(filters.get("lantai"));
            
        }
        if(filters.containsKey("nomor")){
            nomor = Integer.valueOf(filters.get("nomor"));
            
        }
        if(filters.containsKey("startDate")){
            startDate = String.valueOf(filters.get("startDate"));
            
        }
        if(filters.containsKey("endDate")){
            endDate = String.valueOf(filters.get("endDate"));
            
        }
        if(filters.containsKey("status")){
            status = String.valueOf(filters.get("status"));
        }
        

        return jdbcTemplate.update(sql, status, tower, lantai, nomor, startDate, endDate);
    }
}
