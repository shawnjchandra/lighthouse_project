package com.lighthouse.project.Transaksi;

import java.util.List;

public interface TransaksiRepo {
    List<TransaksiModel> findAllTransaction();
    List<TransaksiTowerUnitModel> findAllTTU(); 
    
}
