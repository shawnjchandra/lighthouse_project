package com.lighthouse.project.Transaksi;

import com.lighthouse.project.Tower.TowerUnitModel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TransaksiModel {
    String tgglcheckin;
    String tgglcheckout;
    String statuspembayaran;
    int rating;
    String review;
    String nik;
}



