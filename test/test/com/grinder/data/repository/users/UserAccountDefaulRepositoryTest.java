package com.grinder.data.repository.users;

import com.grinder.entities.users.UserAccount;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserAccountDefaulRepositoryTest {
    private UserAccountRepository userAccountRepository;

    @Before
    public void setUp(){
        boolean testMode = true;
        userAccountRepository = new UserAccountDefaulRepository(testMode);
    }

    @After
    public void tearDown(){
        userAccountRepository.removeAllUserAccounts();
    }

    private UserAccount getTestUserAccount(){
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountName("Test");
        userAccount.setPassword("Password");
        userAccount.setStatusId(1);
        return userAccount;
    }

    @Test
    public void testCreateUserAccount() throws Exception {
        UserAccount testUserAccount = getTestUserAccount();

        boolean successful = userAccountRepository.createUserAccount(testUserAccount);

        assertTrue(successful);
    }

    @Test
    public void testUpdateUserAccount() throws Exception {
        UserAccount testUserAccount = getTestUserAccount();

        userAccountRepository.createUserAccount(testUserAccount);

        UserAccount userAccount = userAccountRepository.getUserAccountByUserName("Test");
        userAccount.setAccountName("Tester");

        userAccountRepository.updateUserAccount(userAccount);

        UserAccount updatedUserAccount = userAccountRepository.getUserAccountByUserName("Tester");

        assertNotNull(updatedUserAccount);
    }

    @Test
    public void testGetUserAccounts() throws Exception {
        UserAccount testUserAccount = getTestUserAccount();

        userAccountRepository.createUserAccount(testUserAccount);

        List<UserAccount> userAccounts = userAccountRepository.getUserAccounts();

        assertNotNull(userAccounts);
        assertTrue(userAccounts.size() == 1);
    }

    @Test
    public void testGetUserAccountByUserId() throws Exception {
        UserAccount testUserAccount = getTestUserAccount();

        userAccountRepository.createUserAccount(testUserAccount);

        UserAccount userAccount = userAccountRepository.getUserAccountByUserName("Test");
        int accountId = userAccount.getAccountId();

        UserAccount userAccountById = userAccountRepository.getUserAccountByUserAccountId(accountId);

        assertNotNull(userAccountById);
        assertEquals(accountId, userAccountById.getAccountId());
    }

    @Test
    public void testGetUserAccountByUserName() throws Exception {
        UserAccount testUserAccount = getTestUserAccount();

        userAccountRepository.createUserAccount(testUserAccount);

        UserAccount userAccount = userAccountRepository.getUserAccountByUserName("Test");

        assertNotNull(userAccount);
        assertEquals("Test", userAccount.getAccountName());
    }

    @Test
    public void testGetUserAccountByUserNameAndPassword() throws Exception {
        UserAccount testUserAccount = getTestUserAccount();

        userAccountRepository.createUserAccount(testUserAccount);

        UserAccount userAccount = userAccountRepository.getUserAccountByUserNameAndPassword("Test", "Password");

        assertNotNull(userAccount);
        assertEquals("Password", userAccount.getPassword());
    }
}