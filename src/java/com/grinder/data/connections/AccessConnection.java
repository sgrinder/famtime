package com.grinder.data.connections;

import java.sql.Connection;

public interface AccessConnection {
    Connection getConnection() throws Exception;
    Connection getTestConnection() throws Exception;
}
