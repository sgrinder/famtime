package com.grinder.utilities.users;

import com.grinder.entities.users.UserAccount;

import java.util.List;

public interface UserUtils {
    UserAccount getUserAccountEntityByAccountId(int userId, List<UserAccount> userAccounts);
    UserAccount getUserAccountEntityByAccountName(String userName, List<UserAccount> userAccounts);
}
