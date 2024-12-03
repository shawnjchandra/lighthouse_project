package com.lighthouse.project.Other;

import java.util.Optional;

public interface PenggunaRepo {
    Optional<PenggunaModel> validateUser(String username, String pass);
    boolean register(String nik, String nama, String nohp, String email, String alamat, String kelurahan, String username,  String pass, String confPass);
    boolean login(String username, String password);
    
    Optional<PenggunaModel> getUserByUsername(String username);
    String getUserType(String username);
    String getUserNik(String username);
}