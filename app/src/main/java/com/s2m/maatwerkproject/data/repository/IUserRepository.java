package com.s2m.maatwerkproject.data.repository;

import com.s2m.maatwerkproject.data.models.User;

import java.util.List;

public interface IUserRepository {
    void addUser(User user);
    void searchUsers(String keywords);
}
