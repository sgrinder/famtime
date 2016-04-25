package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.StatusEntity;
import org.junit.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class StatusDefaultRepositoryTest extends TestSessionFactory {
    private static StatusRepository statusRepository;
    private StatusEntity statusEntity;

    @BeforeClass
    public static void setUpClass() throws Exception {
        statusRepository = new StatusDefaultRepository(mySqlUsersFactorySession);
    }

    @After
    public void tearDown() throws Exception {
        removeStatus(statusEntity);
    }

    @Ignore
    private void removeStatus(StatusEntity statusEntity) {
        statusRepository.removeStatus(statusEntity);
    }

    @Ignore
    private StatusEntity getTestStatusEntity(){
        StatusEntity statusEntity = new StatusEntity();
        statusEntity.setDescription("ACCOUNT_INACTIVE");
        statusEntity.setCreatedDate(new Timestamp(new Date().getTime()));
        statusEntity.setModifiedDate(new Timestamp(new Date().getTime()));
        return statusEntity;
    }

    @Test
    public void testSaveStatus() throws Exception {
        statusEntity = getTestStatusEntity();
        statusRepository.saveStatus(statusEntity);
        assertTrue(statusEntity.getId() != 0);
    }

    @Test
    public void testUpdateStatus() throws Exception {
        statusEntity = getTestStatusEntity();
        statusRepository.saveStatus(statusEntity);

        statusEntity.setDescription("ACCOUNT_ACTIVE");
        statusRepository.updateStatus(statusEntity);

        StatusEntity updatedStatusEntity = statusRepository.getById(statusEntity.getId());

        assertEquals("ACCOUNT_ACTIVE", updatedStatusEntity.getDescription());
        assertEquals(statusEntity.getId(), updatedStatusEntity.getId());
    }

    @Test
    public void testGetById() throws Exception {
        statusEntity = getTestStatusEntity();
        statusRepository.saveStatus(statusEntity);

        StatusEntity statusEntityById = statusRepository.getById(statusEntity.getId());
        assertNotNull(statusEntityById);
    }

    @Test
    public void testGetAll() throws Exception {
        statusEntity = getTestStatusEntity();
        statusRepository.saveStatus(statusEntity);

        List<StatusEntity> statusEntityList = statusRepository.getAll();
        assertEquals(1, statusEntityList.size());
    }
}