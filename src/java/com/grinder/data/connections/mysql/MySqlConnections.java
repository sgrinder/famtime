package com.grinder.data.connections.mysql;

import java.sql.Connection;

public interface MySqlConnections {
    Connection getConnection();
    void closeConnection();
}
