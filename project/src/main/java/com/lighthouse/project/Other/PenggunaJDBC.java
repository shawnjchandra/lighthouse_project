package com.lighthouse.project.Other;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PenggunaJDBC implements PenggunaRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PenggunaService service;

    @Override
    public Optional<PenggunaModel> validateUser (String username, String pass) {
        pass = service.getPasswordEncoder().encode(pass);

        String sql = "select * from pengguna WHERE username LIKE ? AND pass LIKE ? ";    
        List<PenggunaModel> users =  jdbcTemplate.query(sql, this::mapRowToPengguna ,username,pass); 
        
        return users.size() == 0 ? Optional.empty() : Optional.of(users.get(0));
    }

    private PenggunaModel mapRowToPengguna(ResultSet rSet, int rowNum)throws SQLException{
        return new PenggunaModel(
            rSet.getString("nik"),
            rSet.getString("nama"),
            rSet.getString("nohp"),
            rSet.getString("email"),

            rSet.getString("alamat"),
            rSet.getInt("idkel"),
            
            rSet.getString("username"),
            rSet.getString("pass"),
            rSet.getString("tipe")

        );
    }

    @Override
    public boolean register(String nik, String nama, String nohp, String email,String alamat, String kelurahan, String username, String pass, String confPass) {
        String sql = "select * from pengguna where username LIKE ? AND tipe = 'Pelanggan'";

        List<PenggunaModel> users = jdbcTemplate.query(sql,this::mapRowToPengguna, username);
        boolean isAvailable = users.size() > 0 ? false : true;

        System.out.println("isAvailable "+isAvailable);
        
        boolean isSuccess = false;
        
        if(isAvailable){
            // pass = service.getPasswordEncoder().encode(pass);
            // confPass = service.getPasswordEncoder().encode(confPass);
            
            System.out.println(pass+" "+confPass);
            
            boolean samePass = pass.equals(confPass);
            System.out.println(samePass);
            
            if(samePass){
                pass = service.getPasswordEncoder().encode(pass);

                //TODO : Get idKelurahan by 
                System.out.println("kelurahan"+kelurahan);
                int idkel = service.getAlamatRepo().getIDKelByName(kelurahan);
                
                sql = "insert into pengguna (nik, nama, nohp, email,alamat, idkel, username,pass,tipe) VALUES (?,?,?,?,?,?,?,?,'Pelanggan') ";
                
                jdbcTemplate.update(sql, nik,nama,nohp,email,alamat, idkel ,username,pass);    
                
                isSuccess = true;
            }
        }
        System.out.println("isSuccess "+isSuccess);

        
        return isSuccess;
    }

   

    @Override
    public String getUserType(String username) {
        String sql = "select * from pengguna WHERE username LIKE ? ";
        List<PenggunaModel> user = jdbcTemplate.query(sql, this::mapRowToPengguna, username);
        String userType ="";

        if (!user.isEmpty()){
            userType = user.get(0).getTipe();

            System.out.println(userType);
        }
        
        return userType;
    }

    @Override
    public boolean login(String username, String password) {
        String sql = "select * from pengguna WHERE username LIKE ?";
        List<PenggunaModel> user = jdbcTemplate.query(sql, this::mapRowToPengguna, username);
     
        if(!user.isEmpty()){
            String passwordFromQ = user.get(0).getPass();
        
            if(service.getPasswordEncoder().matches(password, passwordFromQ)){
                return true;
            }
        }

        return  false ;
    }

    @Override
    public String getUserNik(String username){
        // System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA" + username);
        String sql = "select * from pengguna WHERE username LIKE ? ";
        List<PenggunaModel> user = jdbcTemplate.query(sql, this::mapRowToPengguna, username);
        String userNik ="";

        if (!user.isEmpty()){
            userNik = user.get(0).getNik();

            System.out.println(userNik);
        }
        
        return userNik;
    }

    @Override
    public Optional<PenggunaModel> getUserByUsername(String username) {
        String sql = "SELECT * FROM pengguna WHERE username LIKE ?";
        List<PenggunaModel> res= jdbcTemplate.query(sql, this::mapRowToPengguna,username);
        
        return res.isEmpty() ? null : Optional.of(res.get(0)); 
    }
 
    // NON-POLICY LOGIN AND REGISTER
}
