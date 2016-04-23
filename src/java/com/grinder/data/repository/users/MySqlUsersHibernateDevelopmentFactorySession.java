package com.grinder.data.repository.users;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySqlUsersHibernateDevelopmentFactorySession implements MySqlUsersFactorySession {
    private SessionFactory sessionFactory;

    public MySqlUsersHibernateDevelopmentFactorySession(){
        this.sessionFactory = new Configuration()
                .configure("hibernate_dev.cfg.xml") // configures settings from hibernate_dev.cfg.xml
                .buildSessionFactory();
    }

    public SessionFactory getSessionFactory(){
        return this.sessionFactory;
    }
}
