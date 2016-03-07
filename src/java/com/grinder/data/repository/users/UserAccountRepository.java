package com.grinder.data.repository.users;

import com.grinder.entities.users.UserAccount;

import java.util.List;

public interface UserAccountRepository {
    boolean createUserAccount(UserAccount userAccount);
    boolean updateUserAccount(UserAccount userAccount);

    List<UserAccount> getUserAccounts();

    UserAccount getUserAccountByUserAccountId(int userAccountId);
    UserAccount getUserAccountByUserName(String userName);
    UserAccount getUserAccountByUserNameAndPassword(String userName, String password);

    //WARNING: TESTING ONLY
    boolean removeAllUserAccounts();
}
