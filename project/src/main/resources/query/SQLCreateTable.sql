
-- create
CREATE TABLE Kecamatan (
  idKec int PRIMARY KEY IDENTITY(1,1),
  namaKec varchar(15) not null
  
);

CREATE TABLE Kelurahan (
  idKel int PRIMARY KEY IDENTITY(1,1),
  namaKel varchar(15) not null,
  idKec int FOREIGN KEY (idKec) references Kecamatan(idKec)
  
);

CREATE TABLE Tower (
 idTower int PRIMARY KEY IDENTITY(1,1),
 namaTower varchar(1) not null
);

CREATE TABLE Unit (
  idUnit int PRIMARY KEY IDENTITY(1,1),
  nomor int not null,
  lantai int not null,
  jenis varchar(10) CHECK (jenis IN ('Studio','2BR') ),
  tarifSewa decimal(10,2),
  idTower int FOREIGN KEY (idTower) references Tower(idTower)
);

CREATE TABLE Pengguna (
  NIK varchar(16) PRIMARY KEY CHECK 
  (NIK LIKE ('[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')),
  nama varchar(10) not null,
  noHP varchar(12) UNIQUE,
  tipe bit not null,
  -- tipe 0 pelanggan, 1 agen
  idAgen int null,
  idPel int null,
  email varchar(20) not null,
  username varchar(10) UNIQUE not null,
  pass varchar(12) not null

);

CREATE TABLE KelolaUnit (
  idUnit int FOREIGN KEY (idUnit) references Unit(idUnit),
  NIK varchar(16) FOREIGN KEY (NIK) references Pengguna(NIK)
 --NIK KHUSUS AGEN
)

CREATE TABLE JadwalKetersediaan(
  idJadwal int PRIMARY KEY IDENTITY(1,1),
  tanggalMulai DATE not null,
  tanggalSelesai DATE not null,
  statusTersedia bit not null,
  -- 0 tidak tersedia, 1 tersedia
  idUnit int FOREIGN KEY (idUnit) references Unit(idUnit)
);

CREATE TABLE Transaksi(
  idTrsk int PRIMARY KEY IDENTITY(1,1),
  tgglCheckIn DATE not null,
  tgglCheckOut DATE not null,
  statusPembayaran bit not null,
  -- status 0 belum terbayar, 1 terbayar
  -- Rating dan review hanya bisa setelah status menjadi 1
  rating int CHECK (rating between 1 and 5) null,
  review varchar(100) null,
  NIK varchar(16) FOREIGN KEY (NIK) references Pengguna(NIK),
  idUnit int FOREIGN KEY (idUnit) references Unit(idUnit)

)

