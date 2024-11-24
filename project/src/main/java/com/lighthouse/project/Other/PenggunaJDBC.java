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

    @Override
    public Optional<PenggunaModel> validateUser (String username, String pass) {
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
            rSet.getString("username"),
            rSet.getString("pass"),
            rSet.getString("tipe")

        );
    }

    @Override
    public boolean register(String nik, String nama, String nohp, String email, String username, String pass, String confPass) {
        String sql = "select * from pengguna where username LIKE ? AND tipe = 'Pelanggan'";

        List<PenggunaModel> users = jdbcTemplate.query(sql,this::mapRowToPengguna, username);
        boolean isAvailable = users.size() > 0 ? false : true;


        boolean isSuccess = false;

        if(isAvailable){
            boolean samePass = validatePasswordRegist(pass, confPass);

            if(samePass){
                sql = "insert into pengguna (nik, nama, nohp, email,username,pass,tipe) VALUES (?,?,?,?,?,?,'Pelanggan') ";
                int done = jdbcTemplate.update(sql, nik,nama,nohp,email,username,pass);    

                isSuccess = true;
            }
        }

        
        return isSuccess;
    }

    private boolean validatePasswordRegist(String pass, String confPass){
        return pass.equals(confPass);
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
        String sql = "select * from pengguna WHERE username LIKE ? AND pass LIKE ? ";
        List<PenggunaModel> user = jdbcTemplate.query(sql, this::mapRowToPengguna, username, password);
     

        return !user.isEmpty() ? true : false ;
    }


 
    // NON-POLICY LOGIN AND REGISTER
}
