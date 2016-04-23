package com.grinder.data.connections.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;


public class MySqlUsersDefaultSchemaConnection implements MySqlConnections {
    private static final Logger logger = LoggerFactory.getLogger(MySqlUsersDefaultSchemaConnection.class);

    private Connection connection;
    private String connectionString;

    public MySqlUsersDefaultSchemaConnection(){
        connectionString =
                MySqlConnectionInformation.JDBC_MYSQL +
                        MySqlConnectionInformation.PRODUCTION_HOST +
                        "/" +
                        MySqlConnectionInformation.USERS_SCHEMA +
                        "?autoReconnect=true&useSSL=false";
        logger.debug("connectionString[{}]", connectionString);
    }

    @Override
    public Connection getConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.connection = DriverManager.getConnection(
                    connectionString,
                    MySqlConnectionInformation.USERS_USERNAME,
                    MySqlConnectionInformation.USERS_PASSWORD
            );
            logger.debug("Connection established");
        } catch (Exception ex){
            logger.error("Failed to establish connection for username[{}]", MySqlConnectionInformation.USERS_USERNAME, ex);
        }

        return this.connection;
    }

    @Override
    public void closeConnection() {
        try{
            if(this.connection != null)
                if(!this.connection.isClosed())
                    this.connection.close();
        } catch (Exception ex){
            logger.error("Failed to close connection.", ex);
        }
    }
}
