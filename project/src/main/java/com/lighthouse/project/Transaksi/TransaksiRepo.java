package com.lighthouse.project.Transaksi;

import java.util.List;
import java.util.Map;

public interface TransaksiRepo {
    List<TransaksiModel> findAllTransaction();
    List<TransaksiTowerUnitModel> findAllTTU(); 
    List<TransaksiTowerUnitModel> findAllTTUBetweenDates(Map<String, Object> filter); 

    List<PenggunaTransaksiModel> findAllUserTransaction();
    List<PenggunaTransaksiModel> findUTByCheckIn(String date);
    List<PenggunaTransaksiModel> findUTByCheckOut(String date);
    
    
}
