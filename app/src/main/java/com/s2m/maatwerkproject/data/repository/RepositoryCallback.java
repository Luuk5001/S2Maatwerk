package com.s2m.maatwerkproject.data.repository;

import java.util.List;

public interface RepositoryCallback<T> {
    void single(T obj, String callKey);
    void list(List<T> obj, String callKey);
    void error(String errorMessage);
}
