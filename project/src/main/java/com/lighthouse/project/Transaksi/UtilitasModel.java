package com.lighthouse.project.Transaksi;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@Getter
@FieldDefaults(makeFinal = false, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UtilitasModel {
    TransaksiTowerUnitModel ttu;
    int frekuensi;
    int total;
    public Object getTransaksiTowerUnitModel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTransaksiTowerUnitModel'");
    }
}
