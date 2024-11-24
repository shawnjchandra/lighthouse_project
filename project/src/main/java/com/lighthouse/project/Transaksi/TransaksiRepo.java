package com.lighthouse.project.Transaksi;

import java.util.List;

public interface TransaksiRepo {
    List<TransaksiModel> findAllTransaction();
    List<TransaksiTowerUnitModel> findAllTTU(); 

    List<PenggunaTransaksiModel> findAllUserTransaction();
    List<PenggunaTransaksiModel> findUTByCheckIn(String date);
    List<PenggunaTransaksiModel> findUTByCheckOut(String date);
    
    
}
