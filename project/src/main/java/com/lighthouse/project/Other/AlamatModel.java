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
public class AlamatModel {
    KelurahanModel kelurahan;
    KecamatanModel kecamtan;
}

@Getter
@Setter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
 class KelurahanModel {
    int idKel;
    String namakel;
}

@Getter
@Setter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
class  KecamatanModel{
    int idKec;
    String namakec;
}


