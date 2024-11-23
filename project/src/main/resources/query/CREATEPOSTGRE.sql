DROP TABLE IF EXISTS Kelurahan,KelolaUnit, Unit, JadwalKetersediaan,  Transaksi, Kecamatan, Tower, Pengguna;
DROP TYPE IF EXISTS TIPE,TIPE_P, STATS, STATS_BAYAR;
DROP SEQUENCE seq_idAgen, seq_idPel;

-- create
CREATE TABLE Kecamatan (
  idKec int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  namaKec varchar(255) not null
  
);

CREATE TABLE Kelurahan (
  idKel int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  namaKel varchar(255) not null,
  idKec int references Kecamatan(idKec) not null
  
);

CREATE TABLE Tower (
 idTower int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
 namaTower varchar(1) not null
);

CREATE TYPE TIPE AS ENUM ('Studio','2BR');

CREATE TABLE Unit (
  idUnit int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  nomor int not null,
  lantai int not null,
  jenis TIPE NOT NULL,
  tarifSewa int NOT NULL,
  idTower int references Tower(idTower) NOT NULL
);

CREATE TYPE TIPE_P AS ENUM ('Agen','Pelanggan');

CREATE TABLE Pengguna (
  NIK char(16) PRIMARY KEY CHECK (NIK ~ ('^[0-9]+$')),
  nama varchar(255) not null,
  noHP varchar(12) UNIQUE,
  tipe TIPE_P not null,
  -- tipe 0 pelanggan, 1 agen
  idAgen int DEFAULT null,
  idPel int DEFAULT null,
  email varchar(20) not null,
  username varchar(255) UNIQUE not null,
  pass varchar(255) not null

);

CREATE TABLE KelolaUnit (
  idUnit int references Unit(idUnit) NOT NULL,
  NIK varchar(16) references Pengguna(NIK) NOT NULL
 --NIK KHUSUS AGEN
);

-- Available, Not Available, Maintenance
CREATE TYPE STATS AS ENUM ('A','NA','M');

CREATE TABLE JadwalKetersediaan(
  idJadwal int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  tanggalMulai DATE not null,
  tanggalSelesai DATE not null,
  statusTersedia STATS not null,
  -- 0 tidak tersedia, 1 tersedia
  idUnit int references Unit(idUnit) NOT NULL
);

-- PAID, UNPAID
CREATE TYPE STATS_BAYAR AS ENUM ('P','U');

CREATE TABLE Transaksi(
  idTrsk int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  tgglCheckIn DATE not null,
  tgglCheckOut DATE not null,
  statusPembayaran STATS_BAYAR not null,
  -- status 0 belum terbayar, 1 terbayar
  -- Rating dan review hanya bisa setelah status menjadi 1
  rating int CHECK (rating between 1 and 5) null,
  review varchar(100) null,
  NIK varchar(16) references Pengguna(NIK) NOT NULL,
  idUnit int references Unit(idUnit) NOT NULL

);

CREATE SEQUENCE seq_idAgen START 1 INCREMENT 1;
CREATE SEQUENCE seq_idPel START 1 INCREMENT 1;

CREATE OR REPLACE FUNCTION pengguna_id_increment()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.tipe = 'Agen' THEN
        -- Jika tipe adalah Agen
        NEW.idAgen := nextval('seq_idAgen');
    ELSE
        -- Jika tipe adalah Pelanggan
        NEW.idPel := nextval('seq_idPel');
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER pengguna_id_trigger
BEFORE INSERT ON Pengguna
FOR EACH ROW
EXECUTE FUNCTION pengguna_id_increment();



