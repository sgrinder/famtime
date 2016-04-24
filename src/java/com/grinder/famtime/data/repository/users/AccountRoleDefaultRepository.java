package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.AccountRoleEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class AccountRoleDefaultRepository implements AccountRoleRepository {
    private SessionFactory sessionFactory;

    public AccountRoleDefaultRepository(MySqlUsersFactorySession mySqlUsersFactorySession){
        this.sessionFactory = mySqlUsersFactorySession.getSessionFactory();
    }


    @Override
    public AccountRoleEntity saveAccountRole(AccountRoleEntity accountRoleEntity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(accountRoleEntity);
        session.getTransaction().commit();
        session.flush();
        session.close();
        return accountRoleEntity;
    }

    @Override
    public AccountRoleEntity updateAccountRole(AccountRoleEntity accountRoleEntity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(accountRoleEntity);
        session.getTransaction().commit();
        session.flush();
        session.close();
        return accountRoleEntity;
    }

    @Override
    public AccountRoleEntity getById(int accountRoleId) {
        Session session = sessionFactory.openSession();
        AccountRoleEntity accountRoleEntity = session.get(AccountRoleEntity.class, accountRoleId);
        session.close();
        return accountRoleEntity;
    }

    @Override
    public List<AccountRoleEntity> getByAccountId(int accountId) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(AccountRoleEntity.class);
        List<AccountRoleEntity> accountRoleEntityList = criteria.add(Restrictions.eq("accountId", accountId)).list();
        session.close();
        return accountRoleEntityList;
    }

    @Override
    public List<AccountRoleEntity> getByRoleId(int roleId) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(AccountRoleEntity.class);
        List<AccountRoleEntity> accountRoleEntityList = criteria.add(Restrictions.eq("roleId", roleId)).list();
        session.close();
        return accountRoleEntityList;
    }

    @Override
    public List<AccountRoleEntity> getAll() {
        Session session = sessionFactory.openSession();
        List<AccountRoleEntity> accountRoleEntityList = session.createCriteria(AccountRoleEntity.class).list();
        session.close();
        return accountRoleEntityList;
    }

    @Override
    public void removeAccountRole(AccountRoleEntity accountRoleEntity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(accountRoleEntity);
        session.getTransaction().commit();
        session.close();
    }
}
