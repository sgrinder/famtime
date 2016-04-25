package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.StatusEntity;

import java.util.List;

public interface StatusRepository {
    StatusEntity saveStatus(StatusEntity statusEntity);
    StatusEntity updateStatus(StatusEntity statusEntity);
    StatusEntity getById(int statusId);
    List<StatusEntity> getAll();
    void removeStatus(StatusEntity statusEntity);
}
