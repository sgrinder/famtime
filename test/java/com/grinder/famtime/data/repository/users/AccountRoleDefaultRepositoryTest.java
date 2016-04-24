package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.AccountRoleEntity;
import org.junit.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AccountRoleDefaultRepositoryTest extends TestSessionFactory {
    private static AccountRoleRepository accountRoleRepository;
    private AccountRoleEntity accountRoleEntity;

    @BeforeClass
    public static void setUpClass() throws Exception {
        accountRoleRepository = new AccountRoleDefaultRepository(mySqlUsersFactorySession);
    }

    @After
    public void tearDown() throws Exception {
        removeTestAccountRole(accountRoleEntity);
    }

    @Ignore
    private void removeTestAccountRole(AccountRoleEntity accountRoleEntity) {
        accountRoleRepository.removeAccountRole(accountRoleEntity);
    }

    @Test
    public void testSaveAccountRole() throws Exception {
        accountRoleEntity = getTestAccountRoleEntity();
        accountRoleRepository.saveAccountRole(accountRoleEntity);
        assertTrue(accountRoleEntity.getId() != 0);
    }

    @Test
    public void testUpdateAccountRole() throws Exception {
        accountRoleEntity = getTestAccountRoleEntity();
        accountRoleRepository.saveAccountRole(accountRoleEntity);

        accountRoleEntity.setRoleId(2);
        accountRoleRepository.updateAccountRole(accountRoleEntity);

        AccountRoleEntity updatedAccountRoleEntity = accountRoleRepository.getById(accountRoleEntity.getId());
        assertEquals(2, updatedAccountRoleEntity.getRoleId());
    }

    @Test
    public void testGetById() throws Exception {
        accountRoleEntity = getTestAccountRoleEntity();
        accountRoleRepository.saveAccountRole(accountRoleEntity);

        AccountRoleEntity accountRoleEntityById = accountRoleRepository.getById(accountRoleEntity.getId());
        assertNotNull(accountRoleEntityById);
    }

    @Test
    public void testGetByAccountId() throws Exception {
        accountRoleEntity = getTestAccountRoleEntity();
        accountRoleRepository.saveAccountRole(accountRoleEntity);

        List<AccountRoleEntity> accountRoleEntityList = accountRoleRepository.getByAccountId(accountRoleEntity.getAccountId());
        assertEquals(1, accountRoleEntityList.size());
    }

    @Test
    public void testGetByRoleId() throws Exception {
        accountRoleEntity = getTestAccountRoleEntity();
        accountRoleRepository.saveAccountRole(accountRoleEntity);

        List<AccountRoleEntity> accountRoleEntityList = accountRoleRepository.getByRoleId(accountRoleEntity.getRoleId());

        assertEquals(1, accountRoleEntityList.size());
    }

    @Test
    public void testGetAll() throws Exception {
        accountRoleEntity = getTestAccountRoleEntity();
        accountRoleRepository.saveAccountRole(accountRoleEntity);

        List<AccountRoleEntity> accountRoleEntityList = accountRoleRepository.getAll();

        assertEquals(1, accountRoleEntityList.size());
    }

    private AccountRoleEntity getTestAccountRoleEntity() {
        AccountRoleEntity accountRoleEntity = new AccountRoleEntity();
        accountRoleEntity.setAccountId(1);
        accountRoleEntity.setRoleId(1);
        accountRoleEntity.setStatusId(1);
        accountRoleEntity.setCreatedDate(new Timestamp(new Date().getTime()));
        accountRoleEntity.setModifiedDate(new Timestamp(new Date().getTime()));
        return accountRoleEntity;
    }
}