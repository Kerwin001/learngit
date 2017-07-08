package com.xmg.pss.service;

import java.util.List;

public interface IBasicService<T> {
	void save(T t);

	void update(T t);

	void delete(Long id);

	T get(Long id);

	List<T> list();
}
