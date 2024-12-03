package com.lighthouse.project.Other;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AlamatJDBC implements AlamatRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    //pas udah milih dropdown, nanti insert di registernya
    @Override
    public int getIDKelByName(String namaKelurahan) {
        String sql = "SELECT * FROM kelurahan WHERE namakel LIKE ?";

        List<KelurahanModel> result = jdbcTemplate.query(sql, this::mapRowToKelurahan, namaKelurahan);
        System.out.println(result);
        
        return result.get(0).getIdKel();
    }

    private KelurahanModel mapRowToKelurahan(ResultSet rSet, int row) throws SQLException{
        return new KelurahanModel(
            rSet.getInt("idkel"),
            rSet.getString("namakel")
        );
    }

     //tampilin dropdown kecamatan
    @Override
    public List<KecamatanModel> findAllKecamatan() {
        String sql = "SELECT * FROM kecamatan";
        return jdbcTemplate.query(sql, this::mapRowToKecamatan);

    }

    
    private KecamatanModel mapRowToKecamatan(ResultSet rSet, int row) throws SQLException{
        return new KecamatanModel(
            rSet.getInt("idkec"),
            rSet.getString("namakec")
        );
    }

    //tampilin dropdown kelurahan

    @Override
    public List<KelurahanModel> findAllKelurahanByIDKec(int idKec) {
        String sql = "SELECT * FROM kecamatan kec JOIN kelurahan kel ON kel.idkec = kec.idkec WHERE kec.idkec = ? ";
        return jdbcTemplate.query(sql, this::mapRowToKelurahan, idKec);
    }

    @Override
    public int getIDKecByName(String namaKecamatan) {
        String sql = "SELECT * FROM kecamatan WHERE namakec LIKE ?";

        List<KecamatanModel> result = jdbcTemplate.query(sql, this::mapRowToKecamatan, namaKecamatan);
        return result.get(0).getIdKec();
    }

    

   

    
    
}
