package com.grinder.data.connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class UserAccountAccessConnection implements AccessConnection {

    @Override
    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(DatabasePaths.JDBC_UCANACCESS + DatabasePaths.USER_ACCOUNTS_DATABASE_PATH);
    }

    @Override
    public Connection getTestConnection() throws Exception {
        return DriverManager.getConnection(DatabasePaths.JDBC_UCANACCESS + DatabasePaths.USER_ACCOUNTS_TEST_DATABASE_PATH);
    }
}
