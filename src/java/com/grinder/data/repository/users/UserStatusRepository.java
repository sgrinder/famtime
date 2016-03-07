package com.grinder.data.repository.users;

import com.grinder.entities.users.UserStatus;

import java.util.List;

public interface UserStatusRepository {
    boolean createStatus(UserStatus userStatus);
    boolean updateStatus(UserStatus userStatus);

    List<UserStatus> getStatuses();
    UserStatus getStatusByStatusId(int statusId);

    //WARNING: TESTING ONLY
    boolean removeAllStatuses();
}
