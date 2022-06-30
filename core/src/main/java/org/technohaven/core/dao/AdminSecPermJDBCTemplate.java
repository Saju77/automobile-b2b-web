package org.technohaven.core.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.AdminSecPerm;
import org.technohaven.core.entities.AdminSecPermMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("blAdminSecPermJDBCTemplate")
public class AdminSecPermJDBCTemplate implements AdminSecPermDao{

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public List<AdminSecPerm> listAdminSecPerms() {
        String sql = "select * from blc_admin_sec_perm_xref";
        List<AdminSecPerm> adminSecPerms = jdbcTemplateObject.query(sql, new AdminSecPermMapper());
        return adminSecPerms;
    }

    @Override
    public int delete(Integer adminPermId) {
        String sql = "DELETE FROM blc_admin_sec_perm_xref WHERE admin_permission_id = ?";
        return jdbcTemplateObject.update(sql, adminPermId);
    }

    @Override
    public Optional<AdminSecPerm> findAdminSecPermById(Integer adminPermId) {
        String sql = "select * from blc_admin_sec_perm_xref where admin_permission_id = ?";
        Optional<AdminSecPerm> adminSecPerm = jdbcTemplateObject.query(sql, new AdminSecPermMapper(), adminPermId).stream().findFirst();;
        return adminSecPerm;
    }

    @Override
    public void create(Integer adminSecId, Integer adminPermId) {
        String insertQuery = "insert into blc_admin_sec_perm_xref (admin_section_id, admin_permission_id) values (?, ?)";
        jdbcTemplateObject.update(insertQuery, adminSecId, adminPermId);
    }

}
