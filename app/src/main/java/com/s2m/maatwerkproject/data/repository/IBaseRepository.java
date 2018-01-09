package com.s2m.maatwerkproject.data.repository;

public interface IBaseRepository<T> {
	void getById(String id, String callbackKey);
	void deleteChild(String id);
	void setChildEventListener(String userId);
}