package com.lighthouse.project.KetersediaanUnit;

import java.util.List;

public interface KetersediaanRepo {
    List<TransaksiKetersediaanModel> findAll();
    List<TransaksiKetersediaanModel> findUsedBetweenDates(String tanggalmulai, String tanggalselesai);
}
