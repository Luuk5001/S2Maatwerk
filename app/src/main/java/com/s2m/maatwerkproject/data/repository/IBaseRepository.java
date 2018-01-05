package com.s2m.maatwerkproject.data.repository;

public interface IBaseRepository<T> {

	void insert(T entity);

	void update(T entity);

	void delete(T entity);

	void getById(String id, String callbackKey);
}