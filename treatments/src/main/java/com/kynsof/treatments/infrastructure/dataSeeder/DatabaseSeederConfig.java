package com.kynsof.treatments.infrastructure.dataSeeder;

import com.kynsof.treatments.infrastructure.repositories.query.Cie10ReadDataJPARepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DatabaseSeederConfig {

    private final DataSource dataSource;
    private final Cie10ReadDataJPARepository repositoryQuery;

    public DatabaseSeederConfig(Cie10ReadDataJPARepository repositoryQuery, DataSource dataSource) {
        this.repositoryQuery = repositoryQuery;
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void seedDatabase() {
        try {
            if (repositoryQuery.count() == 0) {
                ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("cie10.sql"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}