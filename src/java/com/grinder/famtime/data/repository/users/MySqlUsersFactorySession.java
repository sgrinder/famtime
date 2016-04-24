package com.grinder.famtime.data.repository.users;

import org.hibernate.SessionFactory;

public interface MySqlUsersFactorySession {
    SessionFactory getSessionFactory();
}
