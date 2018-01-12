package com.s2m.maatwerkproject.data.repository;

import com.s2m.maatwerkproject.data.models.User;

public interface UserRepositoryInterface {
    void getUserById(String userId);
    void searchUsers(String keywords);
    void createUser(User user);
}
