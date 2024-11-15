--------------------------------------------
 INSERT INTO Tower (namaTower) VALUES('A')
  INSERT INTO Tower (namaTower) VALUES('B')
   INSERT INTO Tower (namaTower) VALUES('C')
    INSERT INTO Tower (namaTower) VALUES('D')

-- Insert Kecamatan
INSERT INTO Kecamatan (namaKec) VALUES 
('Andir'),
('Astana Anyar'),
('Antapani'),
('Arcamanik'),
('Babakan Ciparay'),
('Bandung Kidul'),
('Bandung Kulon'),
('Bandung Wetan'),
('Batununggal'),
('Bojongloa Kaler'),
('Bojongloa Kidul'),
('Buahbatu'),
('Cibeunying Kaler'),
('Cibeunying Kidul'),
('Cibiru'),
('Cicendo'),
('Cidadap'),
('Cinambo'),
('Coblong'),
('Gedebage'),
('Kiaracondong'),
('Lengkong'),
('Mandalajati'),
('Panyileukan'),
('Rancasari'),
('Regol'),
('Sukajadi'),
('Sukasari'),
('Sumur Bandung'),
('Ujungberung');

-- Insert Kelurahan
INSERT INTO Kelurahan (namaKel, idKec) VALUES
('Campaka', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Andir')),
('Ciroyom', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Andir')),
('Dunguscariang', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Andir')),
('Garuda', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Andir')),
('Kebonjeruk', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Andir')),
('Maleber', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Andir')),

('Cibadak', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Astana Anyar')),
('Karanganyar', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Astana Anyar')),
('Karasak', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Astana Anyar')),
('Nyengseret', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Astana Anyar')),
('Panjunan', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Astana Anyar')),
('Pelindunghewan', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Astana Anyar')),

('Antapani Kidul', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Antapani')),
('Antapani Kulon', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Antapani')),
('Antapani Tengah', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Antapani')),
('Antapani Wetan', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Antapani')),

('Cisaranten Bina Harapan', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Arcamanik')),
('Cisaranten Endah', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Arcamanik')),
('Cisaranten Kulon', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Arcamanik')),
('Sukamiskin', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Arcamanik')),

('Babakan', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Babakan Ciparay')),
('Babakanciparay', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Babakan Ciparay')),
('Cirangrang', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Babakan Ciparay')),
('Margahayu Utara', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Babakan Ciparay')),
('Margasuka', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Babakan Ciparay')),
('Sukahaji', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Babakan Ciparay')),

('Batununggal', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Kidul')),
('Kujangsari', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Kidul')),
('Mengger', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Kidul')),
('Wates', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Kidul')),

('Caringin', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Kulon')),
('Cibuntu', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Kulon')),
('Cigondewah Kaler', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Kulon')),
('Cigondewah Kidul', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Kulon')),
('Cigondewah Rahayu', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Kulon')),
('Cijerah', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Kulon')),
('Gempolsari', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Kulon')),
('Warungmuncang', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Kulon')),

('Cihapit', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Wetan')),
('Citarum', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Wetan')),
('Tamansari', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bandung Wetan')),

('Binong', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Batununggal')),
('Cibangkong', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Batununggal')),
('Gumuruh', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Batununggal')),
('Kacapiring', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Batununggal')),
('Kebongedang', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Batununggal')),
('Kebonwaru', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Batununggal')),
('Maleer', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Batununggal')),
('Samaja', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Batununggal')),

('Babakan Asih', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bojongloa Kaler')),
('Babakan Tarogong', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bojongloa Kaler')),
('Jamika', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bojongloa Kaler')),
('Kopo', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bojongloa Kaler')),
('Suka Asih', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bojongloa Kaler')),

-- Bojongloa Kidul (idKec 11)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Cibaduyut', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bojongloa Kidul')),
('Cibaduyut Kidul', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bojongloa Kidul')),
('Cibaduyut Wetan', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bojongloa Kidul')),
('Kebon Lega', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bojongloa Kidul')),
('Mekarwangi', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bojongloa Kidul')),
('Situsaeur', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Bojongloa Kidul'));

-- Buahbatu (idKec 12)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Cijawura', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Buahbatu')),
('Jatisari', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Buahbatu')),
('Margasari', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Buahbatu')),
('Sekejati', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Buahbatu'));

-- Cibeunying Kaler (idKec 13)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Cigadung', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cibeunying Kaler')),
('Cihaurgeulis', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cibeunying Kaler')),
('Neglasari', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cibeunying Kaler')),
('Sukaluyu', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cibeunying Kaler'));

-- Cibeunying Kidul (idKec 14)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Cicadas', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cibeunying Kidul')),
('Cikutra', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cibeunying Kidul')),
('Padasuka', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cibeunying Kidul')),
('Pasirlayung', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cibeunying Kidul')),
('Sukamaju', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cibeunying Kidul')),
('Sukapada', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cibeunying Kidul'));

-- Cibiru (idKec 15)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Cipadung', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cibiru')),
('Cisurupan', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cibiru')),
('Palasari', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cibiru')),
('Pasirbiru', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cibiru'));

-- Cicendo (idKec 16)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Arjuna', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cicendo')),
('Husen Sastranegara', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cicendo')),
('Pajajaran', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cicendo')),
('Pamoyanan', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cicendo')),
('Pasirkaliki', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cicendo')),
('Sukaraja', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cicendo'));

-- Cidadap (idKec 17)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Ciumbuleuit', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cidadap')),
('Hegarmanah', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cidadap')),
('Ledeng', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cidadap'));

-- Cinambo (idKec 18)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Babakan Penghulu', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cinambo')),
('Cisaranten Wetan', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cinambo')),
('Pakemitan', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cinambo')),
('Sukamulya', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Cinambo'));

-- Coblong (idKec 19)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Cipaganti', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Coblong')),
('Dago', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Coblong')),
('Lebakgede', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Coblong')),
('Lebaksiliwangi', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Coblong')),
('Sadangserang', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Coblong')),
('Sekeloa', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Coblong'));

-- Gedebage (idKec 20)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Cimincrang', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Gedebage')),
('Cisaranten Kidul', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Gedebage')),
('Rancabolang', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Gedebage')),
('Rancanumpang', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Gedebage'));

-- Kiaracondong (idKec 21)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Babakansari', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Kiaracondong')),
('Babakansurabaya', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Kiaracondong')),
('Cicaheum', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Kiaracondong')),
('Kebonkangkung', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Kiaracondong')),
('Kebunjayanti', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Kiaracondong')),
('Sukapura', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Kiaracondong'));

-- Lengkong (idKec 22)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Burangrang', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Lengkong')),
('Cijagra', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Lengkong')),
('Cikawao', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Lengkong')),
('Lingkar Selatan', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Lengkong')),
('Malabar', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Lengkong')),
('Paledang', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Lengkong')),
('Turangga', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Lengkong'));

-- Mandalajati (idKec 23)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Jatihandap', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Mandalajati')),
('Karangpamulang', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Mandalajati')),
('Pasir Impun', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Mandalajati')),
('Sindangjaya', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Mandalajati'));

-- Panyileukan (idKec 24)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Cipadung Kidul', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Panyileukan')),
('Cipadung Kulon', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Panyileukan')),
('Cipadung Wetan', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Panyileukan')),
('Mekarmulya', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Panyileukan'));

-- Rancasari (idKec 25)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Cisaranten', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Rancasari')),
('Kampung Baru', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Rancasari')),
('Rancanera', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Rancasari'));

-- Regol (idKec 26)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Kebon Kelapa', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Regol')),
('Kebon Pala', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Regol')),
('Neglasari', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Regol')),
('Sukamaju', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Regol'));

-- Sukajadi (idKec 27)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Cijagra', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sukajadi')),
('Husein Sastranegara', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sukajadi')),
('Indihiang', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sukajadi')),
('Ledeng', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sukajadi')),
('Mekarwangi', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sukajadi'));

-- Sumur Bandung (idKec 28)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Cikawao', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sumur Bandung')),
('Cidadap', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sumur Bandung')),
('Kebon Kawung', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sumur Bandung')),
('Pahlawan', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sumur Bandung')),
('Pasirkaliki', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sumur Bandung')),
('Sukaraja', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sumur Bandung'));

-- Ujungberung (idKec 29)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Cicalengka', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Ujungberung')),
('Cicadas', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Ujungberung')),
('Cimekar', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Ujungberung')),
('Sukamulya', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Ujungberung')),
('Ujungberung', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Ujungberung')),
('Pasirjati', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Ujungberung'));

-- Sukasari (idKec 30)
INSERT INTO Kelurahan (namaKel, idKec) VALUES 
('Gegerkalong', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sukasari')),
('Isola', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sukasari')),
('Sarijadi', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sukasari')),
('Sukarasa', (SELECT idKec FROM Kecamatan WHERE namaKec = 'Sukasari'));


-- Sesuaikan kode kecamatan dan kelurahan lainnya dengan cara yang sama


---------------------------------------------

