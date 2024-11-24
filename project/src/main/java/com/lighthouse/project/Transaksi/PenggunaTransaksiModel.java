package com.lighthouse.project.Transaksi;



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
public class PenggunaTransaksiModel{
    String nama;
    String namatower;
    int lantai;
    int nomor;
    String tgglcheckin;
    String tgglcheckout;
    String statuspembayaran;
}