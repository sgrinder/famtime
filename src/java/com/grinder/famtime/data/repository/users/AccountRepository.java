package com.grinder.famtime.data.repository.users;

import com.grinder.famtime.entities.users.AccountEntity;

import java.util.List;

public interface AccountRepository {
    AccountEntity saveAccount(AccountEntity accountEntity);
    AccountEntity updateAccount(AccountEntity accountEntity);
    AccountEntity getById(int userAccountId);
    List<AccountEntity> getAll();
    void removeAccount(AccountEntity accountEntity);
}
