package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.AccountEntity;
import org.junit.*;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class AccountEntitySecurityDefaultRepositoryTest extends TestSessionFactory {
    private static AccountSecurityRepository accountSecurityRepository;
    private static AccountRepository accountRepository;

    private AccountEntity accountEntity;

    @BeforeClass
    public static void setUp() throws Exception {
        accountSecurityRepository = new AccountSecurityDefaultRepository(mySqlUsersFactorySession);
        accountRepository = new AccountDefaultRepository(mySqlUsersFactorySession);
    }

    @After
    public void tearDown() throws Exception {
        removeUserAccount(accountEntity);
    }

    @Ignore
    private void removeUserAccount(AccountEntity accountEntity){
        accountRepository.removeAccount(accountEntity);
    }

    @Ignore
    private AccountEntity getTestUserAccount(){
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountName("Tester");
        accountEntity.setPassword("passWord");
        accountEntity.setStatusId(0);
        accountEntity.setCreatedDate(new Timestamp(new Date().getTime()));
        accountEntity.setModifiedDate(new Timestamp(new Date().getTime()));
        return accountEntity;
    }

    @Test
    public void testIsValidUserNameAndPassword() throws Exception {
        accountEntity = getTestUserAccount();
        accountRepository.saveAccount(accountEntity);

        boolean isValidUserNameAndPassword = accountSecurityRepository.isValidUserNameAndPassword("Tester", "passWord");
        assertTrue(isValidUserNameAndPassword);
    }

    @Test
    public void testIsInvalidUserNameAndPassword() throws Exception {
        accountEntity = getTestUserAccount();
        accountRepository.saveAccount(accountEntity);

        boolean isValidUserNameAndPassword = accountSecurityRepository.isValidUserNameAndPassword("Tester", "password");
        assertFalse(isValidUserNameAndPassword);
    }
}