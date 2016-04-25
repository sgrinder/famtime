package com.grinder.famtime.data.repository.users;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;

public class MySqlUsersHibernateDevelopmentFactorySession implements MySqlUsersFactorySession {

    private SessionFactory sessionFactory;
    private static final String CONFIG_PATH = "com/grinder/famtime/dbconfigs/hibernate_famtime_users_dev.cfg.xml";

    public MySqlUsersHibernateDevelopmentFactorySession(){
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        this.sessionFactory = new Configuration()
                .configure(CONFIG_PATH) // configures settings from hibernate_famtime_users_dev.cfg.xml
                .buildSessionFactory();
    }

    public SessionFactory getSessionFactory(){
        return this.sessionFactory;
    }

}
