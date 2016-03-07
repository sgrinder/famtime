package com.grinder.data.connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserAccessConnection implements AccessConnection {

    @Override
    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(DatabasePaths.JDBC_UCANACCESS + DatabasePaths.USER_DATABASE_PATH);
    }

    @Override
    public Connection getTestConnection() throws Exception {
        return DriverManager.getConnection(DatabasePaths.JDBC_UCANACCESS + DatabasePaths.USER_TEST_DATABASE_PATH);
    }
}
