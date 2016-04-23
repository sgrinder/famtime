package com.grinder.data.repository.users;

import com.grinder.entities.users.UserAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserAccountDefaultRepository implements UserAccountRepository {

    private MySqlUsersFactorySession mySqlUsersFactorySession;

    public UserAccountDefaultRepository(MySqlUsersFactorySession mySqlUsersFactorySession){
        this.mySqlUsersFactorySession = mySqlUsersFactorySession;
    }

    @Override
    public UserAccount saveAccount(UserAccount userAccount){
        Session session = mySqlUsersFactorySession.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(userAccount);
        session.getTransaction().commit();
        session.flush();
        session.close();
        return userAccount;
    }

    @Override
    public UserAccount updateAccount(UserAccount userAccount){
        Session session = mySqlUsersFactorySession.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(userAccount);
        session.getTransaction().commit();
        session.close();
        return userAccount;
    }

    @Override
    public UserAccount getById(int userAccountId){
        Session session = mySqlUsersFactorySession.getSessionFactory().openSession();
        UserAccount userAccount = (UserAccount) session.get(UserAccount.class, userAccountId);
        session.close();
        return userAccount;
    }

    @Override
    public List<UserAccount> getAll(){
        Session session = mySqlUsersFactorySession.getSessionFactory().openSession();
        List<UserAccount> userAccountList = session.createCriteria(UserAccount.class).list();
        session.close();
        return userAccountList;
    }

    @Override
    public void removeAccount(UserAccount userAccount) {
        Session session = mySqlUsersFactorySession.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(userAccount);
        session.getTransaction().commit();
        session.close();
    }
}
