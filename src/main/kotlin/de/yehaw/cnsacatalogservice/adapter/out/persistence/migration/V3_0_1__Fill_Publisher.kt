package de.yehaw.cnsacatalogservice.adapter.out.persistence.migration

import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.springframework.jdbc.core.JdbcTemplate

class V3_0_1__Fill_Publisher : BaseJavaMigration() {

    override fun migrate(context: Context?) {
        val jdbcTemplate = context?.configuration?.dataSource?.let { JdbcTemplate(it) }
        jdbcTemplate?.update("UPDATE book SET publisher = 'Manning' WHERE publisher IS NULL");
    }

}
