package com.lighthouse.project.KetersediaanUnit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KetersediaanJDBC implements KetersediaanRepo {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TransaksiKetersediaanModel> findAll() {
        String sql = "SELECT \n" + //
                        "    t.namatower, \n" + //
                        "    u.lantai, \n" + //
                        "    u.nomor, \n" + //
                        "    j.tanggalmulai, \n" + //
                        "    j.tanggalselesai, \n" + //
                        "    j.statustersedia, \n" + //
                        "    u.tarifsewa, \n" + //
                        "    p.nama\n" + //
                        "FROM Transaksi tr\n" + //
                        "JOIN Unit u ON tr.idUnit = u.idUnit\n" + //
                        "JOIN tower t on t.idtower = u.idtower\n" + //
                        "JOIN JadwalKetersediaan j ON j.idUnit = u.idUnit\n" + //
                        "JOIN Pengguna p ON tr.NIK = p.NIK\n"+
                        "WHERE J.statustersedia = 'NA'";
        return jdbcTemplate.query(sql, this::mapRowToTKM);
    }

    private TransaksiKetersediaanModel mapRowToTKM(ResultSet rSet , int rowNum) throws SQLException {
        return new TransaksiKetersediaanModel(
            rSet.getString("namatower"),
            rSet.getString("nama"),
            rSet.getInt("lantai"),
            rSet.getInt("nomor"),
            rSet.getString("tanggalmulai"),
            rSet.getString("tanggalselesai"),
            rSet.getString("statustersedia"),
            rSet.getInt("tarifsewa")

        );
    }

    @Override
    public List<TransaksiKetersediaanModel> findUsedBetweenDates(String tanggalmulai, String tanggalselesai) {
        String sql = "SELECT \n" + //
                        "    t.namatower, \n" + //
                        "    u.lantai, \n" + //
                        "    u.nomor, \n" + //
                        "    j.tanggalmulai, \n" + //
                        "    j.tanggalselesai, \n" + //
                        "    j.statustersedia, \n" + //
                        "    u.tarifsewa, \n" + //
                        "    p.nama\n" + //
                        "FROM Transaksi tr\n" + //
                        "JOIN Unit u ON tr.idUnit = u.idUnit\n" + //
                        "JOIN tower t on t.idtower = u.idtower\n" + //
                        "JOIN JadwalKetersediaan j ON j.idUnit = u.idUnit\n" + //
                        "JOIN Pengguna p ON tr.NIK = p.NIK\n"+
                        "WHERE J.statustersedia = 'NA'\n"+
                        "AND tanggalmulai >= ?::date and tanggalselesai <= ?::date";
        return jdbcTemplate.query(sql, this::mapRowToTKM, tanggalmulai,tanggalselesai);
    }
}
