package com.s2m.maatwerkproject.data.repository;

import com.s2m.maatwerkproject.data.models.User;

import java.util.List;

public interface IRepoCallback<T> {
    void single(T obj, String callKey);
    void list(List<T> obj, String callKey);
    void error();
}
