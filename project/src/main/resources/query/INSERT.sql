INSERT INTO Tower (namaTower) VALUES
('A'),
('B');

INSERT INTO Unit (nomor, lantai, jenis, tarifSewa, idTower) VALUES
(101, 1, 'Studio', 2000000, 1),  -- Unit 101 di Tower A (Studio)
(102, 1, '2BR', 3000000, 1),     -- Unit 102 di Tower A (2BR)
(201, 2, 'Studio', 2200000, 2),  -- Unit 201 di Tower B (Studio)
(202, 2, '2BR', 3500000, 2);     -- Unit 202 di Tower B (2BR)

INSERT INTO Pengguna (NIK, nama, noHP, tipe, email, username, pass) VALUES
('1234567890123456', 'Agen A', '081234567890', 'Agen', 'agenA@mail.com', 'agenA', 'password'),
('2345678901234567', 'Pelanggan A', '082345678901', 'Pelanggan', 'pelangganA@mail.com', 'pelangganA', 'password'),
('3456789012345678', 'Agen B', '083456789012', 'Agen', 'agenB@mail.com', 'agenB', 'password'),
('4567890123456789', 'Pelanggan B', '084567890123', 'Pelanggan', 'pelangganB@mail.com', 'pelangganB', 'password');

INSERT INTO KelolaUnit (idUnit, NIK) VALUES
(1, '1234567890123456'),  -- Agen A mengelola Unit 1
(2, '1234567890123456'),  -- Agen A mengelola Unit 2
(3, '3456789012345678'),  -- Agen B mengelola Unit 3
(4, '3456789012345678');  -- Agen B mengelola Unit 4

INSERT INTO Transaksi (tgglCheckIn, tgglCheckOut, statusPembayaran, NIK, idUnit) VALUES
('2024-11-01', '2024-11-05', 'U', '2345678901234567', 1),  -- Pelanggan A sewa unit 1 (Unpaid)
('2024-11-10', '2024-11-15', 'P', '4567890123456789', 3),  -- Pelanggan B sewa unit 3 (Paid)
('2024-11-20', '2024-11-25', 'U', '2345678901234567', 2);  -- Pelanggan A sewa unit 2 (Unpaid)

INSERT INTO JadwalKetersediaan (tanggalMulai, tanggalSelesai, statusTersedia, idUnit) VALUES
('2024-11-01', '2024-11-05', 'NA', 1),  -- Unit 1 tidak tersedia karena ada transaksi
('2024-11-06', '2024-11-30', 'A', 1),  -- Unit 1 tersedia setelah transaksi
('2024-11-10', '2024-11-15', 'NA', 3),  -- Unit 3 tidak tersedia karena ada transaksi
('2024-11-16', '2024-11-30', 'A', 3),  -- Unit 3 tersedia setelah transaksi
('2024-11-20', '2024-11-25', 'NA', 2),  -- Unit 2 tidak tersedia karena ada transaksi
('2024-11-26', '2024-11-30', 'A', 2);  -- Unit 2 tersedia setelah transaksi

