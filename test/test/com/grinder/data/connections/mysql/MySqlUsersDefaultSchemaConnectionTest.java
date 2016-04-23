package com.grinder.data.connections.mysql;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;
//THIS IS AN INITIAL TEST THAT WILL BE DISABLED ONCE PASSED
public class MySqlUsersDefaultSchemaConnectionTest {

    private Connection connection;
    private MySqlConnections productionConnection;

    //@Before
    public void setUp(){
        this.productionConnection = new MySqlUsersDefaultSchemaConnection();
        this.connection = productionConnection.getConnection();
    }

    //@After
    public void tearDown() throws Exception {
        if(this.connection != null){
            if(!this.connection.isClosed()){
                this.connection.close();
            }
        }
    }

    //@Test
    public void testGetConnection() throws Exception {
        boolean connectionClosed = connection.isClosed();
        assertEquals(connectionClosed, false);
    }

    //@Test
    public void testCloseConnection() throws Exception {
        connection.close();
        boolean connectionClosed = connection.isClosed();
        assertEquals(connectionClosed, true);
    }
}