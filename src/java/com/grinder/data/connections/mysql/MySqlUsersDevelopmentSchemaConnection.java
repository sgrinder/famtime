package com.grinder.data.connections.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlUsersDevelopmentSchemaConnection implements MySqlConnections {
    private Connection connection;
    private String connectionString;

    public MySqlUsersDevelopmentSchemaConnection(){
        connectionString =
                MySqlConnectionInformation.JDBC_MYSQL +
                        MySqlConnectionInformation.LOCAL_HOST +
                        "/" +
                        MySqlConnectionInformation.USERS_SCHEMA_DEVELOPMENT +
                        "?autoReconnect=true&useSSL=false";
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

        } catch (Exception ex){

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
        }
    }

}
