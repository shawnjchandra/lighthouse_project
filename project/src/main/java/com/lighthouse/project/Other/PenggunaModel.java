package com.lighthouse.project.Other;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
public class PenggunaModel {
    String nik;
    String nama;
    String nohp;
    String email;
    String username;
    String pass;
    String tipe;
}
