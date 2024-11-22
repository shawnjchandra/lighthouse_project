INSERT INTO Pengguna (NIK, nama, noHP, tipe, email, username, pass) VALUES
-- Agen
('1234567890123456', 'Ali', '081234567890', 'Agen', 'ali@agency.com', 'ali_agen', 'password123'),
('1234567890123457', 'Budi', '081234567891', 'Agen', 'budi@agency.com', 'budi_agen', 'password456'),
-- Pelanggan
('1234567890123458', 'Citra', '081234567892', 'Pelanggan', 'citra@gmail.com', 'citra_user', 'pass789'),
('1234567890123459', 'Dewi', '081234567893', 'Pelanggan', 'dewi@gmail.com', 'dewi_user', 'pass987'),
('1234567890123460', 'Eko', '081234567894', 'Pelanggan', 'eko@gmail.com', 'eko_user', 'pass654');

INSERT INTO Tower (namaTower) VALUES 
('A'), 
('B'), 
('C'); -- Assume Tower table already exists.

-- Insert into Unit
INSERT INTO Unit (nomor, lantai, jenis, tarifSewa, idTower) VALUES
(101, 1, 'Studio', 2500000.00, 1), -- Unit di Tower A
(102, 1, '2BR', 4500000.00, 1),   -- Unit di Tower A
(201, 2, 'Studio', 2600000.00, 1),-- Unit di Tower A
(301, 3, '2BR', 4700000.00, 2),   -- Unit di Tower B
(401, 4, 'Studio', 2800000.00, 2),-- Unit di Tower B
(402, 4, '2BR', 4800000.00, 2),   -- Unit di Tower B
(501, 5, 'Studio', 2900000.00, 3),-- Unit di Tower C
(502, 5, '2BR', 4900000.00, 3);   -- Unit di Tower C


INSERT INTO JadwalKetersediaan (tanggalMulai, tanggalSelesai, statusTersedia, idUnit) VALUES
-- Active
('2024-12-01', '2024-12-10', 'A', 1),
('2024-12-05', '2024-12-15', 'A', 2),
-- Not Active
('2024-12-20', '2024-12-25', 'NA', 3),
-- Maintenance
('2025-01-01', '2025-01-07', 'M', 1);

INSERT INTO Transaksi (tgglCheckIn, tgglCheckOut, statusPembayaran, rating, review, NIK, idUnit) VALUES
-- Paid transactions
('2024-12-01', '2024-12-05', 'P', 5, 'Excellent service!', '1234567890123456', 1),
('2024-12-06', '2024-12-10', 'P', 4, 'Very comfortable stay.', '1234567890123457', 2),
-- Unpaid transactions
('2024-12-11', '2024-12-15', 'U', NULL, NULL, '1234567890123458', 3),
('2024-12-16', '2024-12-20', 'U', NULL, NULL, '1234567890123459', 1);

