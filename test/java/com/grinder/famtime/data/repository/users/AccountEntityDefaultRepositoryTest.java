package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.AccountEntity;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AccountEntityDefaultRepositoryTest extends TestSessionFactory {
    private static AccountRepository accountRepository;
    private AccountEntity accountEntity;

    @BeforeClass
    public static void setUpClass(){
        accountRepository = new AccountDefaultRepository(mySqlUsersFactorySession);
    }

    @After
    public void tearDown(){
        removeTestAccount(accountEntity);
    }

    @Ignore
    private AccountEntity getTestUserAccount(){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountName("Test");
        accountEntity.setPassword("Password");
        accountEntity.setStatusId(1);
        accountEntity.setCreatedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
        accountEntity.setModifiedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
        return accountEntity;
    }

    @Test
    public void testCreateUserAccount() throws Exception {
        accountEntity = getTestUserAccount();
        accountRepository.saveAccount(accountEntity);
        assertNotNull(accountEntity.getId());
    }

    @Test
    public void testUpdateUserAccount() throws Exception{
        accountEntity = getTestUserAccount();

        accountRepository.saveAccount(accountEntity);
        accountEntity.setStatusId(0);

        accountEntity = accountRepository.updateAccount(accountEntity);

        assertEquals(0, accountEntity.getStatusId());
    }

    @Test
    public void testUserReadById(){
        accountEntity = getTestUserAccount();
        accountRepository.saveAccount(accountEntity);

        AccountEntity retrievedAccountEntity = accountRepository.getById(accountEntity.getId());

        assertNotNull(retrievedAccountEntity);
        assertEquals(retrievedAccountEntity.getId(), accountEntity.getId());
    }

    @Test
    public void testUserRealAll(){
        accountEntity = getTestUserAccount();
        accountRepository.saveAccount(accountEntity);

        List<AccountEntity> accountEntityList = accountRepository.getAll();

        assertTrue(accountEntityList.size() > 0);
    }

    public void removeTestAccount(AccountEntity accountEntity){
        accountRepository.removeAccount(accountEntity);
    }

}