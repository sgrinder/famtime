package com.grinder.famtime.data.repository.users;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class AccountSecurityDefaultRepository implements AccountSecurityRepository {
    private MySqlUsersFactorySession mySqlUsersFactorySession;

    public AccountSecurityDefaultRepository(MySqlUsersFactorySession mySqlUsersFactorySession){
        this.mySqlUsersFactorySession = mySqlUsersFactorySession;
    }

    @Override
    public boolean isValidUserNameAndPassword(String userName, String password) {
        Session session = mySqlUsersFactorySession.getSessionFactory().openSession();

        Query query = session.createSQLQuery(
                "SELECT " +
                    "* " +
                "FROM " +
                    "accounts " +
                "WHERE " +
                    "account_name = :inAccountName " +
                    "AND password = MD5(:inPassword)"
        )
                .setParameter("inAccountName", userName)
                .setParameter("inPassword", password);
        List results = query.list();

        boolean result = results.size() == 0? false : true;

        session.close();

        return result;
    }
}
