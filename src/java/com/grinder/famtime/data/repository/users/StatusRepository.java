package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.StatusEntity;

import java.util.List;

public interface StatusRepository {
    boolean createStatus(StatusEntity userStatus);
    boolean updateStatus(StatusEntity userStatus);

    List<StatusEntity> getStatuses();
    StatusEntity getStatusByStatusId(int statusId);

    //WARNING: TESTING ONLY
    boolean removeAllStatuses();
}
