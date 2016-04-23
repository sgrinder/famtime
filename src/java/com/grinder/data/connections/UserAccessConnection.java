package com.grinder.data.connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserAccessConnection implements AccessConnection {


    @Override
    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(AccessDatabasePaths.JDBC_UCANACCESS + AccessDatabasePaths.USER_DATABASE_PATH);
    }

    @Override
    public Connection getTestConnection() throws Exception {
        return DriverManager.getConnection(AccessDatabasePaths.JDBC_UCANACCESS + AccessDatabasePaths.USER_TEST_DATABASE_PATH);
    }
}
