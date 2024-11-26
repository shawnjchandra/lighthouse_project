package com.lighthouse.project.KetersediaanUnit;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

// @Data
// @Getter
// @Setter
// @FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
// @AllArgsConstructor
// public class KetersediaanModel {
//     String tanggalmulai;
//     String tanggalselesai;
//     String statustersedia;
// }

@Data
@Getter
@Setter
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TransaksiKetersediaanModel{
    String namatower;
    String nama;
    int lantai;
    int nomor;
    String tanggalmulai;
    String tanggalselesai;
    String statustersedia;
    int tarifsewa;

}
