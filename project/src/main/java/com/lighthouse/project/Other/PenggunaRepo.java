package com.lighthouse.project.Other;

import java.util.Optional;

public interface PenggunaRepo {
    Optional<PenggunaModel> validateUser(String username, String pass);
    boolean register(String nik, String nama, String nohp, String email, String username, String pass, String confPass);
    String getUserType(String nik);
}