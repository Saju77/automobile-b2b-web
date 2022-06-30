package org.technohaven.core.dao;

import org.technohaven.core.entities.AdminSecPerm;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public interface AdminSecPermDao {

    /**
     * This is the method to be used to initialize
     * database resources ie. connection.
     */
    void setDataSource(DataSource ds);

    /**
     * This is the method to be used to create
     * a record in the blc_admin_sec_perm_xref(JoinTable) table [Note: Column name -> ADMIN_SECTION_ID, ADMIN_PERMISSION_ID].
     */
    void create(Integer adminSecId, Integer adminPermId);

    /**
     * This is the method to be used to list down
     * all the records from the blc_admin_sec_perm_xref table.
     */
    List<AdminSecPerm> listAdminSecPerms();

    /**
     * This is the method to be used to delete
     * a record from the blc_admin_sec_perm_xref(JoinTable) table
     */
    public int delete(Integer adminPermId);

    /**
     * This is the method to be used to find
     * a record from the blc_admin_sec_perm_xref(JoinTable) table
     */
    Optional<AdminSecPerm> findAdminSecPermById(Integer adminPermId);

}
