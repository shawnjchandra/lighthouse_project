package com.lighthouse.project.Other;

import java.util.List;

public interface AlamatRepo {
    public int getIDKelByName(String namaKelurahan);
    public int getIDKecByName(String namaKecamatan);
    public List<KecamatanModel> findAllKecamatan();
    public List<KelurahanModel> findAllKelurahanByIDKec(int idKec);
    
}
