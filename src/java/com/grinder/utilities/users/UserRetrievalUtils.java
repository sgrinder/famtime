package com.grinder.utilities.users;

import com.grinder.entities.users.UserAccount;

import java.util.List;
import java.util.stream.Collectors;

public class UserRetrievalUtils implements UserUtils {

    @Override
    public UserAccount getUserAccountEntityByAccountId(int accountId, List<UserAccount> userAccounts) {
        return userAccounts
                .stream()
                .filter(userAccount -> userAccount.getAccountId() == accountId)
                .collect(Collectors.toList()).get(0);
    }

    @Override
    public UserAccount getUserAccountEntityByAccountName(String accountName, List<UserAccount> userAccounts) {
        return userAccounts
                .stream()
                .filter(userAccount -> userAccount.getAccountName().equals(accountName))
                .collect(Collectors.toList()).get(0);
    }
}
