package com.lighthouse.project.Tower;

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
public class TowerUnitModel {
    String namatower;
    int nomor;
    int lantai;
    String jenis;
    int tarifsewa;
}