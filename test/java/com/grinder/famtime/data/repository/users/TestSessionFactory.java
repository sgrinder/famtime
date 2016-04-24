package com.grinder.famtime.data.repository.users;

public class TestSessionFactory {
    public static MySqlUsersFactorySession mySqlUsersFactorySession
            = new MySqlUsersHibernateDevelopmentFactorySession();
}
