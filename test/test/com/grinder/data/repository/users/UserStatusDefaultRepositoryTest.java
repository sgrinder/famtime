package com.grinder.data.repository.users;

import com.grinder.entities.users.UserStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserStatusDefaultRepositoryTest {
    private UserStatusRepository userStatusRepository;

    @Before
    public void setUp(){
        boolean testMode = true;
        userStatusRepository = new UserStatusDefaultRepository(testMode);
    }

    @After
    public void tearDown(){
        userStatusRepository.removeAllStatuses();
    }

    public UserStatus getTestUserStatus(){
        UserStatus userStatus = new UserStatus();
        userStatus.setDescription("Test");

        return userStatus;
    }

    @Test
    public void testCreateStatus() throws Exception {
        UserStatus testUserStatus = getTestUserStatus();

        boolean successful = userStatusRepository.createStatus(testUserStatus);

        assertTrue(successful);
    }

    @Test
    public void testUpdateStatus() throws Exception {
        UserStatus testUserStatus = getTestUserStatus();

        userStatusRepository.createStatus(testUserStatus);

        UserStatus userStatus = userStatusRepository.getStatuses().get(0);
        userStatus.setDescription("Tester");

        userStatusRepository.updateStatus(userStatus);

        UserStatus updatedUserStatus = userStatusRepository.getStatuses().get(0);

        boolean successful = updatedUserStatus.getDescription().equals("Tester");

        assertTrue(successful);
    }

    @Test
    public void testGetStatuses() throws Exception {
        UserStatus testUserStatus = getTestUserStatus();

        userStatusRepository.createStatus(testUserStatus);

        List<UserStatus> userStatuses = userStatusRepository.getStatuses();

        assertTrue(userStatuses.size() == 1);
    }

    @Test
    public void testGetStatusByStatusId() throws Exception {
        UserStatus testUserStatus = getTestUserStatus();

        userStatusRepository.createStatus(testUserStatus);

        UserStatus userStatus = userStatusRepository.getStatuses().get(0);

        UserStatus userStatusByStatusId = userStatusRepository.getStatusByStatusId(userStatus.getStatusId());

        assertEquals(userStatus.getStatusId(), userStatusByStatusId.getStatusId());
    }
}