package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class Database {
    public static BasicDataSource dataSource;

    public static DataSource getDataSource(){
        if(dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/cardealershipdatabase");
            dataSource.setUsername("root");
            dataSource.setPassword("yearup");

            dataSource.setMinIdle(2);
            dataSource.setMaxIdle(5);
            dataSource.setMaxTotal(10);
        }
        return dataSource;
    }
}
