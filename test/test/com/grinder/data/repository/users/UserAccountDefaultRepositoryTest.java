package com.grinder.data.repository.users;

import com.grinder.data.connections.mysql.MySqlConnections;
import com.grinder.data.connections.mysql.MySqlUsersDevelopmentSchemaConnection;
import com.grinder.entities.users.UserAccount;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserAccountDefaultRepositoryTest {
    private UserAccountRepository userAccountRepository;
    private UserAccount userAccount;

    @Before
    public void setUp(){
        userAccountRepository = new UserAccountDefaultRepository(new MySqlUsersHibernateDevelopmentFactorySession());
    }

    @After
    public void tearDown(){
        removeTestAccount(userAccount);
    }

    private UserAccount getTestUserAccount(){
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountName("Test");
        userAccount.setPassword("Password");
        userAccount.setStatusId(1);
        userAccount.setCreatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
        userAccount.setModifiedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
        return userAccount;
    }

    @Test
    public void testHibernateCreateUserAccount() throws Exception {
        userAccount = getTestUserAccount();
        userAccountRepository.saveAccount(userAccount);
        assertNotNull(userAccount.getAccountId());
    }

    @Test
    public void testHibernateUpdateUserAccount() throws Exception{
        userAccount = getTestUserAccount();

        userAccountRepository.saveAccount(userAccount);
        userAccount.setStatusId(1);

        userAccount = userAccountRepository.updateAccount(userAccount);

        assertEquals(1, userAccount.getStatusId());
    }

    @Test
    public void testHibernateUserReadById(){
        userAccount = getTestUserAccount();
        userAccountRepository.saveAccount(userAccount);

        UserAccount retrievedUserAccount = userAccountRepository.getById(userAccount.getAccountId());

        assertNotNull(retrievedUserAccount);
        assertEquals(retrievedUserAccount.getAccountId(), userAccount.getAccountId());
    }

    @Test
    public void testHibernateUserRealAll(){
        userAccount = getTestUserAccount();
        userAccountRepository.saveAccount(userAccount);

        List<UserAccount> userAccountList = userAccountRepository.getAll();

        assertTrue(userAccountList.size() > 0);
    }

    public void removeTestAccount(UserAccount userAccount){
        userAccountRepository.removeAccount(userAccount);
    }

}