package com.lighthouse.project.KetersediaanUnit;

import java.util.List;
import java.util.Map;

public interface KetersediaanRepo {
    List<TransaksiKetersediaanModel> findAll();
    List<TransaksiKetersediaanModel> findUsedBetweenDates(String tanggalmulai, String tanggalselesai);

    List<TransaksiKetersediaanModel> findWithFilters(Map<String,Object> filters);
}
