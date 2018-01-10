package com.s2m.maatwerkproject.data.repository;

public interface BaseRepositoryInterface<T> {
	void getById(String id, String callbackKey);
	void deleteChild(String id);
}