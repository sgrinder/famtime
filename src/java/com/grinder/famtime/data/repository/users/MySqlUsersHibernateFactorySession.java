package com.grinder.famtime.data.repository.users;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;

public class MySqlUsersHibernateFactorySession implements MySqlUsersFactorySession {
    private SessionFactory sessionFactory;

    public MySqlUsersHibernateFactorySession(){
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        this.sessionFactory = new Configuration()
                .configure("com/grinder/famtime/dbconfigs/hibernate_famtime_users_prod.cfg.xml") // configures settings from hibernate_famtime_users_dev.cfg.xml
                .buildSessionFactory();
    }

    @Override
    public SessionFactory getSessionFactory() {
        return null;
    }
}
