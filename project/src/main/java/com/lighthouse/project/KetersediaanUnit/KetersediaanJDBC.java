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
}
