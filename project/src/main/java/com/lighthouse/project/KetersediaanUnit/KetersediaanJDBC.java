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
        String sql = "select t.namatower, u.lantai, u.nomor, jk.tanggalmulai, jk.tanggalselesai, jk.statustersedia, u.tarifsewa\n" + //
                        "from jadwalketersediaan as jk\n" + //
                        "inner join unit as u on jk.idunit = u.idunit\n" + //
                        "inner join tower as t on u.idtower = t.idtower";
        return jdbcTemplate.query(sql, this::mapRowToTKM);
    }

    private TransaksiKetersediaanModel mapRowToTKM(ResultSet rSet , int rowNum) throws SQLException {
        return new TransaksiKetersediaanModel(
            rSet.getString("namatower"),
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
        String sql = "select t.namatower, u.lantai, u.nomor, jk.tanggalmulai, jk.tanggalselesai, jk.statustersedia, u.tarifsewa\n" + //
                        "from jadwalketersediaan as jk\n" + //
                        "inner join unit as u on jk.idunit = u.idunit\n" + //
                        "inner join tower as t on u.idtower = t.idtower\n" + //
                        "WHERE tanggalmulai >= ?::date and tanggalselesai <= ?::date";
        return jdbcTemplate.query(sql, this::mapRowToTKM, tanggalmulai,tanggalselesai);
    }
}
