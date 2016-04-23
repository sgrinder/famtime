package com.grinder.data.repository.users;

import com.grinder.entities.users.UserAccount;

import java.util.List;

public interface UserAccountRepository {
    UserAccount saveAccount(UserAccount userAccount);
    UserAccount updateAccount(UserAccount userAccount);
    UserAccount getById(int userAccountId);
    List<UserAccount> getAll();
    void removeAccount(UserAccount userAccount);
}
