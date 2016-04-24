package com.grinder.famtime.data.repository.users;

public interface AccountSecurityRepository {
    boolean isValidUserNameAndPassword(String userName, String password);
}
