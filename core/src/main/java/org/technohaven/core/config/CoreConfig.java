package org.technohaven.core.config;

import org.broadleafcommerce.common.demo.AutoImportPersistenceUnit;
import org.broadleafcommerce.common.demo.AutoImportSql;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jeff Fischer
 */
@Configuration
@ComponentScan("org.technohaven.core")
public class CoreConfig {

   /* @Bean
    public AutoImportSql blCommunitySolrIndexerData() {
        return new AutoImportSql(AutoImportPersistenceUnit.BL_PU,"/sql/load_solr_reindex_community.sql", 9999);
    }*/

    @Bean
    public AutoImportSql blLocationSetupModuleData() {
        return new AutoImportSql(AutoImportPersistenceUnit.BL_PU,"/sql/load_location_setup_module_data.sql", 9999);
    }

    @Bean
    public AutoImportSql blMappingSectionsAndPermissionsData() {
        return new AutoImportSql(AutoImportPersistenceUnit.BL_PU,"/sql/load_mapping_sections_and_permissions_data.sql", 9999);
    }

}
