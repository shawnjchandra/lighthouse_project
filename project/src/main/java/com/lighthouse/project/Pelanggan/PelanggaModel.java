package com.lighthouse.project.Pelanggan;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Value
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
public class PelanggaModel {
    String nik;
    String nama;
    String nohp;
    String email;
    String username;
}
