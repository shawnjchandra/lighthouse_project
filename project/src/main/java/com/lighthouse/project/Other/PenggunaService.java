package com.lighthouse.project.Other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Service
@Getter
public class PenggunaService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AlamatRepo alamatRepo;

}
