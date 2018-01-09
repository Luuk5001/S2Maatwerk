package com.s2m.maatwerkproject.data.repository;

import com.s2m.maatwerkproject.data.models.User;

public interface UserRepositoryInterface {
    void addUser(User user);
    void searchUsers(String keywords);
}
