package org.technohaven.core.entities;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminSecPermMapper implements RowMapper<AdminSecPerm> {
    public AdminSecPerm mapRow(ResultSet rs, int rowNum) throws SQLException {
        AdminSecPerm adminSecPerm = new AdminSecPerm();
        adminSecPerm.setAdminSecId(rs.getInt("admin_section_id"));
        adminSecPerm.setAdminPermId(rs.getInt("admin_permission_id"));
        return adminSecPerm;
    }
}