package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.AccountEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AccountDefaultRepository implements AccountRepository {
    private SessionFactory sessionFactory;

    public AccountDefaultRepository(MySqlUsersFactorySession mySqlUsersFactorySession){
        this.sessionFactory = mySqlUsersFactorySession.getSessionFactory();
    }

    @Override
    public AccountEntity saveAccount(AccountEntity accountEntity){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(accountEntity);
        session.getTransaction().commit();
        session.flush();
        session.close();
        return accountEntity;
    }

    @Override
    public AccountEntity updateAccount(AccountEntity accountEntity){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(accountEntity);
        session.getTransaction().commit();
        session.close();
        return accountEntity;
    }

    @Override
    public AccountEntity getById(int userAccountId){
        Session session = sessionFactory.openSession();
        AccountEntity accountEntity = (AccountEntity) session.get(AccountEntity.class, userAccountId);
        session.close();
        return accountEntity;
    }

    @Override
    public List<AccountEntity> getAll(){
        Session session = sessionFactory.openSession();
        List<AccountEntity> accountEntityList = session.createCriteria(AccountEntity.class).list();
        session.close();
        return accountEntityList;
    }

    @Override
    public void removeAccount(AccountEntity accountEntity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(accountEntity);
        session.getTransaction().commit();
        session.close();
    }
}
