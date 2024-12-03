package com.lighthouse.project.Transaksi;

import java.util.List;
import java.util.Map;

import com.lighthouse.project.KetersediaanUnit.TransaksiKetersediaanModel;
import com.lighthouse.project.Other.PenggunaModel;
import com.lighthouse.project.Tower.TowerUnitModel;

public interface TransaksiRepo {
    List<TransaksiModel> findAllTransaction();
    List<TransaksiTowerUnitModel> findAllTTU(); 
    List<TransaksiTowerUnitModel> findAllTTUBetweenDates(Map<String, Object> filter); 

    
    List<TTUWithRevRat> findAllTTUREVRAT();
    List<TTUWithRevRat> findWithFiltersSearch(Map<String,Object> filterPencarian);
    List<TTUWithRevRat> findTTURevRatsWithNIK(String nik);



    List<PenggunaTransaksiModel> findAllUserTransaction();
    List<PenggunaTransaksiModel> findUTByCheckIn(String date);
    List<PenggunaTransaksiModel> findUTByCheckOut(String date);
    
    boolean addTransaction(String checkIn, String checkout, PenggunaModel pelanggan, TowerUnitModel unit,String statuspembayaran);

    List<TransaksiTowerUnitModel> findRiwayatTransaksi(String nik);
    


    TransaksiID getIdTransaksi(String tgglcheckin, String tgglcheckout, String statuspembayaran, String nik);
    void saveReview(int idtrsk, int rating, String review);
    
}
