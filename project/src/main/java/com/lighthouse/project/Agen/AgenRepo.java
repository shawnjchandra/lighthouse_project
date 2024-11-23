package com.lighthouse.project.Agen;

import java.util.List;

import com.lighthouse.project.Transaksi.TransaksiModel;

public interface AgenRepo {
    List<TransaksiModel> findAllTransaction();
    
}
