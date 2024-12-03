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
public class TTUWithRevRat{
    int idtrsk;
    String namatower;
    int lantai;
    int nomor;
    String jenis;
    int tarifsewa;
    String tgglcheckin;
    String tgglcheckout;

    // Tambahan
    int rating;
    String review;

}
